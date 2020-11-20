package youngNo.payment.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Receive extends Model{
	private int id;
	private String created_date;
	private int order_id;
	private ArrayList<Promotion> promotions;
	
	public Receive(int id, String created_date, int order_id) {
		this.id = id;
		this.setOrder_id(order_id);
		this.created_date = created_date;
		this.promotions = new ArrayList<Promotion>();
	}
	
	public Receive(int id, int order_id) {
		this(id, null, order_id);
	}
	
	@SuppressWarnings("finally")
	public static Receive findByOrderId(int orderId) {
		Connection conn = null;
		Receive receive = null;
		String whereQuery = String.format(" order_id=%d ", orderId);
		try {
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			//ResultSet result = stmt.executeQuery("SELECT * FROM Receive "
			//		+ "WHERE "+whereQuery);
			ResultSet result = stmt.executeQuery("SELECT * FROM Receive "
					+ "INNER JOIN PromotionUsing ON PromotionUsing.receive_id=Receive.id "
					+ " WHERE "+whereQuery);
			if (!result.next()) {
				stmt.executeUpdate("INSERT INTO Receive (order_id) "
						+ String.format(" VALUES (%d) ", orderId));
				result = stmt.executeQuery("SELECT * "
						+ " FROM Receive "
						+ "INNER JOIN PromotionUsing ON PromotionUsing.receive_id=Receive.id "
						+ " WHERE "+whereQuery);
			}
			receive = new Receive(result.getInt("id"), result.getString("create_date"), result.getInt("order_id"));
			do {
				receive.getPromotions().add(new Promotion(1, result.getInt("promotion_id"), result.getInt("receive_id")));
			}while (result.next());
			conn.close();
			return receive;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			return receive;
		}
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public String getCreated_date() {
		return created_date;
	}

	public int getId() {
		return id;
	}

	@Override
	protected void saveHandle(Statement stmt) throws SQLException {
		
	}

	public ArrayList<Promotion> getPromotions() {
		return promotions;
	}
}
