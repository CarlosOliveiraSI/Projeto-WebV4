/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.modelo.dao;

/**
 *
 * @author rulli
 */

import com.mycompany.mavenproject1.modelo.entidade.Fornecedor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FornecedorDao extends GenericoDAO<Fornecedor> {

    public void salvar(Fornecedor f) {
        String insert = "INSERT INTO Fornecedor (nome, cnpj, telefone, endereco) VALUES (?, ?, ?, ?)";
        save(insert, f.getNome(), f.getCnpj(), f.getTelefone(), f.getEndereco());
    }

    public void alterar(Fornecedor f) {
        String update = "UPDATE Fornecedor SET nome = ?, cnpj = ?, telefone = ?, endereco = ? WHERE id = ?";
        save(update, f.getNome(), f.getCnpj(), f.getTelefone(), f.getEndereco(), f.getId());
    }

    public void excluir(Fornecedor f) {
        String delete = "DELETE FROM Fornecedor WHERE id = ?";
        save(delete, f.getId());
    }

    public Fornecedor buscarPorId(int id) {
        String select = "SELECT * FROM Fornecedor WHERE id = ?";
        return buscarPorId(select, new FornecedorRowMapper(), id);
    }

    public List<Fornecedor> buscarTodos() {
        String select = "SELECT * FROM Fornecedor";
        return buscarTodos(select, new FornecedorRowMapper());
    }

    public static class FornecedorRowMapper implements RowMapper<Fornecedor> {
        @Override
        public Fornecedor mapRow(ResultSet rs) throws SQLException {
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setId(rs.getInt("id"));
            fornecedor.setNome(rs.getString("nome"));
            fornecedor.setCnpj(rs.getString("cnpj"));
            fornecedor.setTelefone(rs.getString("telefone"));
            fornecedor.setEndereco(rs.getString("endereco"));
            return fornecedor;
        }
    }
}

