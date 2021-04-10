package Mmn15Q1;


public class BankAccount {
	// this class represent bank account
		private static int countTransactions = 0;
		private long accountId ;
		private double  balance;
		private boolean enoughForTrans;
		
		//builder for the class
		BankAccount(long accountId , double balance) {
			this.accountId = accountId;
			this.balance = balance;
		}
		
		public long getId() {
			return accountId;
		}
		
		public synchronized double getBalance() {
			return balance;
		}
		
		// commit a synchronized transaction in the bank account
		public synchronized  void  transaction(double sum, Clerk clerck)  {
			
			// if we have enough money for the transaction set the var to true, otherwise to false		
			while (! (enoughForTrans = balance + sum >= 0 ? true : false ) ) {
				System.out.println("not enogh for transaction in account:"+ accountId + " asked: " +sum+" have: "+balance);
				try {
					wait();
				} 
				catch (InterruptedException e) {					
					e.printStackTrace();
				}
			}
			// commit the transaction
			balance += sum;
			System.out.println("transaction number: " + countTransactions++ + " completed"+ this+ " balance before transaction: "+(balance-sum));
			notifyAll(); // notify all the clerks we ended the transaction
		}
				
		public  String toString () {
			String str = " account id :" + accountId + " the balance of the account is :" + balance ;
			return str ;
		}

	}


