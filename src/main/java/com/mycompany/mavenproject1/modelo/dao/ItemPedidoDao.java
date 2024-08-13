/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.modelo.dao;

/**
 *
 * @author rulli
 */

import com.mycompany.mavenproject1.modelo.dao.GenericoDAO;
import com.mycompany.mavenproject1.modelo.entidade.ItemPedido;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ItemPedidoDao extends GenericoDAO<ItemPedido> {

    public void salvar(ItemPedido ip) {
        String insert = "INSERT INTO ItemPedido (pedido_id, prato_id, quantidade, preco) VALUES (?, ?, ?, ?)";
        save(insert, ip.getPedidoId(), ip.getPratoId(), ip.getQuantidade(), ip.getPreco());
    }

    public void alterar(ItemPedido ip) {
        String update = "UPDATE ItemPedido SET pedido_id = ?, prato_id = ?, quantidade = ?, preco = ? WHERE id = ?";
        save(update, ip.getPedidoId(), ip.getPratoId(), ip.getQuantidade(), ip.getPreco(), ip.getId());
    }

    public void excluir(ItemPedido ip) {
        String delete = "DELETE FROM ItemPedido WHERE id = ?";
        save(delete, ip.getId());
    }

    public ItemPedido buscarPorId(int id) {
        String select = "SELECT * FROM ItemPedido WHERE id = ?";
        return buscarPorId(select, new ItemPedidoRowMapper(), id);
    }

    public List<ItemPedido> buscarTodos() {
        String select = "SELECT * FROM ItemPedido";
        return buscarTodos(select, new ItemPedidoRowMapper());
    }

    public static class ItemPedidoRowMapper implements RowMapper<ItemPedido> {
        @Override
        public ItemPedido mapRow(ResultSet rs) throws SQLException {
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setId(rs.getInt("id"));
            itemPedido.setPedidoId(rs.getInt("pedido_id"));
            itemPedido.setPratoId(rs.getInt("prato_id"));
            itemPedido.setQuantidade(rs.getInt("quantidade"));
            itemPedido.setPreco(rs.getDouble("preco"));
            return itemPedido;
        }
    }
}

