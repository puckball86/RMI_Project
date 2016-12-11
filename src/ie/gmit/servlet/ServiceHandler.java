package ie.gmit.servlet;
/**
 * ServiceHandler class is a servlet
 * 
 * author: Dara Starr
 * 
 */

import java.io.*;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.*;
import javax.servlet.http.*;

import ie.gmit.rmi.Requestator;
import ie.gmit.rmi.Resultator;
import ie.gmit.rmi.ServiceHandlerImpl;
import ie.gmit.rmi.StringService;

public class ServiceHandler extends HttpServlet {
	private static final long serialersionUID = 1L;
	private String remoteHost = null;
	private static long jobNumber = 0;
	
	private volatile BlockingQueue<Requestator> inQueue;
	private volatile Map<String, Resultator> outQueue;
	private volatile ExecutorService executor;
	
	//variables for checking
	private volatile String distance = "";
	private final int THREAD_POOL_SIZE = 4;
	private volatile boolean checkProcessed = false;

	public void init() throws ServletException {
		
		ServletContext ctx = getServletContext();
		remoteHost = ctx.getInitParameter("RMI_SERVER");
		//Reads the value from the <context-param> in web.xml
		
		//Setting up inQueue, outQueue and executor
		inQueue = new LinkedBlockingQueue<Requestator>();
		outQueue = new HashMap<String, Resultator>();
		executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		//Initialise some request variables with the submitted form info. These are local to this method and thread safe...
		String algorithm = req.getParameter("cmbAlgorithm");
		String s = req.getParameter("txtS");
		String t = req.getParameter("txtT");
		String taskNumber = req.getParameter("frmTaskNumber");
		
		StringService service = null;
		try {
			service = (StringService) Naming.lookup("rmi://localhost:1099/stringservice");
			
		} catch (NotBoundException e1) {
			e1.printStackTrace();
		}


		out.print("<html><head><title>Distributed Systems Assignment</title>");		
		out.print("</head>");		
		out.print("<body>");
		
		//adding a request to the queue
		if (taskNumber == null){
			jobNumber++;
			
			taskNumber = new String("T" + jobNumber);
			
			checkProcessed = false;
			
			Requestator request = new Requestator(s, t, algorithm, taskNumber);
			
			inQueue.add(request);
			
			Runnable worker = new ServiceHandlerImpl(inQueue, outQueue, service);
			
			executor.execute(worker);
			
			
					
		}else {
			//Check out-queue for finished jobs
			if(outQueue.containsKey(taskNumber)){
				
				Resultator queueItem = outQueue.get(taskNumber);
				
				System.out.println("\nChecking Status of Task No:" + taskNumber);

				checkProcessed = queueItem.isProcessed();
				
				// Check to see if the item is processed
				if (checkProcessed == true) {
					// Remove the processed item from Map using the tackNumber
					outQueue.remove(taskNumber);
					//Get the Distance of the Current Task
					distance = queueItem.getResult();

					out.print("\nTask " + taskNumber + " Successfully Processed and Removed from OutQueue");
					out.print("Distance Between String (" + s + ") and String (" + t + ") = " + distance);
				}

			}
		}
		
		out.print("<H1>Processing request for Job#: " + taskNumber + "</H1>");
		out.print("<div id=\"r\"></div>");
		
		out.print("<font color=\"#993333\"><b>");
		out.print("RMI Server is located at " + remoteHost);
		out.print("<br>Algorithm: " + algorithm);		
		out.print("<br>String <i>s</i> : " + s);
		out.print("<br>String <i>t</i> : " + t);
	
		
	
		
		
		//If the task is complete, Show the Distance
		//otherwise the page will refresh and display checking 
		if (checkProcessed == true){
			out.print("<br><br>Distance: " + distance + "<br>");
		}	
		else {
			out.print("<form name=\"frmRequestDetails\">");// RefreshPage
			out.print("<br><br>Checking Distance, Please Wait..<br>");
		}
		
		
		out.print("<form name=\"frmRequestDetails\">");
		out.print("<input name=\"cmbAlgorithm\" type=\"hidden\" value=\"" + algorithm + "\">");
		out.print("<input name=\"txtS\" type=\"hidden\" value=\"" + s + "\">");
		out.print("<input name=\"txtT\" type=\"hidden\" value=\"" + t + "\">");
		out.print("<input name=\"frmTaskNumber\" type=\"hidden\" value=\"" + taskNumber + "\">");
		out.print("</form>");								
		out.print("</body>");	
		out.print("</html>");
		
		
		out.print("<script>");
		out.print("var wait=setTimeout(\"document.frmRequestDetails.submit();\", 10000);");
		out.print("</script>");
				
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
 	}
}