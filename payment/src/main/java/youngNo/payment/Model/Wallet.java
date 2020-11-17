package youngNo.payment.Model;
public class Wallet {
	private static int id;
	private double balance;
	private int user_id;
	private String idWallet;
	
	
	public Wallet(double balance, int user_id) {
		this.setUser_id(user_id);
		this.setBalance(balance);
		countId();
		setIdWallet(Integer.toString(id));
	}
	
	private static void countId() {
		Wallet.id++;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getIdWallet() {
		return idWallet;
	}

	public void setIdWallet(String idWallet) {
		this.idWallet = idWallet;
	}
	
}