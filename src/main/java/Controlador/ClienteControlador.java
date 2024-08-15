package Controlador;

import Banco.DatabaseConnection;
import Classes_de_Entidade.Cliente;
import Classes_DAO.ClienteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ClienteControlador")
public class ClienteControlador extends HttpServlet {

    private ClienteDAO clienteDao;
    private Connection connection;

    @Override
    public void init() throws ServletException {
        try {
            // Obter a conexão do banco de dados
            connection = DatabaseConnection.getConnection();
            // Passar a conexão para o ClienteDAO
            clienteDao = new ClienteDAO(connection);
        } catch (SQLException e) {
            // Lidar com possíveis erros de conexão com o banco de dados
            throw new ServletException("Erro ao inicializar o ClienteControlador", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opcao = request.getParameter("opcao");
        if (opcao == null) {
            opcao = "";
        }

        try {
            switch (opcao) {
                case "salvar":
                    salvarCliente(request);
                    break;
                case "editar":
                    editarCliente(request);
                    break;
                case "excluir":
                    excluirCliente(request);
                    break;
                default:
                    listarClientes(request, response);
                    break;
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Ocorreu um erro ao processar a operação: " + e.getMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
            return;
        }

        response.sendRedirect("clienteList.jsp");
    }

    private void salvarCliente(HttpServletRequest request) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setNome(request.getParameter("nome"));
        cliente.setEmail(request.getParameter("email"));
        cliente.setTelefone(request.getParameter("telefone"));
        cliente.setCpf(request.getParameter("cpf"));
        cliente.setSenha(request.getParameter("senha"));
        clienteDao.salvar(cliente);
    }

    private void editarCliente(HttpServletRequest request) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(Integer.parseInt(request.getParameter("id")));
        cliente.setNome(request.getParameter("nome"));
        cliente.setEmail(request.getParameter("email"));
        cliente.setTelefone(request.getParameter("telefone"));
        cliente.setCpf(request.getParameter("cpf"));
        cliente.setSenha(request.getParameter("senha"));
        clienteDao.atualizar(cliente);
    }

    private void excluirCliente(HttpServletRequest request) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        clienteDao.excluir(id);
    }

    private void listarClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Cliente> clientes = clienteDao.listar();
            request.setAttribute("clientes", clientes);
            request.getRequestDispatcher("clienteList.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("errorMessage", "Erro ao listar clientes: " + e.getMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listarClientes(request, response);
    }

    @Override
    public void destroy() {
        // Fechar a conexão quando o servlet for destruído
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            // Log ou tratamento de erro
            e.printStackTrace();
        }
    }
}
