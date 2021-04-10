package Mmn15Q1;

public class TransactionPool {
	private Transaction[] pool;
	private int currPtr;
	
	// constructor for the pool of transactions
	public TransactionPool(Transaction[] pool) {
		// ignoring aliasing
		this.pool = pool;
		currPtr = pool.length-1;
	}
	
	// return transaction for the clerk, if there is no more transactions in the pull the method returns null 
	public Transaction getOneTransaction() {
		if (currPtr < 0)
			return null;
		return pool[currPtr--];
	}
}


