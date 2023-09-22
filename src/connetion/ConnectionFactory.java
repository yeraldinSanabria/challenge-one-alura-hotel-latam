package connetion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection creaConexion() throws SQLException {

		Connection conexion = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/hotelalura?useTimezone=true&serverTimezone=UTC", "root", "yeral.");
	
		return conexion;
	}

}
