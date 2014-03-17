package monitoring.capacity;

public class TimeInMillisFromTestStart 
implements Comparable<TimeInMillisFromTestStart> {

    private double timeInMillisFromTestStart;



    /* *********************************************************************
     * CONSTRUCTORS ********************************************************
     * *********************************************************************/

    public TimeInMillisFromTestStart(double timeInMillisFromTestStart) {
	this.timeInMillisFromTestStart = timeInMillisFromTestStart;
    }



    /* *********************************************************************
     * GETTERS/SETTERS *****************************************************
     * *********************************************************************/

    public double getTimeInMillisFromTestStart() {
	return timeInMillisFromTestStart;
    }



    /* *********************************************************************
     * OTHERS **************************************************************
     * *********************************************************************/

    @Override
    public boolean equals(Object object) {
	return  (object instanceof TimeInMillisFromTestStart) &&
		compareTo((TimeInMillisFromTestStart)object)==0;
    }

    @Override
    public int compareTo(TimeInMillisFromTestStart tmfts) {

	if(tmfts.getTimeInMillisFromTestStart() == 
		timeInMillisFromTestStart) {
	    return 0;
	}
	else if(tmfts.getTimeInMillisFromTestStart() < 
		timeInMillisFromTestStart) {
	    return 1;
	}
	else {
	    return -1;
	}
    }
}
