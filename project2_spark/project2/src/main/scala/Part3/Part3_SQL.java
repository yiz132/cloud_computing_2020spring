package Part3;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class Part3_SQL {

    public static void run(){
        SparkConf conf = new SparkConf().setMaster("local").setAppName("part3");
        SparkSession spark = SparkSession.builder().config(conf).getOrCreate();
        Dataset<Row> training = spark.read().format("libsvm")
                .load("access_log");
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        run();
        long endTime   = System.currentTimeMillis();
        long totalTime = (endTime - startTime)/1000;
        System.out.println("Time taken: " + totalTime);
    }
}
