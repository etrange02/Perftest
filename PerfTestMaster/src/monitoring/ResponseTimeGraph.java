package monitoring;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;

import javax.swing.JPanel;

import org.jCharts.axisChart.AxisChart;
import org.jCharts.chartData.AxisChartDataSet;
import org.jCharts.chartData.DataSeries;
import org.jCharts.properties.AxisProperties;
import org.jCharts.properties.ChartProperties;
import org.jCharts.properties.LegendProperties;
import org.jCharts.properties.LineChartProperties;
import org.jCharts.properties.PointChartProperties;
import org.jCharts.test.TestDataGenerator;
import org.jCharts.types.ChartType;

public class ResponseTimeGraph extends JPanel {


    private DataInfosProvider dataInfosProvider;

    public ResponseTimeGraph(DataInfosProvider dataInfosProvider) {
	this.dataInfosProvider = dataInfosProvider;
    }

    public void createGraph() throws Exception {
	
	String[] xAxisLabels= 
	    { 
		"1", "2", "3", "4", "5", "6", "7", "8", "9", "10" 
	    };
	String xAxisTitle= "s";
	String yAxisTitle= "request/s";
	String title= "Request handling capacity";
	DataSeries dataSeries = new DataSeries( 
		xAxisLabels, xAxisTitle, yAxisTitle, title );

	double[][] data= new double[][]{ { 250, 45, -36, 66, 145, 80, 55 } };
	String[] legendLabels= { "Bugs" };
	Paint[] paints= TestDataGenerator.getRandomPaints( 1 );

	Stroke[] strokes= { LineChartProperties.DEFAULT_LINE_STROKE };
	Shape[] shapes= { PointChartProperties.SHAPE_CIRCLE };
	LineChartProperties lineChartProperties= new LineChartProperties( 
		strokes, shapes );

	AxisChartDataSet axisChartDataSet= new AxisChartDataSet( 
		data, legendLabels, paints, ChartType.LINE, 
		lineChartProperties );
	dataSeries.addIAxisPlotDataSet( axisChartDataSet );

	ChartProperties chartProperties= new ChartProperties();
	AxisProperties axisProperties= new AxisProperties();
	LegendProperties legendProperties= new LegendProperties();

	AxisChart axisChart= new AxisChart( 
		dataSeries, chartProperties, 
		axisProperties, legendProperties, 450, 400 );

	axisChart.setGraphics2D((Graphics2D) getGraphics());
	axisChart.render();
    }

    @Override
    public void paint(Graphics g) {
	try {

	    createGraph();
	    super.paint(g);

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
