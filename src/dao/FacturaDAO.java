package dao;

import model.Factura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO {

    public void agregarFactura(Factura factura) {
        String sql = "INSERT INTO factura (id_cliente, total, fecha_factura) VALUES (?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, factura.getIdCliente());
            preparedStatement.setDouble(2, factura.getTotal());
            preparedStatement.setDate(3, java.sql.Date.valueOf(factura.getFechaFactura()));
            preparedStatement.executeUpdate();

            System.out.println("Factura agregada exitosamente");
        } catch (SQLException e) {
            System.out.println("Error al agregar factura");
            e.printStackTrace();
        }
    }

    public List<Factura> obtenerFacturas() {
        List<Factura> facturas = new ArrayList<>();
        String sql = "SELECT * FROM factura";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Factura factura = new Factura();
                factura.setIdFactura(resultSet.getInt("id_factura"));
                factura.setFechaFactura(resultSet.getDate("fecha_factura").toString());
                factura.setTotal(resultSet.getDouble("total"));
                factura.setIdCliente(resultSet.getInt("id_cliente"));
                facturas.add(factura);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener facturas");
            e.printStackTrace();
        }
        return facturas;
    }
}
