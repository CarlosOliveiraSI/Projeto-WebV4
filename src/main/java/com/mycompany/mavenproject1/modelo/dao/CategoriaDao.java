/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.modelo.dao;

/**
 *
 * @author rulli
 */

import com.mycompany.mavenproject1.modelo.entidade.Categoria;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CategoriaDao extends GenericoDAO<Categoria> {

    public void salvar(Categoria c) {
        String insert = "INSERT INTO Categoria (nome) VALUES (?)";
        save(insert, c.getNome());
    }

    public void alterar(Categoria c) {
        String update = "UPDATE Categoria SET nome = ? WHERE id = ?";
        save(update, c.getNome(), c.getId());
    }

    public void excluir(Categoria c) {
        String delete = "DELETE FROM Categoria WHERE id = ?";
        save(delete, c.getId());
    }

    public Categoria buscarPorId(int id) {
        String select = "SELECT * FROM Categoria WHERE id = ?";
        return buscarPorId(select, new CategoriaRowMapper(), id);
    }

    public List<Categoria> buscarTodos() {
        String select = "SELECT * FROM Categoria";
        return buscarTodos(select, new CategoriaRowMapper());
    }

    public static class CategoriaRowMapper implements RowMapper<Categoria> {
        @Override
        public Categoria mapRow(ResultSet rs) throws SQLException {
            Categoria categoria = new Categoria();
            categoria.setId(rs.getInt("id"));
            categoria.setNome(rs.getString("nome"));
            return categoria;
        }
    }
}

