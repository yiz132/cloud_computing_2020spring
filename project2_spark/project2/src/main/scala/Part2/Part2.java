package Part2;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;


public class Part2 {
    public static void main( String[] args )
    {
        //SparkConf conf = new SparkConf().setMaster("yarn").setAppName("part2");
        SparkConf conf = new SparkConf().setMaster("local").setAppName("part2");

        SparkSession part = SparkSession.builder().config(conf).getOrCreate();

        Dataset<Row> artist = part.read().format("com.databricks.spark.csv").option("delimiter", "\t").option("inferSchema", "true")
                .option("header", "true").load("user_artists.dat");

        Dataset<Row>  colu = artist.groupBy(artist.col("artistID")).sum("weight");
        Dataset<Row> top = colu.sort(colu.col("sum(weight)").desc());
        top.show();
    }
}