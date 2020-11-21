package youngNo.payment.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Model {
	protected static final String url="jdbc:sqlite:payment.db";
	public void save() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			saveHandle(stmt);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected abstract void saveHandle(Statement stmt) throws SQLException;
}
