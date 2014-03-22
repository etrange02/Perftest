package monitoring.capacity;

public class TimeInMillis 
implements Comparable<TimeInMillis> {

	private long timeInMillisFromTestStart;



	/* *********************************************************************
	 * CONSTRUCTORS ********************************************************
	 * *********************************************************************/

	public TimeInMillis(long timeInMillisFromTestStart) {
		this.timeInMillisFromTestStart = timeInMillisFromTestStart;
	}



	/* *********************************************************************
	 * GETTERS/SETTERS *****************************************************
	 * *********************************************************************/

	public long getTimeInMillisFromTestStart() {
		return timeInMillisFromTestStart;
	}



	/* *********************************************************************
	 * OTHERS **************************************************************
	 * *********************************************************************/

	@Override
	public boolean equals(Object object) {
		return  (object instanceof TimeInMillis) &&
				compareTo((TimeInMillis)object)==0;
	}

	@Override
	public int compareTo(TimeInMillis tmfts) {

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
