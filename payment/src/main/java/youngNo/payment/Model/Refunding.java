package youngNo.payment.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Refunding extends Model{
	private int id;
	private String created_date;
	private int receive_id;
	
	public Refunding() {
		
	}
	/*
	private Refunding(int id, int receive_id) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		this.created_date = formatter.format(date).toString();
		this.receive_id = receive_id;
		this.id = id;
	}
	*/
	
	private Refunding(int id, String created_date, int receive_id) {
		this.id = id;
		this.created_date = created_date;
		this.receive_id = receive_id;
	}
	
	public Refunding(Receive receive) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		this.created_date = formatter.format(date).toString();
		if (!(receive.getCreated_date() == null))
			this.receive_id = receive.getId();
		else
			this.receive_id = 0;
	}
	
	@SuppressWarnings("finally")
	public static ArrayList<Refunding> getRefundingByReceive(ArrayList<Receive> receives){
		Connection conn = null;
		ArrayList<Refunding> refundings = new ArrayList<Refunding>();
		ArrayList<Integer> receives_id = new ArrayList<Integer>();
		for (Receive receive: receives) {
			if (!(receive.getCreated_date() == null)) {
				receives_id.add(receive.getId());
			}
		}
		try {
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			String inQuery = receives_id.toString();
			inQuery = inQuery.substring(1, inQuery.length()-1);
			ResultSet result = stmt.executeQuery(" SELECT * FROM Refunding "
					+ String.format(" WHERE receive_id IN (%s) ", inQuery));
			while (result.next()) {
				refundings.add(new Refunding(
						result.getInt("id"),
						result.getString("create_date"),
						result.getInt("receive_id")
						));
			}
			conn.close();
			return refundings;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			return refundings;
		}
	}

	public String getCreated_date() {
		return created_date;
	}

	public int getReceive_id() {
		return receive_id;
	}

	public int getId() {
		return id;
	}

	@Override
	protected void saveHandle(Statement stmt) throws SQLException {
		if (this.receive_id != 0) {
			stmt.executeUpdate(" INSERT INTO Refunding (receive_id,create_date) "
					+ String.format(" VALUES (%d,'%s') ", this.receive_id, this.created_date));
		}
	}
}
