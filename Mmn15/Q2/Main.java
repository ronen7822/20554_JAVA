package Mmn15Q2;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Data data = new Data(0,0);
	
		// update
		FirstProcess process1 = new FirstProcess(data);				
		// getting the difference
		SecondProcess process2 = new SecondProcess(data);
		
		process1.start();
		process2.start();		

	}

}
