package org.example;


import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class Main {
    public static void main(String[] args) {
//        SparkSession sparkSession = SparkSession.builder().master("local").appName("ReadCsv").getOrCreate();
//        Dataset<Row> dataset = sparkSession.read().option("header", "true").csv("src/main/resources/telecom.csv");
//
//        dataset.show();
//        dataset.groupBy("TipoContrato").count().show();
//        dataset.groupBy("TipoContrato").count().select("TipoContrato").show();
//        dataset.groupBy("TipoContrato").count().select("count").show();
    }
}