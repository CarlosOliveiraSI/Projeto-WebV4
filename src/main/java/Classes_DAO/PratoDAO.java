/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes_DAO;

/**
 *
 * @author carlo
 */
import Classes_de_Entidade.Prato;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PratoDAO {

    private Connection connection;

    public PratoDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Prato prato) throws SQLException {
        String sql = "INSERT INTO Prato (nome, descricao, preco, categoria_id, fornecedor_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, prato.getNome());
            stmt.setString(2, prato.getDescricao());
            stmt.setDouble(3, prato.getPreco());
            stmt.setInt(4, prato.getCategoriaId());
            stmt.setInt(5, prato.getFornecedorId());
            stmt.executeUpdate();
        }
    }

    public Prato buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Prato WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Prato prato = new Prato();
                prato.setId(rs.getInt("id"));
                prato.setNome(rs.getString("nome"));
                prato.setDescricao(rs.getString("descricao"));
                prato.setPreco(rs.getDouble("preco"));
                prato.setCategoriaId(rs.getInt("categoria_id"));
                prato.setFornecedorId(rs.getInt("fornecedor_id"));
                return prato;
            }
        }
        return null;
    }

    public void atualizar(Prato prato) throws SQLException {
        String sql = "UPDATE Prato SET nome = ?, descricao = ?, preco = ?, categoria_id = ?, fornecedor_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, prato.getNome());
            stmt.setString(2, prato.getDescricao());
            stmt.setDouble(3, prato.getPreco());
            stmt.setInt(4, prato.getCategoriaId());
            stmt.setInt(5, prato.getFornecedorId());
            stmt.setInt(6, prato.getId());
            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM Prato WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Prato> listar() throws SQLException {
        List<Prato> pratos = new ArrayList<>();
        String sql = "SELECT * FROM Prato";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Prato prato = new Prato();
                prato.setId(rs.getInt("id"));
                prato.setNome(rs.getString("nome"));
                prato.setDescricao(rs.getString("descricao"));
                prato.setPreco(rs.getDouble("preco"));
                prato.setCategoriaId(rs.getInt("categoria_id"));
                prato.setFornecedorId(rs.getInt("fornecedor_id"));
                pratos.add(prato);
            }
        }
        return pratos;
    }
}
