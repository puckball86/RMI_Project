package ie.gmit.rmi;
/**
 * @author Dara Starr
 */

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;


	public class StringServiceImpl extends UnicastRemoteObject implements StringService{
		
		private static final long serialVersionUID = 1L;
		
		public StringServiceImpl() throws RemoteException {
			super();
		}

		@Override
		public Resultator compare(String s, String t, String algo) throws RemoteException {			
			Resultator result = new ResultatorImpl();
			
			//compare
			Thread thread = new Thread(new StringComparator(s, t, result, algo));
			thread.start();
			
			return result;
		}

			

} // class

