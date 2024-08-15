/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes_DAO;

/**
 *
 * @author carlo
 */
import Classes_de_Entidade.Pedido;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    private Connection connection;

    public PedidoDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Pedido pedido) throws SQLException {
        String sql = "INSERT INTO Pedido (cliente_id, mesa_id, data, status, total) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pedido.getClienteId());
            stmt.setInt(2, pedido.getMesaId());
            stmt.setDate(3, pedido.getData());
            stmt.setString(4, pedido.getStatus());
            stmt.setDouble(5, pedido.getTotal());
            stmt.executeUpdate();
        }
    }

    public Pedido buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Pedido WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setClienteId(rs.getInt("cliente_id"));
                pedido.setMesaId(rs.getInt("mesa_id"));
                pedido.setData(rs.getDate("data"));
                pedido.setStatus(rs.getString("status"));
                pedido.setTotal(rs.getDouble("total"));
                return pedido;
            }
        }
        return null;
    }

    public void atualizar(Pedido pedido) throws SQLException {
        String sql = "UPDATE Pedido SET cliente_id = ?, mesa_id = ?, data = ?, status = ?, total = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pedido.getClienteId());
            stmt.setInt(2, pedido.getMesaId());
            stmt.setDate(3, pedido.getData());
            stmt.setString(4, pedido.getStatus());
            stmt.setDouble(5, pedido.getTotal());
            stmt.setInt(6, pedido.getId());
            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM Pedido WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Pedido> listar() throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM Pedido";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setClienteId(rs.getInt("cliente_id"));
                pedido.setMesaId(rs.getInt("mesa_id"));
                pedido.setData(rs.getDate("data"));
                pedido.setStatus(rs.getString("status"));
                pedido.setTotal(rs.getDouble("total"));
                pedidos.add(pedido);
            }
        }
        return pedidos;
    }
}
