/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.modelo.dao;

/**
 *
 * @author rulli
 */

import com.mycompany.mavenproject1.modelo.entidade.Pagamento;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PagamentoDao extends GenericoDAO<Pagamento> {

    public void salvar(Pagamento p) {
        String insert = "INSERT INTO Pagamento (pedido_id, metodo, valor) VALUES (?, ?, ?)";
        save(insert, p.getPedidoId(), p.getMetodo(), p.getValor());
    }

    public void alterar(Pagamento p) {
        String update = "UPDATE Pagamento SET pedido_id = ?, metodo = ?, valor = ? WHERE id = ?";
        save(update, p.getPedidoId(), p.getMetodo(), p.getValor(), p.getId());
    }

    public void excluir(Pagamento p) {
        String delete = "DELETE FROM Pagamento WHERE id = ?";
        save(delete, p.getId());
    }

    public Pagamento buscarPorId(int id) {
        String select = "SELECT * FROM Pagamento WHERE id = ?";
        return buscarPorId(select, new PagamentoRowMapper(), id);
    }

    public List<Pagamento> buscarTodos() {
        String select = "SELECT * FROM Pagamento";
        return buscarTodos(select, new PagamentoRowMapper());
    }

    public static class PagamentoRowMapper implements RowMapper<Pagamento> {
        @Override
        public Pagamento mapRow(ResultSet rs) throws SQLException {
            Pagamento pagamento = new Pagamento();
            pagamento.setId(rs.getInt("id"));
            pagamento.setPedidoId(rs.getInt("pedido_id"));
            pagamento.setMetodo(rs.getString("metodo"));
            pagamento.setValor(rs.getDouble("valor"));
            return pagamento;
        }
    }
}

