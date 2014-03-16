package monitoring.example;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
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


public class Main extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -2163565033253616857L;
    private JPanel panel;

    public Main() throws Exception {
	initComponents();
    }

    private void initComponents() throws Exception {

	this.setSize( 500, 500 );
	this.panel=new JPanel( true );
	this.panel.setSize( 500, 500 );
	this.getContentPane().add( this.panel );
	this.setVisible( true );

	String[] xAxisLabels= 
	    { "1998", "1999", "2000", "2001", "2002", "2003", "2004" };
	String xAxisTitle= "Years";
	String yAxisTitle= "Problems";
	String title= "Micro$oft at Work";
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

	axisChart.setGraphics2D((Graphics2D) this.panel.getGraphics());
	axisChart.render();

	addWindowListener( new java.awt.event.WindowAdapter()
	{
	    public void windowClosing( WindowEvent windowEvent )
	    {
		exitForm( windowEvent );
	    }
	});
    }

    private void exitForm( WindowEvent windowEvent )
    {
	System.exit( 0 );
    }

    public static void main(String[] args) throws Exception {
	new Main();
	System.out.println("finish");
    }
}
