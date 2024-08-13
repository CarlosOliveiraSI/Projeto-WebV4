/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.modelo.dao;

import com.mycompany.mavenproject1.modelo.entidade.Mesa;

/**
 *
 * @author rulli
**/

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MesaDao extends GenericoDAO<Mesa> {

    public void salvar(Mesa m) {
        String insert = "INSERT INTO Mesa (numero, capacidade, disponivel) VALUES (?, ?, ?)";
        save(insert, m.getNumero(), m.getCapacidade(), m.isDisponivel());
    }

    public void alterar(Mesa m) {
        String update = "UPDATE Mesa SET numero = ?, capacidade = ?, disponivel = ? WHERE id = ?";
        save(update, m.getNumero(), m.getCapacidade(), m.isDisponivel(), m.getId());
    }

    public void excluir(Mesa m) {
        String delete = "DELETE FROM Mesa WHERE id = ?";
        save(delete, m.getId());
    }

    public Mesa buscarPorId(int id) {
        String select = "SELECT * FROM Mesa WHERE id = ?";
        return buscarPorId(select, new MesaRowMapper(), id);
    }

    public List<Mesa> buscarTodas() {
        String select = "SELECT * FROM Mesa";
        return buscarTodos(select, new MesaRowMapper());
    }

    public static class MesaRowMapper implements RowMapper<Mesa> {
        @Override
        public Mesa mapRow(ResultSet rs) throws SQLException {
            Mesa mesa = new Mesa();
            mesa.setId(rs.getInt("id"));
            mesa.setNumero(rs.getInt("numero"));
            mesa.setCapacidade(rs.getInt("capacidade"));
            mesa.setDisponivel(rs.getBoolean("disponivel"));
            return mesa;
        }
    }
}

