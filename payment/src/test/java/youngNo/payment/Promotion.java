package youngNo.payment;
public class Promotion {
	private static int id;
	private int promotion_id;
	private int receive_id;
	
	
	public Promotion(int promotion_id, int receive_id) {
		this.setReceive_id(receive_id);
		this.setPromotion_id(promotion_id);
		countId();
	}
	
	private static void countId() {
		Promotion.id++;
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
}
