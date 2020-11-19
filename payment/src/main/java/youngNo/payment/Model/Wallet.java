package youngNo.payment.Model;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Wallet extends Model{
	private double balance;
	private int user_id;
	private int id;
	
	public Wallet() {
		
	}
	
	public Wallet(int id, double balance, int user_id) {
		this.setUser_id(user_id);
		this.balance = balance;
		this.id = id;
	}
	
	@Override
	protected void saveHandle(Statement stmt) throws SQLException {
		stmt.executeUpdate("UPDATE Wallets "
				+ String.format("SET balance = %.2f ", this.balance)
				+ String.format("WHERE user_id=%d AND id=%d ", user_id, id));
	}

	public double getBalance() {
		return balance;
	}
	
	public void addBalance(double balance) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		PaymentLog paymentLog = new PaymentLog(this.id, formatter.format(date).toString(), this.id, balance);
		paymentLog.save();
		System.out.println();
		this.balance = this.balance + balance;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getId() {
		return id;
	}
	
}
