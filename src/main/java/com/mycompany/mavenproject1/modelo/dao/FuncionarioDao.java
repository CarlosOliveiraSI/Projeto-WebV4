/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.modelo.dao;

/**
 *
 * @author rulli
 */

import com.mycompany.mavenproject1.modelo.entidade.Funcionario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FuncionarioDao extends GenericoDAO<Funcionario> {

    public void salvar(Funcionario f) {
        String insert = "INSERT INTO Funcionario (nome, cargo, salario) VALUES (?, ?, ?)";
        save(insert, f.getNome(), f.getCargo(), f.getSalario());
    }

    public void alterar(Funcionario f) {
        String update = "UPDATE Funcionario SET nome = ?, cargo = ?, salario = ? WHERE id = ?";
        save(update, f.getNome(), f.getCargo(), f.getSalario(), f.getId());
    }

    public void excluir(Funcionario f) {
        String delete = "DELETE FROM Funcionario WHERE id = ?";
        save(delete, f.getId());
    }

    public Funcionario buscarPorId(int id) {
        String select = "SELECT * FROM Funcionario WHERE id = ?";
        return buscarPorId(select, new FuncionarioRowMapper(), id);
    }

    public List<Funcionario> buscarTodos() {
        String select = "SELECT * FROM Funcionario";
        return buscarTodos(select, new FuncionarioRowMapper());
    }

    public static class FuncionarioRowMapper implements RowMapper<Funcionario> {
        @Override
        public Funcionario mapRow(ResultSet rs) throws SQLException {
            Funcionario funcionario = new Funcionario();
            funcionario.setId(rs.getInt("id"));
            funcionario.setNome(rs.getString("nome"));
            funcionario.setCargo(rs.getString("cargo"));
            funcionario.setSalario(rs.getDouble("salario"));
            return funcionario;
        }
    }
}
