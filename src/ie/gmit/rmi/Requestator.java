package ie.gmit.rmi;
/**
 * Requestator class is used for passing an object to the queue
 * 
 * @author Dara Starr
 *
 */

public class Requestator {
	private String algo;
	private String str1;
	private String str2;
	private String taskNumber;
	
	public Requestator(){
		super();
	}
	
	public Requestator(String s, String t, String algorithm, String taskNum) {
		
		this.algo = algorithm;
		this.str1 = s;
		this.str2 = t;
		this.taskNumber = taskNum;
	}

	public String getAlgo() {
		return algo;
	}

	public void setAlgo(String algo) {
		this.algo = algo;
	}

	public String getStr1() {
		return str1;
	}

	public void setStr1(String str1) {
		this.str1 = str1;
	}

	public String getStr2() {
		return str2;
	}

	public void setStr2(String str2) {
		this.str2 = str2;
	}

	public String getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}

	
	
	

}
