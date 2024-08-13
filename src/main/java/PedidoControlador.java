
import com.mycompany.mavenproject1.modelo.entidade.Cliente;
import com.mycompany.mavenproject1.modelo.entidade.Pedido;
import com.mycompany.mavenproject1.modelo.entidade.Prato;
import com.mycompany.mavenproject1.servico.PedidoServico;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/PedidoControlador")
public class PedidoControlador extends HttpServlet {

    private PedidoServico pedidoServico;

    @Override
    public void init() throws ServletException {
        super.init();
        pedidoServico = new PedidoServico();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opcao = request.getParameter("opcao");
        if (opcao == null) {
            opcao = "";
        }

        switch (opcao) {
            case "salvar":
                salvarPedido(request);
                break;
            case "editar":
                editarPedido(request);
                break;
            case "excluir":
                excluirPedido(request);
                break;
            case "cancelar":
                cancelarPedido(request);
                break;
            default:
                listarPedidos(request, response);
                break;
        }

        response.sendRedirect("cadastroPedido.jsp");
    }

    private void salvarPedido(HttpServletRequest request) {
        Pedido pedido = new Pedido();
        pedido.setCodigoPedido(Integer.parseInt(request.getParameter("codigoPedido")));
        pedido.setCodigoCliente(Integer.parseInt(request.getParameter("codigoCliente")));
        pedido.setDataPedido(request.getParameter("dataPedido"));
        pedido.setCodigoProduto(Integer.parseInt(request.getParameter("codigoProduto")));
        pedido.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
        pedido.setTotalPedido(Double.parseDouble(request.getParameter("totalPedido")));
        pedidoServico.salvar(pedido);
    }

    private void editarPedido(HttpServletRequest request) {
        Pedido pedido = new Pedido();
        pedido.setCodigoPedido(Integer.parseInt(request.getParameter("codigoPedido")));
        pedido.setCodigoCliente(Integer.parseInt(request.getParameter("codigoCliente")));
        pedido.setDataPedido(request.getParameter("dataPedido"));
        pedido.setCodigoProduto(Integer.parseInt(request.getParameter("codigoProduto")));
        pedido.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
        pedido.setTotalPedido(Double.parseDouble(request.getParameter("totalPedido")));
        pedidoServico.editar(pedido);
    }

    private void excluirPedido(HttpServletRequest request) {
        int codigoPedido = Integer.parseInt(request.getParameter("codigoPedido"));
        pedidoServico.excluir(codigoPedido);
    }

    private void cancelarPedido(HttpServletRequest request) {
        // Lógica para cancelar (se houver)
    }

    private void listarPedidos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Pedido> pedidos = pedidoServico.listar();
        List<Cliente> clientes = pedidoServico.listarCliente();
        List<Prato> pratos = pedidoServico.listarProdutos();

        request.setAttribute("pedidos", pedidos);
        request.setAttribute("clientes", clientes);
        request.setAttribute("produtos", pratos);

        request.getRequestDispatcher("cadastroPedido.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listarPedidos(request, response);
    }
}
