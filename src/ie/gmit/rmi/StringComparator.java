package ie.gmit.rmi;
/**
 * StringComparator is a class for choosing which algorithm to use
 * and compare the two strings
 * 
 * author: Dara Starr
 */
import ie.gmit.algorithms.*;

public class StringComparator implements Runnable{
	private String s;
	private String t;
	private Resultator result;
	private String algo;
	
	private Levenshtein ls = new Levenshtein();
	private HammingDistance hd = new HammingDistance();
	private DamerauLevenshtein dl = new DamerauLevenshtein();
	private JaroWinkler jw = new JaroWinkler();
	
	

	public StringComparator(String str1, String str2, Resultator r, String algorithm) {
		
		this.s = str1;
		this.t = str2;
		this.result = r;
		this.algo = algorithm;
	}

	@Override
	public void run() {
		int distance;
		double distanceD;
		float distanceF;
		String distanceS;
		
		try {
			//Decide which string comparison algorithm to run.
			if(algo.equalsIgnoreCase("Levenshtein Distance")){
				
				distance = ls.distance(s, t);
				try {
					result.setResult("Levenshtein Distance is: "+distance);
					Thread.sleep(5000);
					result.setProcessed();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			else if(algo.equalsIgnoreCase("Hamming Distance")){
				
				distance = hd.distance(s, t);
				
				try {
					result.setResult("Hamming Distance is: "+distance);
					Thread.sleep(5000);
					result.setProcessed();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			else if(algo.equalsIgnoreCase("Damerau-Levenshtein Distance")){
				
				distance = dl.distance(s, t);
				
				try {
					result.setResult("Damerau-levenshtein Distance is: "+distance);
					Thread.sleep(5000);
					result.isProcessed();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			else if(algo.equalsIgnoreCase("Jarowinkler Distance")){
				
				distanceD = jw.getSimilarity(s, t);
				
				try {
					result.setResult("Jarowinkler Distance is: "+distanceD);
					Thread.sleep(5000);
					result.isProcessed();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
				
			
		} catch (Exception e) {
			
		}
		
	}

}
