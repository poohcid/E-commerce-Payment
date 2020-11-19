package youngNo.payment.ModelForm;

public class PromotionForm {
	private int order_id;
	private int promotion_id;
	
	public PromotionForm() {
		
	}
	
	public PromotionForm(int orderId, int promotionId) {
		this.setOrder_id(orderId);
		this.setPromotion_id(promotionId);
	}

	public int getPromotion_id() {
		return promotion_id;
	}

	public void setPromotion_id(int promotion_id) {
		this.promotion_id = promotion_id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
}
