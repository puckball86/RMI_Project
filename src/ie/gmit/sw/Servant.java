package ie.gmit.sw;
/**
 * @author Dara Starr
 */

import java.rmi.Naming;

import java.rmi.registry.LocateRegistry;

import ie.gmit.rmi.StringService;
import ie.gmit.rmi.StringServiceImpl;;

public class Servant {
	
	public static void main(String[] args) throws Exception{
		
		
		StringService service = new StringServiceImpl();
		//Start RMI on port 1099
		LocateRegistry.createRegistry(1099);
		
		Naming.rebind("stringservice", service);
		
		//Print a comment to standard output
		System.out.println("Connected........");
	}

}
