package youngNo.payment.ModelForm;

public class WalletForm {
	private int balance;
	
	public WalletForm() {
		
	}
	
	public WalletForm(int balance) {
		this.balance = balance;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
}
