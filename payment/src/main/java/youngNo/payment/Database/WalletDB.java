package youngNo.payment.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class WalletDB extends ModelDB {
	private double balance;
	private int userId;

	public WalletDB(int id, double balance, int userId) {
		super(id);
		this.setBalance(balance);
		this.setUserId(userId);
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("finally")
	public static WalletDB findOne(int userId){
		Connection conn = null;
		WalletDB wallet = null;
		try {
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM Wallets WHERE user_id="+userId);
			if (!result.next()) {
				int id = stmt.executeUpdate("INSERT INTO Wallets (balance, user_id)"
						+ String.format("VALUES (0.0, %d)", userId));
				wallet = new WalletDB(id, 0.0, userId);
			}
			else {
				wallet = new WalletDB(result.getInt("id"), result.getFloat("balance"), result.getInt("user_id"));
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

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
