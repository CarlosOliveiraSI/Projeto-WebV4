/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes_DAO;

/**
 *
 * @author carlo
 */
import Classes_de_Entidade.Pagamento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO {

    private Connection connection;

    public PagamentoDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Pagamento pagamento) throws SQLException {
        String sql = "INSERT INTO Pagamento (pedido_id, tipo, valor, data) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pagamento.getPedidoId());
            stmt.setString(2, pagamento.getTipo());
            stmt.setDouble(3, pagamento.getValor());
            stmt.setDate(4, pagamento.getData());
            stmt.executeUpdate();
        }
    }

    public Pagamento buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Pagamento WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Pagamento pagamento = new Pagamento();
                pagamento.setId(rs.getInt("id"));
                pagamento.setPedidoId(rs.getInt("pedido_id"));
                pagamento.setTipo(rs.getString("tipo"));
                pagamento.setValor(rs.getDouble("valor"));
                pagamento.setData(rs.getDate("data"));
                return pagamento;
            }
        }
        return null;
    }

    public void atualizar(Pagamento pagamento) throws SQLException {
        String sql = "UPDATE Pagamento SET pedido_id = ?, tipo = ?, valor = ?, data = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pagamento.getPedidoId());
            stmt.setString(2, pagamento.getTipo());
            stmt.setDouble(3, pagamento.getValor());
            stmt.setDate(4, pagamento.getData());
            stmt.setInt(5, pagamento.getId());
            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM Pagamento WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Pagamento> listar() throws SQLException {
        List<Pagamento> pagamentos = new ArrayList<>();
        String sql = "SELECT * FROM Pagamento";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Pagamento pagamento = new Pagamento();
                pagamento.setId(rs.getInt("id"));
                pagamento.setPedidoId(rs.getInt("pedido_id"));
                pagamento.setTipo(rs.getString("tipo"));
                pagamento.setValor(rs.getDouble("valor"));
                pagamento.setData(rs.getDate("data"));
                pagamentos.add(pagamento);
            }
        }
        return pagamentos;
    }
}
