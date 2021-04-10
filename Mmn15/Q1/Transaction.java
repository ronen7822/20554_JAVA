package Mmn15Q1;

// Transaction in bank account
public class Transaction {
	private long accountId;
	private double sum;
	public Transaction(long accountId , double sum) {
		this.accountId = accountId;
		this.sum = sum;		
	}
	
	public long getId() {
		return accountId;
	}
	
	public double getSum() {
		return sum;
	}
}
