/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes_DAO;

/**
 *
 * @author carlo
 */
import Classes_de_Entidade.Reserva;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    private Connection connection;

    public ReservaDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Reserva reserva) throws SQLException {
        String sql = "INSERT INTO Reserva (cliente_id, mesa_id, data, hora, status) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, reserva.getClienteId());
            stmt.setInt(2, reserva.getMesaId());
            stmt.setDate(3, reserva.getData());
            stmt.setTime(4, reserva.getHora());
            stmt.setString(5, reserva.getStatus());
            stmt.executeUpdate();
        }
    }

    public Reserva buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Reserva WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setId(rs.getInt("id"));
                reserva.setClienteId(rs.getInt("cliente_id"));
                reserva.setMesaId(rs.getInt("mesa_id"));
                reserva.setData(rs.getDate("data"));
                reserva.setHora(rs.getTime("hora"));
                reserva.setStatus(rs.getString("status"));
                return reserva;
            }
        }
        return null;
    }

    public void atualizar(Reserva reserva) throws SQLException {
        String sql = "UPDATE Reserva SET cliente_id = ?, mesa_id = ?, data = ?, hora = ?, status = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, reserva.getClienteId());
            stmt.setInt(2, reserva.getMesaId());
            stmt.setDate(3, reserva.getData());
            stmt.setTime(4, reserva.getHora());
            stmt.setString(5, reserva.getStatus());
            stmt.setInt(6, reserva.getId());
            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM Reserva WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Reserva> listar() throws SQLException {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM Reserva";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setId(rs.getInt("id"));
                reserva.setClienteId(rs.getInt("cliente_id"));
                reserva.setMesaId(rs.getInt("mesa_id"));
                reserva.setData(rs.getDate("data"));
                reserva.setHora(rs.getTime("hora"));
                reserva.setStatus(rs.getString("status"));
                reservas.add(reserva);
            }
        }
        return reservas;
    }
}
