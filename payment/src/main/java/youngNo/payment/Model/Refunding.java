package youngNo.payment.Model;
public class Refunding {
	private static int id;
	private String created_date;
	private int receive_id;
	
	
	public Refunding(String created_date, int receive_id) {
		this.setReceive_id(receive_id);
		this.setCreated_date(created_date);
		countId();
	}
	
	private static void countId() {
		Refunding.id++;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public int getReceive_id() {
		return receive_id;
	}

	public void setReceive_id(int receive_id) {
		this.receive_id = receive_id;
	}
}
