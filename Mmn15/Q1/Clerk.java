package Mmn15Q1;


// Clerk in a bank, can perform transactions
public class Clerk extends Thread {
	
	private int sleepTime; // time to sleep after committing successful transaction
	private BankAccount [] accountPool ;	
	private TransactionPool transactionPool;
	
	public Clerk(TransactionPool transactionpool, BankAccount [] accountPool) {		
		this.transactionPool = transactionpool;
		this.accountPool = accountPool;		
		sleepTime = (int)(100*Math.random()) ;
	}
	
	// gets transaction from the transaction pool, and executes it
	public void run() {
		// while there are more transactions
		Transaction currTransaction;
		while ( (currTransaction = transactionPool.getOneTransaction() )!= null ) {
			
			long currId = currTransaction.getId();
			double currSum = currTransaction.getSum();
			// search the bank account in order to executes the transaction in the account
			BankAccount commitAction = this.searchBankAccount ( currId );
			
			System.out.println("trying to commit transaction in sum of: " + currSum + commitAction );
			
			commitAction.transaction(currSum, this);
			// go to sleep for random time up to 100 mili seconds
			try {
				Thread.sleep(sleepTime);
			} 
			catch (InterruptedException e)	{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	// return bank account from the data structure that saves the bank accounts
	public BankAccount searchBankAccount (long accountId) {
		for (int i=0; i < accountPool.length ; i++)
			if (accountId == accountPool[i].getId())
				return accountPool[i];
		return null;
		
	}

}
