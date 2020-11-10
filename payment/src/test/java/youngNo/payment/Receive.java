
public class Receive {
	private static int id;
	private String created_date;
	private int order_id;
	
	
	public Receive(String created_date, int order_id) {
		this.setOrder_id(order_id);
		this.setCreated_date(created_date);
		countId();
	}
	
	private static void countId() {
		Receive.id++;
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

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
}
