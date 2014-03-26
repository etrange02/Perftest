package gui.panels.monitoring.variance;

import gui.panels.monitoring.AbstractGraphPanel;
import gui.panels.monitoring.AbstractInfosProvider;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

import org.jCharts.axisChart.AxisChart;
import org.jCharts.chartData.AxisChartDataSet;
import org.jCharts.chartData.DataSeries;
import org.jCharts.properties.AxisProperties;
import org.jCharts.properties.ChartProperties;
import org.jCharts.properties.ClusteredBarChartProperties;
import org.jCharts.properties.LegendProperties;
import org.jCharts.test.TestDataGenerator;
import org.jCharts.types.ChartType;

import controls.cslavemanagement.SlaveManagementFacade;

public class VarianceGraph extends AbstractGraphPanel {

    private VarianceInfosProvider varianceInfosProvider;
    private SortedMap<Long, Double>  varianceInfos;
    
    
    
    /* *********************************************************************
     * CONSTRUCTORS ********************************************************
     * *********************************************************************/
    
    public VarianceGraph(SlaveManagementFacade slaveManagement) {
	
	varianceInfosProvider = new VarianceInfosProvider(slaveManagement);
	varianceInfos = null;
    }
    
    
    
    /* *********************************************************************
     * GETTERS/SETTERS *****************************************************
     * *********************************************************************/
    
    @Override
    public AbstractInfosProvider getInfosProvider() {
	return varianceInfosProvider;
    }

    
    
    /* *********************************************************************
     * IMPORTANTS **********************************************************
     * *********************************************************************/
    
    @Override
    public void updatePanel() throws Exception {
	
	varianceInfos = varianceInfosProvider.getVarianceInfos();
	
	if(varianceInfos == null ||  varianceInfos.isEmpty()) {
	    createDefaultVarianceValues();
	}
	

	Iterator<Long> varianceIterator = 
		varianceInfos.keySet().iterator();
	int i = 0;
	String[] xAxisLabels= new String[varianceInfos.size()];
	double[] values= new double[varianceInfos.size()];
	Long tmp = null;
	
	while(varianceIterator.hasNext()){
	    
	    tmp = varianceIterator.next();
	    xAxisLabels[i] = tmp.toString();
	    values[i] = varianceInfos.get(tmp);
	    i++;
	}
	
	String xAxisTitle= "time(ms)";
	String yAxisTitle= "% of total request";
	String title= "Variance";
	DataSeries dataSeries = new DataSeries( xAxisLabels, xAxisTitle, yAxisTitle, title );

	double[][] data= new double[][]{ values };
	String[] legendLabels= { "variance" };
	Paint[] paints= TestDataGenerator.getRandomPaints( 1 );
	ClusteredBarChartProperties clusteredBarChartProperties= new ClusteredBarChartProperties();
	AxisChartDataSet axisChartDataSet= new AxisChartDataSet( data, legendLabels, paints, ChartType.BAR_CLUSTERED, clusteredBarChartProperties );
	dataSeries.addIAxisPlotDataSet( axisChartDataSet );

	ChartProperties chartProperties= new ChartProperties();

	//---to make this plot horizontally, pass true to the AxisProperties Constructor
	//AxisProperties axisProperties= new AxisProperties( true );
	AxisProperties axisProperties= new AxisProperties();
	LegendProperties legendProperties= new LegendProperties();
	AxisChart axisChart= new AxisChart( dataSeries, chartProperties, axisProperties, legendProperties, 650, 600 );


	axisChart.setGraphics2D((Graphics2D) this.getGraphics());
	axisChart.render();
    }

    /**
     * Create default variance values. Usefull when the infos provider
     * give us nothing to display.
     */
    public void createDefaultVarianceValues() {
	
	varianceInfos = new TreeMap<>();
	varianceInfos.put(new Long(10), 0.0);
    }
}
