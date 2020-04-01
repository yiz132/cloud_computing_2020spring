package Part3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.ml.feature.VectorAssembler;
import org.apache.spark.ml.regression.LinearRegression;
import org.apache.spark.ml.regression.LinearRegressionModel;
import org.apache.spark.ml.regression.LinearRegressionTrainingSummary;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.sql.*;
import org.apache.spark.sql.expressions.Window;
import scala.Tuple2;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Part3 {
    public static void run() throws IOException {
        SparkConf conf = new SparkConf().setMaster("yarn").setAppName("part3");
        JavaSparkContext sc = new JavaSparkContext(conf);
        SparkSession spark = SparkSession.builder().config(conf).getOrCreate();
        JavaRDD<String> input = sc.textFile( "access_log" ).repartition(2);
        JavaRDD<String> words = input.flatMap( s -> Collections.singletonList(sub_log(s)).iterator() );
        JavaPairRDD<String,String> pairRDD = words.mapToPair(k -> new Tuple2<>(k.split(" ")[0],parseDateString(k.split(" ")[1]))).cache(); //original input
        JavaPairRDD<String, Integer> month_counts = pairRDD.mapToPair(k -> new Tuple2<>(getMonth(k._2),1)).reduceByKey(Integer::sum).sortByKey().cache(); //after date formatting
        Encoder<Tuple2<String,Integer>> encoder = Encoders.tuple(Encoders.STRING(),Encoders.INT());
        Dataset<Row> raw = spark.createDataset(JavaPairRDD.toRDD(month_counts), encoder).toDF("Time","Counts").withColumn("label",functions.row_number().over(Window.orderBy("Time")));
        Dataset<Row> raw2 = raw.select("label","Time","Counts");
        raw2.cache();
        raw2.show();
        VectorAssembler assembler = new VectorAssembler()
                .setInputCols(new String[]{"label","Counts"})
                .setOutputCol("features");
        Dataset<Row> training = assembler.transform(raw2);
        LinearRegression lr = new LinearRegression()
                .setMaxIter(10)
                .setRegParam(0.3)
                .setElasticNetParam(0.8);
        // Fit the model.
        LinearRegressionModel lrModel = lr.fit(training);
        lrModel.write().save("model");
        Dataset<Row> out = lrModel.transform(training);
        out.cache();
        out.show();
        // Print the coefficients and intercept for linear regression.
        System.out.println("Coefficients: "
                + lrModel.coefficients() + " Intercept: " + lrModel.intercept());
        // Summarize the model over the training set and print out some metrics.
        LinearRegressionTrainingSummary trainingSummary = lrModel.summary();
        System.out.println("numIterations: " + trainingSummary.totalIterations());
        System.out.println("objectiveHistory: " + Vectors.dense(trainingSummary.objectiveHistory()));
        trainingSummary.residuals().show();
        System.out.println("RMSE: " + trainingSummary.rootMeanSquaredError());
        System.out.println("r2: " + trainingSummary.r2());
        out.write().format("parquet").save("output.parquet");
    }


    //input:   2009-07-15 15:50:35
    private static String getMonth(String s) {
        int first = s.indexOf("-")+1;
        int sec = s.indexOf("-",first);
        return s.substring(0,sec);
    }

    //return the month and year
    private static String parseDateString(String s) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss");
        Date date = format.parse(s);
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format2.format(date);
    }

    private static String sub_log(String s) {
        int start = s.indexOf("[")+1;
        int end = s.indexOf(" ", start);
        return s.substring(0,s.indexOf(" ")) +" " + s.substring(start,end);
    }


    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        run();
        long endTime   = System.currentTimeMillis();
        long totalTime = (endTime - startTime)/1000;
        System.out.println("Time taken: " + totalTime);
    }
}
