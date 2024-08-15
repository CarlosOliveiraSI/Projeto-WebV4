/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes_DAO;

/**
 *
 * @author carlo
 */
import Classes_de_Entidade.ItemPedido;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemPedidoDAO {

    private Connection connection;

    public ItemPedidoDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(ItemPedido itemPedido) throws SQLException {
        String sql = "INSERT INTO ItemPedido (pedido_id, prato_id, quantidade, preco) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, itemPedido.getPedidoId());
            stmt.setInt(2, itemPedido.getPratoId());
            stmt.setInt(3, itemPedido.getQuantidade());
            stmt.setDouble(4, itemPedido.getPreco());
            stmt.executeUpdate();
        }
    }

    public ItemPedido buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM ItemPedido WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ItemPedido itemPedido = new ItemPedido();
                itemPedido.setId(rs.getInt("id"));
                itemPedido.setPedidoId(rs.getInt("pedido_id"));
                itemPedido.setPratoId(rs.getInt("prato_id"));
                itemPedido.setQuantidade(rs.getInt("quantidade"));
                itemPedido.setPreco(rs.getDouble("preco"));
                return itemPedido;
            }
        }
        return null;
    }

    public void atualizar(ItemPedido itemPedido) throws SQLException {
        String sql = "UPDATE ItemPedido SET pedido_id = ?, prato_id = ?, quantidade = ?, preco = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, itemPedido.getPedidoId());
            stmt.setInt(2, itemPedido.getPratoId());
            stmt.setInt(3, itemPedido.getQuantidade());
            stmt.setDouble(4, itemPedido.getPreco());
            stmt.setInt(5, itemPedido.getId());
            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM ItemPedido WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<ItemPedido> listar() throws SQLException {
        List<ItemPedido> itensPedido = new ArrayList<>();
        String sql = "SELECT * FROM ItemPedido";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ItemPedido itemPedido = new ItemPedido();
                itemPedido.setId(rs.getInt("id"));
                itemPedido.setPedidoId(rs.getInt("pedido_id"));
                itemPedido.setPratoId(rs.getInt("prato_id"));
                itemPedido.setQuantidade(rs.getInt("quantidade"));
                itemPedido.setPreco(rs.getDouble("preco"));
                itensPedido.add(itemPedido);
            }
        }
        return itensPedido;
    }
}
