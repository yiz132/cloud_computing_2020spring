package Part3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.regression.LinearRegressionModel;
import org.apache.spark.mllib.regression.LinearRegressionWithSGD;
import scala.Tuple2;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Part3 {
    public static void run(){
        SparkConf conf = new SparkConf().setMaster("local").setAppName("part3");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> input = sc.textFile( "access_log" ).repartition(2);
        JavaRDD<String> words = input.flatMap( s -> Collections.singletonList(sub_log(s)).iterator() );
//        words.foreach(s -> System.out.println(s.toString()));
        JavaPairRDD<String,String> pairRDD = words.mapToPair(k -> new Tuple2<>(k.split(" ")[0],parseDateString(k.split(" ")[1]))).cache(); //original input
        JavaPairRDD<String, Integer> month_counts = pairRDD.mapToPair(k -> new Tuple2<>(getMonth(k._2),1)).reduceByKey(Integer::sum).sortByKey().cache(); //after date formatting
//        pairRDD.foreach(System.out::println);
//        List<Tuple2<String, String>> top = pairRDD.take(5);
//        top.forEach(best -> System.out.println(best._1+ " "+best._2));
        List<Tuple2<String, Integer>> topCounts = month_counts.take(10);
        topCounts.forEach(k -> System.out.println(k._1 + ":" + k._2));
        JavaRDD<LabeledPoint> parsedData = month_counts.map(new Function<Tuple2<String, Integer>, LabeledPoint>() {
            @Override
            public LabeledPoint call(Tuple2<String, Integer> stringIntegerTuple2) throws Exception {
                String key = stringIntegerTuple2._1;
                int mid = key.indexOf("-");
                int year = Integer.parseInt(key.substring(0,mid));
                int month = Integer.parseInt(key.substring(mid+1));
                double label = 12*year+month;
                int value = stringIntegerTuple2._2;
                Vector v = Vectors.dense((int)label,value);
                return new LabeledPoint(label,v);
            }
        });
        parsedData.cache();
        int numIterations = 100;
        LinearRegressionModel linearModel = LinearRegressionWithSGD.train(parsedData.rdd(), numIterations);
        print(parsedData, linearModel);
    }

    private static void print(JavaRDD<LabeledPoint> parsedData, LinearRegressionModel model) {
        JavaPairRDD<Double, Double> valuesAndPreds = parsedData.mapToPair(point -> {
            double prediction = model.predict(point.features()); //用模型预测训练数据
            return new Tuple2<>(point.label(), prediction);
        });
        //打印训练集中的真实值与相对应的预测值
        valuesAndPreds.foreach((Tuple2<Double, Double> t) -> {
            System.out.println("训练集真实值："+t._1()+" ,预测值： "+t._2());
        });
        //计算预测值与实际值差值的平方值的均值
        Double MSE = valuesAndPreds.mapToDouble((Tuple2<Double, Double> t) -> Math.pow(t._1() - t._2(), 2)).mean();
        System.out.println(model.getClass().getName() + " training Mean Squared Error = " + MSE);
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


    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        run();
        long endTime   = System.currentTimeMillis();
        long totalTime = (endTime - startTime)/1000;
        System.out.println("Time taken: " + totalTime);
    }
}
