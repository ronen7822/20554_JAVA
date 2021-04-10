package Mmn15Q2;

public class SecondProcess extends Thread {
	private Data DataReference;
	private int sleepTime; // time of delay
	
	public SecondProcess(Data obj) {
		DataReference = obj;	
		sleepTime = (int)(100*Math.random()) ;
	}
	
	public void run() {
		for (int i=0; i < 10 ; i++) {
			int diff = DataReference.getDiff();			
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
