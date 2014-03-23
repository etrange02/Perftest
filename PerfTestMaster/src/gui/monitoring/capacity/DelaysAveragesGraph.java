package gui.monitoring.capacity;

import gui.monitoring.interfaces.IGUIMonitor;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.util.Iterator;
import java.util.SortedMap;

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

import controls.cslavemanagement.SlaveManagementFacade;

public class DelaysAveragesGraph extends JPanel implements IGUIMonitor {


	private DelaysInfosProvider delaysInfosProvider;
	
	
	/* *********************************************************************
	 * CONSTRUCTORS ********************************************************
	 * *********************************************************************/

	/**
	 * @param timeInterval Forall k natural, all request sent into 
	 * [0 + k*timeInterval,(k+1)*timeInterval[ are considered to have 
	 * been sent at (k+1)*(timeInterval/2.0). timeInterval is in millisec.
	 * @throws Exception 
	 */
	public DelaysAveragesGraph(
			int timeInterval,
			SlaveManagementFacade slaveManagementFacade) {
		
		super(true);
		
		this.delaysInfosProvider = 
				new DelaysInfosProvider(
						timeInterval,
						slaveManagementFacade);
	}

	
	
	/* *********************************************************************
	 * GETTERS/SETTERS *****************************************************
	 * *********************************************************************/
	
	public DelaysInfosProvider getDelaysInfosProvider() {
		return delaysInfosProvider;
	}
	
	
	/* *********************************************************************
	 * IMPORTANTS **********************************************************
	 * *********************************************************************/
	
	private void createGraph() throws Exception {

		SortedMap<Double, Double>  requestBySecAverages = 
				delaysInfosProvider.getDelaysAverages();
		Iterator<Double> requestBySecAveragesIterator = 
				requestBySecAverages.keySet().iterator();
		int i = 0;

		String[] xAxisLabels= new String[requestBySecAverages.size()];
		double[] values = new double[requestBySecAverages.size()];

		while(requestBySecAveragesIterator.hasNext()) {
			Double timeFromOriginInMillis = 
					requestBySecAveragesIterator.next();
			xAxisLabels[i] = timeFromOriginInMillis.toString();
			values[i] = requestBySecAverages.get(timeFromOriginInMillis);
			i++;
		}

		String xAxisTitle= "ms";
		String yAxisTitle= "delay average";
		String title= "delays average";
		DataSeries dataSeries = new DataSeries( 
				xAxisLabels, xAxisTitle, yAxisTitle, title );

		double[][] data= new double[][] { values };



		String[] legendLabels= { "No legend" };
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
				axisProperties, legendProperties, 650, 600 );

		Graphics g = this.getGraphics();
		
		if(g==null) {
			System.out.println("DelaysAvaragesGraph.createGraph(): g is null");
		}
		
		axisChart.setGraphics2D((Graphics2D) this.getGraphics());
		axisChart.render();
	}
	
	@Override
	public void update() {
		
		try {
			createGraph();
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
	}
}
