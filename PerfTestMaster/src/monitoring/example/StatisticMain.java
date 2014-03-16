package monitoring.example;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jCharts.axisChart.AxisChart;
import org.jCharts.chartData.AxisChartDataSet;
import org.jCharts.chartData.DataSeries;
import org.jCharts.properties.AxisProperties;
import org.jCharts.properties.ChartProperties;
import org.jCharts.properties.ClusteredBarChartProperties;
import org.jCharts.properties.LegendProperties;
import org.jCharts.test.TestDataGenerator;
import org.jCharts.types.ChartType;

public class StatisticMain extends JFrame{

    /**
     * 
     */
    private static final long serialVersionUID = -9210479042987276319L;
    private JPanel panel;

    public StatisticMain() throws Exception {
	initComponents();
    }
    
    public void initComponents() throws Exception {

	this.setSize( 500, 500 );
	this.panel=new JPanel( true );
	this.panel.setSize( 500, 500 );
	this.getContentPane().add( this.panel );
	this.setVisible( true );

	String[] xAxisLabels= { "1998", "1999", "2000", "2001", "2002", "2003", "2004" };
	String xAxisTitle= "Years";
	String yAxisTitle= "Problems";
	String title= "Micro$oft at Work";
	DataSeries dataSeries = new DataSeries( xAxisLabels, xAxisTitle, yAxisTitle, title );

	double[][] data= new double[][]{ { 250, 45, -36, 66, 145, 80, 55 }, { 150, 15, 6, 62, -54, 10, 84 }, { 20, 145, 36, 6, 45, 18, 5 } };
	String[] legendLabels= { "Bugs", "Security Holes", "Backdoors" };
	Paint[] paints= TestDataGenerator.getRandomPaints( 3 );
	ClusteredBarChartProperties clusteredBarChartProperties= new ClusteredBarChartProperties();
	AxisChartDataSet axisChartDataSet= new AxisChartDataSet( data, legendLabels, paints, ChartType.BAR_CLUSTERED, clusteredBarChartProperties );
	dataSeries.addIAxisPlotDataSet( axisChartDataSet );

	ChartProperties chartProperties= new ChartProperties();

	//---to make this plot horizontally, pass true to the AxisProperties Constructor
	//AxisProperties axisProperties= new AxisProperties( true );
	AxisProperties axisProperties= new AxisProperties();
	LegendProperties legendProperties= new LegendProperties();
	AxisChart axisChart= new AxisChart( dataSeries, chartProperties, axisProperties, legendProperties, 450, 300 );


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
	new StatisticMain();
	System.out.println("finish");
    }
}
