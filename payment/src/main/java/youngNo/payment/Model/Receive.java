package youngNo.payment.Model;

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
