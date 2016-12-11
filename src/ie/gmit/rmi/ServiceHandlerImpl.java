package ie.gmit.rmi;

/**
 * @author Dara Starr
 */

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ServiceHandlerImpl implements Runnable{
	private BlockingQueue<Requestator> queueIn;
	private Resultator result;
	private StringService strSer;
	private Map<String, Resultator> queueOut;
	
	public ServiceHandlerImpl(BlockingQueue<Requestator> queueIn, Map<String, Resultator> queueOut, StringService strSer) {
		
		this.queueIn = queueIn;
		this.strSer = strSer;
		this.queueOut = queueOut;
	}

	@Override
	public void run() {
		Requestator request = queueIn.poll();
		
		try {
			
			Thread.sleep(6000);
			
			result = strSer.compare(request.getStr1(), request.getStr2(), request.getAlgo());
			
			queueOut.put(request.getTaskNumber(), result);
		} catch (Exception e) {
			
			e.printStackTrace();
		}		
		
	}

}
