package youngNo.payment.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import youngNo.payment.Model.*;

public class WalletDB extends ModelDB {
	private double balance;
	private int userId;
	
	public WalletDB() {
	}
	
	@SuppressWarnings("finally")
	public Wallet findOne(int userId){
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
