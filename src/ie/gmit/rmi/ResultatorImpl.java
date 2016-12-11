package ie.gmit.rmi;
/**
 * ResultatorImpl extends UnicastRemoteObject and implements the interface Resultator
 * 
 * @author Dara Starr
 */

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ResultatorImpl extends UnicastRemoteObject implements Resultator{
	
	private static final long serialVersionUID = 1L;
	private String s;
	private String t;
	private String result;
	private boolean isProcessed = false;
	
	public ResultatorImpl() throws RemoteException{
		
	}

	public ResultatorImpl(String str1, String str2) throws RemoteException{
		this.s = str1;
		this.t = str2;
	}

	@Override
	public String getResult() throws RemoteException {
		
		return result;
	}

	@Override
	public void setResult(String result) throws RemoteException {
		
		this.result = result;
	}

	@Override
	public boolean isProcessed() throws RemoteException {
		
		return isProcessed;
	}

	@Override
	public void setProcessed() throws RemoteException {
		
		this.isProcessed = true;
	}

}
