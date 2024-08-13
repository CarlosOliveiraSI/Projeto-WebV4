/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.servico;

import com.mycompany.mavenproject1.modelo.dao.ClienteDao;
import com.mycompany.mavenproject1.modelo.entidade.Cliente;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author carlo
 */
public class ClienteServico {
    private ClienteDao clienteDAO;

    public ClienteServico(Connection connection) {
        this.clienteDAO = new ClienteDao(connection);
    }

    public List<Cliente> listarClientes() {
        try {
            return clienteDAO.listarTodos();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
