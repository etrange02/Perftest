package monitoring.capacity;

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

public class CapacityGraph extends JPanel {


	private CapacityDatasProvider capacityInfosProvider;

	public CapacityGraph(CapacityDatasProvider capacityInfosProvider) {
		this.capacityInfosProvider = capacityInfosProvider;
	}

	public void createGraph() throws Exception {

		SortedMap<TimeInMillis, Double>  requestBySecAverages = 
				capacityInfosProvider.getDelaysAverages();
		Iterator<TimeInMillis> requestBySecAveragesIterator = 
				requestBySecAverages.keySet().iterator();
		int i = 0;

		String[] xAxisLabels= new String[requestBySecAverages.size()];
		double[] values = new double[requestBySecAverages.size()];

		while(requestBySecAveragesIterator.hasNext()) {
			TimeInMillis tmfts = 
					requestBySecAveragesIterator.next();
			xAxisLabels[i] = Double.toString(
					tmfts.getTimeInMillisFromTestStart());
			values[i] = requestBySecAverages.get(tmfts);
			i++;
		}

		String xAxisTitle= "ms";
		String yAxisTitle= "delay average";
		String title= "Request handling capacity";
		DataSeries dataSeries = new DataSeries( 
				xAxisLabels, xAxisTitle, yAxisTitle, title );

		double[][] data= new double[][] { values };



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
				axisProperties, legendProperties, 650, 600 );

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
