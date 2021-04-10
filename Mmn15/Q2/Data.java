package Mmn15Q2;

public class Data{
	 private int x = 0;
	 private int y = 0;
	 private boolean canUpdate ; // mutex
	 
	 public Data (int x, int y){
		 this.x = x;
		 this.y = y;
		 canUpdate = true;
	 }
	 
	 // return |x-y|
	 public synchronized int getDiff(){
		 
		 // wait until canUpdate is false
		 while (canUpdate)  {		
			try {
				wait();
			} 
			catch (InterruptedException e) {					
				e.printStackTrace();
			}
		 }
		 System.out.println("the diffrence between y and x is: " + Math.abs(x-y) );
		// now the other threads can run the function update
		 canUpdate = true; 
		 notifyAll(); // wake the other threads 
		 return (Math.abs(x-y));
	 }
	 
	 // updates x and y -> x = x+dx , y =y+dy
	 public synchronized void update(int dx, int dy){
		 
		 // wait until canUpdate is true
		 while ( !canUpdate)  {
			try {
				wait();
			} 
			catch (InterruptedException e) {					
				e.printStackTrace();
			}
		 }
		 System.out.println("dx=" + dx + ", dy="+dy+". and x="+x +" y ="+ y+" after update x="+(x+dx) +" y ="+ (y+dy) );
		 x = x + dx;
		 y = y + dy;	
		// now the other threads can run the function getDiff
		 canUpdate = false;
		 notifyAll(); // wake the other threads 
	 }
	 
}
