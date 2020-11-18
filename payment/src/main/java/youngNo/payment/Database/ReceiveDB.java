package youngNo.payment.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class ReceiveDB extends ModelDB{
	private String createDate;
	private int orderId;
	
	public ReceiveDB(int id, String createDate, int orderId) {
		this.createDate = createDate;
		this.orderId = orderId;
	}
	
	@SuppressWarnings("finally")
	public static HashMap<Integer, ReceiveDB> findAll(){
		Connection conn = null;
		HashMap<Integer, ReceiveDB> receives = new HashMap<Integer, ReceiveDB>();
		try {
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM Receives");
			while (result.next()) {
				receives.put(result.getInt("id"), new ReceiveDB(result.getInt("id"), result.getString("create_date"), result.getInt("order_id")));
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
