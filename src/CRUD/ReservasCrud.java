package CRUD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connetion.ConnectionFactory;

public class ReservasCrud {

    private ConnectionFactory factory;

    public ReservasCrud() {
        factory = new ConnectionFactory();
    }

    public int insert(Date fechaEntrada, Date fechaSalida, int valor, String formaPago) {
        try (Connection conexion = factory.creaConexion()) {
            String sql = "INSERT INTO reserva (fechaEntrada, fechaSalida, valor, formaDePago) VALUES (?, ?, ?, ?)";
            PreparedStatement stm = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setDate(1, fechaEntrada);
            stm.setDate(2, fechaSalida);
            stm.setInt(3, valor);
            stm.setString(4, formaPago);

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
