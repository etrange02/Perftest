package gui.panels.monitoring;

public class RawData {
	
	private long sendTimeMillis;
	private long receptionTimeMillis;
	
	public RawData(long sendTimeMillis, long receptionTimeMillis) {
		this.sendTimeMillis = sendTimeMillis;
		this.receptionTimeMillis = receptionTimeMillis;
	}
	
	public long getSendTimeMillis() {
		return sendTimeMillis;
	}
	public long getReceptionTimeMillis() {
		return receptionTimeMillis;
	}
}
