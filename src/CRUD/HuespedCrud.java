package CRUD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connetion.ConnectionFactory;

public class HuespedCrud {

	   private ConnectionFactory factory;

	    public HuespedCrud() {
	        factory = new ConnectionFactory();
	    }
	    
	    public int insert(String nombre , String apellido,  Date fecha_de_nacimiento, String  nacionalidad, int telefono) {
	        try (Connection conexion = factory.creaConexion()) {
	            String sql = "INSERT INTO reserva (nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono) VALUES (?, ?, ?, ?,?)";
	            PreparedStatement stm = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	            stm.setString(1, nombre);
	            stm.setString(2, apellido);
	            stm.setDate(3, fecha_de_nacimiento);
	            stm.setString(4, nacionalidad);
	            stm.setInt(5, telefono);

	            int affectedRows = stm.executeUpdate();

	            if (affectedRows == 0) {
	                return -1; // No se insertó ninguna fila
	            }

	            try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                    return generatedKeys.getInt(1);
	                } else {
	                    return -1; // No se generaron claves autogeneradas
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return -1; // Ocurrió un error durante la inserción
	        }
	    }
}
