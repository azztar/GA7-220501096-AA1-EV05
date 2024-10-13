package dao;

import model.Medicamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoDAO {

    public void agregarMedicamento(Medicamento medicamento) {
        String sql = "INSERT INTO medicamentos (nombre_medicamento, descripcion, precio, id_proveedor) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, medicamento.getNombreMedicamento());
            preparedStatement.setString(2, medicamento.getDescripcion());
            preparedStatement.setDouble(3, medicamento.getPrecio());
            preparedStatement.setInt(4, medicamento.getIdProveedor());
            preparedStatement.executeUpdate();

            System.out.println("Medicamento agregado exitosamente");
        } catch (SQLException e) {
            System.out.println("Error al agregar medicamento");
            e.printStackTrace();
        }
    }

    public List<Medicamento> obtenerMedicamentos() {
        List<Medicamento> medicamentos = new ArrayList<>();
        String sql = "SELECT * FROM medicamentos";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Medicamento medicamento = new Medicamento();
                medicamento.setIdMedicamento(resultSet.getInt("id_medicamento"));
                medicamento.setNombreMedicamento(resultSet.getString("nombre_medicamento"));
                medicamento.setDescripcion(resultSet.getString("descripcion"));
                medicamento.setPrecio(resultSet.getDouble("precio"));
                medicamento.setIdProveedor(resultSet.getInt("id_proveedor"));
                medicamentos.add(medicamento);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener medicamentos");
            e.printStackTrace();
        }
        return medicamentos;
    }
}
