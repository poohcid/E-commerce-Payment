
public class PaymentLog {
	private static int id;
	private String created_date;
	private int wallet_id;
	private double amount;
	
	
	public PaymentLog(String created_date, int wallet_id, double amount) {
		this.setWallet_id(wallet_id);
		this.setCreated_date(created_date);
		this.setAmount(amount);
		countId();
	}
	
	private static void countId() {
		PaymentLog.id++;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getWallet_id() {
		return wallet_id;
	}

	public void setWallet_id(int wallet_id) {
		this.wallet_id = wallet_id;
	}
}
