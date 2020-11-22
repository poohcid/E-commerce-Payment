package youngNo.payment.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PaymentLog extends Model{
	private int id;
	private String created_date;
	private int wallet_id;
	private double amount;
	private String type;
	
	public PaymentLog(int id, String created_date, int wallet_id, double amount, String type) {
		this.id = id;
		this.wallet_id = wallet_id;
		this.created_date = created_date;
		this.amount = amount;
		this.type = type;
	}
	
	@SuppressWarnings("finally")
	public static ArrayList<PaymentLog> findAll(int wallet_id){
		Connection conn = null;
		ArrayList<PaymentLog> paymentLogs = new ArrayList<PaymentLog>();
		try {
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(" SELECT * FROM PaymentLog "
					+ String.format(" WHERE wallet_id=%d ", wallet_id));
			while (result.next()) {
				paymentLogs.add(new PaymentLog(result.getInt("id"),
						result.getString("create_date"),
						result.getInt("wallet_id"),
						result.getFloat("amount"),
						result.getString("type")));
			}
			conn.close();
			return paymentLogs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			return paymentLogs;
		}
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
		stmt.executeUpdate("INSERT INTO PaymentLog (create_date, wallet_id, amount, type) "
				+ String.format(" VALUES ('%s', %d, %.2f, '%s') ", this.created_date, this.wallet_id, this.amount, this.type));
	}

	public String getType() {
		return type;
	}
}
