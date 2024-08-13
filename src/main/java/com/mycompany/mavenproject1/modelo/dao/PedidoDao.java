package com.mycompany.mavenproject1.modelo.dao;

import Banco.Database;
import com.mycompany.mavenproject1.modelo.entidade.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDao extends GenericoDAO<Pedido> {

    public void salvar(Pedido p) {
        String insert = "INSERT INTO Pedido (cliente_id, data, total) VALUES (?, ?, ?)";
        save(insert, p.getCodigoCliente(), p.getDataPedido(), p.getTotalPedido());
    }

    public void alterar(Pedido p) {
        String update = "UPDATE Pedido SET cliente_id = ?, data = ?, total = ? WHERE id = ?";
        save(update, p.getCodigoCliente(), p.getDataPedido(), p.getTotalPedido(), p.getCodigoPedido());
    }

    public void excluir(Pedido p) {
        String delete = "DELETE FROM Pedido WHERE id = ?";
        save(delete, p.getCodigoPedido());
    }

    public Pedido buscarPorId(int id) {
        String select = "SELECT * FROM Pedido WHERE id = ?";
        Pedido pedido = null;

        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(select)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                pedido = new Pedido();
                pedido.setCodigoPedido(rs.getInt("id"));
                pedido.setCodigoCliente(rs.getInt("cliente_id"));
                pedido.setDataPedido(rs.getString("data"));
                pedido.setCodigoProduto(rs.getInt("produto_id"));
                pedido.setQuantidade(rs.getInt("quantidade"));
                pedido.setTotalPedido(rs.getDouble("total"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pedido;
    }

    public List<Pedido> buscarTodos() {
        String select = "SELECT * FROM Pedido";
        List<Pedido> pedidos = new ArrayList<>();

        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(select);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setCodigoPedido(rs.getInt("id"));
                pedido.setCodigoCliente(rs.getInt("cliente_id"));
                pedido.setDataPedido(rs.getString("data"));
                pedido.setCodigoProduto(rs.getInt("produto_id"));
                pedido.setQuantidade(rs.getInt("quantidade"));
                pedido.setTotalPedido(rs.getDouble("total"));

                pedidos.add(pedido);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pedidos;
    }
}
