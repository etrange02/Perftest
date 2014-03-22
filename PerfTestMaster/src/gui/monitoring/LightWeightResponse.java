package gui.monitoring;

public class LightWeightResponse {
	
	private long sendTimeMillis;
	private long receptionTimeMillis;
	
	public LightWeightResponse(long sendTimeMillis, long receptionTimeMillis) {
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
