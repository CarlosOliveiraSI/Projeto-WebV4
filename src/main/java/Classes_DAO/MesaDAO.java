/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes_DAO;

/**
 *
 * @author carlo
 */
import Classes_de_Entidade.Mesa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MesaDAO {

    private Connection connection;

    public MesaDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Mesa mesa) throws SQLException {
        String sql = "INSERT INTO Mesa (numero, capacidade, disponivel) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, mesa.getNumero());
            stmt.setInt(2, mesa.getCapacidade());
            stmt.setBoolean(3, mesa.isDisponivel());
            stmt.executeUpdate();
        }
    }

    public Mesa buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Mesa WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Mesa mesa = new Mesa();
                mesa.setId(rs.getInt("id"));
                mesa.setNumero(rs.getInt("numero"));
                mesa.setCapacidade(rs.getInt("capacidade"));
                mesa.setDisponivel(rs.getBoolean("disponivel"));
                return mesa;
            }
        }
        return null;
    }

    public void atualizar(Mesa mesa) throws SQLException {
        String sql = "UPDATE Mesa SET numero = ?, capacidade = ?, disponivel = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, mesa.getNumero());
            stmt.setInt(2, mesa.getCapacidade());
            stmt.setBoolean(3, mesa.isDisponivel());
            stmt.setInt(4, mesa.getId());
            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM Mesa WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Mesa> listar() throws SQLException {
        List<Mesa> mesas = new ArrayList<>();
        String sql = "SELECT * FROM Mesa";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Mesa mesa = new Mesa();
                mesa.setId(rs.getInt("id"));
                mesa.setNumero(rs.getInt("numero"));
                mesa.setCapacidade(rs.getInt("capacidade"));
                mesa.setDisponivel(rs.getBoolean("disponivel"));
                mesas.add(mesa);
            }
        }
        return mesas;
    }
}
