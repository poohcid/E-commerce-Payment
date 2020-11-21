package youngNo.payment.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
			ResultSet result = stmt.executeQuery("SELECT * FROM Receive "
					+ " LEFT OUTER JOIN PromotionUsing ON PromotionUsing.receive_id=Receive.id "
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
	
	public static ArrayList<Receive> findAllByOrderId(ArrayList<Integer> orders_id) {
		Connection conn = null;
		ArrayList<Receive> receive = null;
		/*
		String whereQuery = String.format(" order_id=%d ", orderId);
		try {
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM Receive "
					+ " LEFT OUTER JOIN PromotionUsing ON PromotionUsing.receive_id=Receive.id "
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
		*/
		
		return null;
	}
	
	public void addPromotion(int promotion_id) {
		Connection conn = null;
		if (created_date != null) {
			return;
		}
		try {
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO PromotionUsing (promotion_id, receive_id) "
					+ String.format(" VALUES (%d, %d) ",promotion_id, id));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void removePromotion(int promotion_id) {
		Connection conn = null;
		if (created_date != null) {
			return;
		}
		try {
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM PromotionUsing"
					+ String.format(" WHERE receive_id=%d AND promotion_id=%d ", this.id, promotion_id));
			if (result.next()) {
				stmt.executeUpdate("DELETE FROM PromotionUsing "
						+ String.format(" WHERE id=%d", result.getInt("id")));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void confirm () {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		this.created_date = formatter.format(date).toString();
		this.save();
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
		stmt.executeUpdate("UPDATE Receive "
				+ String.format(" SET create_date='%s' ", this.created_date)
				+ String.format(" WHERE id=%d ", id));
	}

	public ArrayList<Promotion> getPromotions() {
		return promotions;
	}
}
