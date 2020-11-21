package youngNo.payment.ModelForm;

public class WalletForm {
	private int balance;
	private int user_id;
	
	public WalletForm() {
		
	}
	
	public WalletForm(int balance, int user_id) {
		this.balance = balance;
		this.user_id = user_id;
	}
	
	public int getUser_id() {
		return user_id;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
}
