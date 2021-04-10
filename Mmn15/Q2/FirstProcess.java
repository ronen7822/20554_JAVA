package Mmn15Q2;

public class FirstProcess extends Thread {
	private int [] xArr = new int [10];
	private int [] yArr = new int [10];
	private Data DataReference;
	private int sleepTime; // time of delay
	
	public FirstProcess(Data obj) {
		for (int i=0; i < xArr.length ; i++) {
			xArr[i] = (int)(100*Math.random()) ;
			yArr[i] = (int)(100*Math.random()) ;
		}	
		DataReference = obj;	
		sleepTime = (int)(100*Math.random()) ;
	}
	
	public void run() {
		for (int i=0; i < 10 ; i++) {
			DataReference.update(xArr[i],yArr[i]);
			// go to sleep for random time
			try {
				Thread.sleep(sleepTime);
			} 
			catch (InterruptedException e)	{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
