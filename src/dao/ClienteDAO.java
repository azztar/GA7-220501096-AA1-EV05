package dao;

import model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void agregarCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (nombre_cliente, telefono_cliente, email_cliente, direccion_cliente) VALUES (?, ?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, cliente.getNombre());
            preparedStatement.setString(2, cliente.getTelefono());
            preparedStatement.setString(3, cliente.getEmail());
            preparedStatement.setString(4, cliente.getDireccion());
            preparedStatement.executeUpdate();

            System.out.println("Cliente agregado exitosamente");
        } catch (SQLException e) {
            System.out.println("Error al agregar cliente");
            e.printStackTrace();
        }
    }

    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(resultSet.getInt("id_cliente"));
                cliente.setNombre(resultSet.getString("nombre_cliente"));
                cliente.setTelefono(resultSet.getString("telefono_cliente"));
                cliente.setEmail(resultSet.getString("email_cliente"));
                cliente.setDireccion(resultSet.getString("direccion_cliente"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener clientes");
            e.printStackTrace();
        }
        return clientes;
    }
}
