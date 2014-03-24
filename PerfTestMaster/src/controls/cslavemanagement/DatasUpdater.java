package controls.cslavemanagement;

import java.util.ArrayList;
import java.util.List;

import shared.Constants;
import shared.DataBuffer;

public class DatasUpdater implements Runnable {

	private SlaveManagementFacade slaveManagementFacade;

	public DatasUpdater(SlaveManagementFacade slaveManagementFacade) {
		this.slaveManagementFacade = slaveManagementFacade;
	}

	public void run() {

		try {

			//Wait for slaves to get its first responses
			Thread.sleep(2*Constants.SECS_IN_INTERVAL_FOREACH_RESPPACK*1000);
			
			while(true){

				List<DataBuffer> responsesPacks = new ArrayList<>();

				
				Thread.sleep(Constants.SECS_IN_INTERVAL_FOREACH_RESPPACK*1000);

				
				for(Slave slave : slaveManagementFacade.getSlave()){

					if(slave.isRunning()) {

						TCPConnection tcpConnection = slave.getTCPClientSlave();
						tcpConnection.result();
						responsesPacks.add(tcpConnection.getLastResponsePack());
					}
				}

				slaveManagementFacade.setLastReceivedResponsesPack(
						responsesPacks);
				slaveManagementFacade.updateAllSlaveListeners();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
