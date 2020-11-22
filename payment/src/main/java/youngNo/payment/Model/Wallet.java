package youngNo.payment.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
	
	@SuppressWarnings("finally")
	public static Wallet findOne(int userId) {
		Connection conn = null;
		Wallet wallet = null;
		try {
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM Wallet WHERE user_id="+userId);
			if (!result.next()) {
				stmt.executeUpdate("INSERT INTO Wallet (balance, user_id)"
						+ String.format("VALUES (0.0, %d)", userId));
				result = stmt.executeQuery("SELECT * FROM Wallet WHERE user_id="+userId);
				
				wallet = new Wallet(result.getInt("id"), 0.0, userId);
			}
			else {
				wallet = new Wallet(result.getInt("id"), result.getFloat("balance"), result.getInt("user_id"));
			}
			conn.close();
			return wallet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return wallet;
		}
	}
	
	@Override
	protected void saveHandle(Statement stmt) throws SQLException {
		stmt.executeUpdate("UPDATE Wallet "
				+ String.format("SET balance = %.2f ", this.balance)
				+ String.format("WHERE user_id=%d AND id=%d ", user_id, id));
	}

	public double getBalance() {
		return balance;
	}
	
	public void addBalance(double balance) {
		PaymentLog paymentLog = new PaymentLog(this.id, this.id, balance, "top up");
		this.balance = this.balance + balance;
		this.save();
		paymentLog.save();
	}
	
	public boolean pay(double amount) {
		if (this.balance - amount < 0)
			return false;
		this.balance = this.balance - amount;
		PaymentLog paymentLog = new PaymentLog(this.id, this.id, amount, "pay");
		this.save();
		paymentLog.save();
		return true;
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
