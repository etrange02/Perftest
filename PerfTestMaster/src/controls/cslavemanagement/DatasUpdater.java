package controls.cslavemanagement;

import java.util.ArrayList;
import java.util.List;

import shared.DataBuffer;

public class DatasUpdater implements Runnable {

	private SlaveManagementFacade slaveManagementFacade;

	public DatasUpdater(SlaveManagementFacade slaveManagementFacade) {
		this.slaveManagementFacade = slaveManagementFacade;
	}

	public void run() {

		try {

			while(true){

				List<DataBuffer> responsesPacks = new ArrayList<>();

				Thread.sleep(5000);

				
				System.out.println("DatasUpdater.run(): going to update ");
				for(Slave slave : slaveManagementFacade.getSlave()){

					if(slave.isRunning()) {

						TCPConnection tcpConnection = slave.getTCPClientSlave();
						tcpConnection.result();
						responsesPacks.add(tcpConnection.getLastResponsePack());
					}
				}

				slaveManagementFacade.setLastReceivedResponsesPack(
						responsesPacks);
				System.out.println("DatasUpdater.run(): done ");
				slaveManagementFacade.updateAllSlaveListeners();
				System.out.println("DatasUpdater.run(): notified ");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
