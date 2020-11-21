package youngNo.payment.ModelForm;

public class PaymentForm {
	private int order_id;
	
	public PaymentForm() {
		
	}
	
	public PaymentForm(int order_id) {
		this.order_id = order_id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
}
