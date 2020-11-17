package youngNo.payment.Database;

public class ModelDB {
	protected static String url="jdbc:sqlite:payment.db";
	protected int id;
	
	public ModelDB(int id) {
		this.id = id;
	}
}
