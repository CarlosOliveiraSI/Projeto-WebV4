package com.mycompany.mavenproject1.servico;

import com.mycompany.mavenproject1.modelo.dao.ClienteDao;
import com.mycompany.mavenproject1.modelo.dao.PedidoDao;
import com.mycompany.mavenproject1.modelo.dao.PratoDAO;
import com.mycompany.mavenproject1.modelo.entidade.Cliente;
import com.mycompany.mavenproject1.modelo.entidade.Pedido;
import com.mycompany.mavenproject1.modelo.entidade.Prato;
import java.sql.SQLException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PedidoServico {

    private ClienteDao clienteDAO;
    private PratoDAO pratoDAO;
    private PedidoDao pedidoDAO;

    public PedidoServico() {
        clienteDAO = new ClienteDao();
        pratoDAO = new PratoDAO();
        pedidoDAO = new PedidoDao();
    }

    // Métodos para Cliente
    public List<Cliente> listarCliente() {
        try {
            return clienteDAO.listarTodos();
        } catch (SQLException ex) {
            Logger.getLogger(PedidoServico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Métodos para Prato
    public List<Prato> listarProdutos() {
        return pratoDAO.buscarTodos();
    }

    // Métodos para Pedido
    public void salvar(Pedido pedido) {
        pedidoDAO.salvar(pedido);
    }

    public void editar(Pedido pedido) {
        pedidoDAO.alterar(pedido);
    }

    public void excluir(int id) {
        Pedido pedido = pedidoDAO.buscarPorId(id);
        if (pedido != null) {
            pedidoDAO.excluir(pedido);
        }
    }

    public Pedido buscarPorId(int id) {
        return pedidoDAO.buscarPorId(id);
    }

    public List<Pedido> listar() {
        return pedidoDAO.buscarTodos();
    }
}
