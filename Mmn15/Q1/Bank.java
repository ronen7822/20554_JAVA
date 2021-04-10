package Mmn15Q1;

public class Bank {

	// main method to test the threads
	public static void main(String[] args) {
		
		// 5 new bank accounts
		BankAccount first = new BankAccount(0,0);
		BankAccount second = new BankAccount(1,0);
		BankAccount third = new BankAccount(2,0);
		BankAccount fourt = new BankAccount(3,0);
		BankAccount fifth = new BankAccount(4,0);
		BankAccount [] bankAccountPool = {first,second,third,fourt,fifth} ;
	
		
		Transaction[] pool = new Transaction[100];
		
		for (int i=0; i < pool.length ; i++)
			// random Transactions in account 0 to 4 , the sum is between -1000 and 1000
			pool[i] = new Transaction( (int)(5*Math.random()) ,  2000*Math.random() - 1000  ) ;
		
		TransactionPool transactionPool = new TransactionPool(pool);
		
		// 10  new clerks
		Clerk clerk1 = new Clerk(transactionPool,bankAccountPool );
		Clerk clerk2 = new Clerk(transactionPool,bankAccountPool );
		Clerk clerk3 = new Clerk(transactionPool,bankAccountPool );
		Clerk clerk4 = new Clerk(transactionPool,bankAccountPool );
		Clerk clerk5 = new Clerk(transactionPool,bankAccountPool );
		Clerk clerk6 = new Clerk(transactionPool,bankAccountPool );
		Clerk clerk7 = new Clerk(transactionPool,bankAccountPool );
		Clerk clerk8 = new Clerk(transactionPool,bankAccountPool );
		Clerk clerk9 = new Clerk(transactionPool,bankAccountPool );
		Clerk clerk10 = new Clerk(transactionPool,bankAccountPool );
		
		// starting the threads 
		clerk1.start();
		clerk2.start();
		clerk3.start();
		clerk4.start();
		clerk5.start();
		clerk6.start();
		clerk7.start();
		clerk8.start();
		clerk9.start();
		clerk10.start();
		
	}

}
