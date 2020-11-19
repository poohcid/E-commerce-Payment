package youngNo.payment.Model;

import java.sql.SQLException;
import java.sql.Statement;

public class PaymentLog extends Model{
	private int id;
	private String created_date;
	private int wallet_id;
	private double amount;
	
	
	public PaymentLog(int id, String created_date, int wallet_id, double amount) {
		this.id = id;
		this.wallet_id = wallet_id;
		this.created_date = created_date;
		this.amount = amount;
	}

	public String getCreated_date() {
		return created_date;
	}

	public double getAmount() {
		return amount;
	}

	public int getWallet_id() {
		return wallet_id;
	}

	public int getId() {
		return id;
	}

	@Override
	protected void saveHandle(Statement stmt) throws SQLException {
		System.out.println(this.created_date);
		stmt.executeUpdate("INSERT INTO PaymentLog (create_date, wallet_id, amount) "
				+ String.format(" VALUES ('%s', %d, %.2f) ", this.created_date, this.wallet_id, this.amount));
	}
}
