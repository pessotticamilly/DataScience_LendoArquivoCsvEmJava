package org.example;


import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import javax.ws.rs.core.Application;
import java.io.File;
import java.util.List;

public class HistogramChart extends Application {
    public static void main(String[] args) {
        SparkSession sparkSession = SparkSession.builder().master("local").appName("ReadCsv").getOrCreate();
        Dataset<Row> dataset = sparkSession.read().option("header", "true").csv("src/main/resources/telecom.csv");
        Dataset<Row> datasetMesesComoCliente = dataset.select("MesesComoCliente");
        List<Row> listaMesesComoCliente = datasetMesesComoCliente.collectAsList();
        double[] listaValoresMeses = new double[listaMesesComoCliente.size()];

        // Convertendo os valores para o gráfico
        for (int i = 0; i < listaMesesComoCliente.size(); i++) {
            listaValoresMeses[i] = Double.parseDouble(listaMesesComoCliente.get(i).get(0) + "");
        }

        // Montando o histograma
        HistogramDataset datas = new HistogramDataset();
        datas.addSeries("key", listaValoresMeses, 20);

        JFreeChart histogram = ChartFactory.createHistogram("JFreeChart Histogram", "Data", "Frequency", datas, PlotOrientation.VERTICAL, false, false, false);
        final ChartPanel chartPanel = new ChartPanel(histogram);
        chartPanel.setPreferredSize(new java.awt.Dimension(450, 270));

        // Mostrando o histograma na tela
        ApplicationFrame applicationFrame = new ApplicationFrame("Histograma");
        applicationFrame.setContentPane(chartPanel);
        applicationFrame.pack();
        RefineryUtilities.centerFrameOnScreen(applicationFrame);
        applicationFrame.setVisible(true);
    }
}