package com.mycompany.mavenproject1.modelo.dao;

import com.mycompany.mavenproject1.modelo.entidade.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao extends GenericoDAO<Cliente> {

    private Connection connection;

    public ClienteDao(Connection connection) {
        this.connection = connection;
    }

    public ClienteDao() {
        // Se você não precisa de um construtor padrão, remova ou ajuste conforme necessário
        throw new UnsupportedOperationException("Construtor padrão não suportado.");
    }

    public List<Cliente> listarTodos() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setSenha(rs.getString("senha"));
                cliente.setUsername(rs.getString("username"));
                cliente.setPassword(rs.getString("password"));
                clientes.add(cliente);
            }
        }

        return clientes;
    }

    public Cliente buscarPorUsername(String username) {
        String sql = "SELECT * FROM cliente WHERE username = ?";
        Cliente cliente = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setSenha(rs.getString("senha"));
                cliente.setUsername(rs.getString("username"));
                cliente.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;
    }
}
