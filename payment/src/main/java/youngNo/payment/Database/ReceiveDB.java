package youngNo.payment.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import youngNo.payment.Model.*;

public class ReceiveDB extends ModelDB{
	private String createDate;
	private int orderId;
	
	public ReceiveDB() {
		
	}
	
	public ReceiveDB(int id, String createDate, int orderId) {
		this.createDate = createDate;
		this.orderId = orderId;
	}
	
	@SuppressWarnings("finally")
	public HashMap<Integer, Receive> findAll(){
		Connection conn = null;
		HashMap<Integer, Receive> receives = new HashMap<Integer, Receive>();
		try {
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM Receive");
			while (result.next()) {
				receives.put(result.getInt("id"), new Receive(result.getInt("id"), result.getString("create_date"), result.getInt("order_id")));
			}
			conn.close();
			return receives;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return receives;
		}
	}
	
	@SuppressWarnings("finally")
	public Receive findByOrderId(int orderId) {
		Connection conn = null;
		Receive receive = null;
		String whereQuery = String.format(" order_id=%d ", orderId);
		try {
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM Receive "
					+ "WHERE "+whereQuery);
			if (!result.next()) {
				stmt.executeUpdate("INSERT INTO Receive (order_id) "
						+ String.format(" VALUES (%d) ", orderId));
				result = stmt.executeQuery("SELECT * FROM Receive "
						+ "WHERE "+whereQuery);
			}
			receive = new Receive(result.getInt("id"), result.getString("create_date"), result.getInt("order_id"));
			conn.close();
			return receive;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			return receive;
		}
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	
	//public static HashMap<>
}
