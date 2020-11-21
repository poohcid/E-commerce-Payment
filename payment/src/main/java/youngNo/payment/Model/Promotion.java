package youngNo.payment.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import youngNo.payment.ModelForm.PromotionForm;

public class Promotion extends Model{
	
	private int promotion_id;
	private int receive_id;
	
	public Promotion(int id, int promotion_id, int receive_id) {
		this.setReceive_id(receive_id);
		this.setPromotion_id(promotion_id);
	}
	
	public static void addPromotion(int promotion_id, int receive_id) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO PromotionUsing (promotion_id, receive_id) "
					+ String.format(" VALUES (%d, %d) ",promotion_id, receive_id));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void removePromotion(int promotion_id, Receive receive) {
		Promotion promotionRemove;
		for (Promotion promoton : receive.getPromotions()) {
			if (promoton.getPromotion_id() == promotion_id) {
				promotionRemove = promoton;
				break;
			}
		}
		/*
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO PromotionUsing (promotion_id, receive_id) "
					+ String.format(" VALUES (%d, %d) ",promotion_id, receive.getId()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}

	public int getPromotion_id() {
		return promotion_id;
	}

	public void setPromotion_id(int promotion_id) {
		this.promotion_id = promotion_id;
	}

	public int getReceive_id() {
		return receive_id;
	}

	public void setReceive_id(int receive_id) {
		this.receive_id = receive_id;
	}

	@Override
	protected void saveHandle(Statement stmt) throws SQLException {
		
	}
}
