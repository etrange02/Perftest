package gui.panels.monitoring.delays;

import gui.panels.monitoring.AbstractGraphPanel;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

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

public class DelaysAveragesGraph extends AbstractGraphPanel {


    private DelaysInfosProvider delaysInfosProvider;
    private SortedMap<Long, Double>  requestBySecAverages;
    
    /**
     * The last displayed time (in the X axis). Use to create a more realist
     * default graph when we got no value from infos providers.
     */
    private Long lastTime;


    
    /* *********************************************************************
     * CONSTRUCTORS ********************************************************
     * *********************************************************************/

    /**
     * @param timeInterval Forall k natural, all request sent into 
     * [0 + k*timeInterval,(k+1)*timeInterval[ are considered to have 
     * been sent at (k+1)*(timeInterval/2.0). timeInterval is in millisec.
     * @param nbSecToDisplay the number of second to display in the graph. By 
     * example, if nbSecToDisplay = 5, the at 7, averages for 3,4,5,6,7 will be
     * displayed.
     * @throws Exception 
     */
    public DelaysAveragesGraph(
	    SlaveManagementFacade slaveManagementFacade,
	    int nbSecToDisplay) {

	this.delaysInfosProvider = 
		new DelaysInfosProvider(slaveManagementFacade, nbSecToDisplay);
	this.requestBySecAverages = null;
	this.lastTime = new Long(0);
    }



    /* *********************************************************************
     * GETTERS/SETTERS *****************************************************
     * *********************************************************************/

    public DelaysInfosProvider getInfosProvider() {
	return delaysInfosProvider;
    }


    /* *********************************************************************
     * IMPORTANTS **********************************************************
     * *********************************************************************/

    public void updatePanel() throws Exception {

	
	requestBySecAverages = delaysInfosProvider.getDelaysAverages();
	
	if(requestBySecAverages == null ||  requestBySecAverages.isEmpty()) {
	    createDefaultDelaysAverageValues();
	}
	
	Iterator<Long> requestBySecAveragesIterator = 
		requestBySecAverages.keySet().iterator();
	int i = 0;
	String[] xAxisLabels= new String[requestBySecAverages.size()];
	double[] values = new double[requestBySecAverages.size()];
	

	Long tmp = null;
	while(requestBySecAveragesIterator.hasNext()) {

	    tmp = requestBySecAveragesIterator.next();
	    
	  //display second instead of millis
	    xAxisLabels[i] = new Double(tmp/1000.0).toString(); 
	    values[i] = requestBySecAverages.get(tmp);
	    i++;
	}
	lastTime = tmp;

	String xAxisTitle= "time(s)";
	String yAxisTitle= "delay average(ms)";
	String title= 
		"Delays averages. Total success: "+delaysInfosProvider.getTotalSuccess()+
		", Total miss: "+delaysInfosProvider.getTotalMiss();
	DataSeries dataSeries = new DataSeries( 
		xAxisLabels, xAxisTitle, yAxisTitle, title );

	double[][] data= new double[][] { values };

	String[] legendLabels= { "delay average" };
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
		axisProperties, legendProperties, 500, 450 );

	axisChart.setGraphics2D((Graphics2D) this.getGraphics());
	axisChart.render();
    }
    
    /**
     * Create default delays average value. Usefull when the infos provider
     * give us nothing to display.
     */
    private void createDefaultDelaysAverageValues() {
	
	requestBySecAverages = new TreeMap<>();
	
	requestBySecAverages.put(lastTime+1, -1.0);
    }
}
