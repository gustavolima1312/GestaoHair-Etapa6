package dao;

import conexao.ConexaoMySQL;
import gestaohair.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public class ProdutoDAO {

    public boolean cadastrar(Produto produto) {

        String sql = "INSERT INTO produto "
                + "(nome, data_validade, quantidade_estoque, preco, fornecedor_id) "
                + "VALUES (?, ?, ?, ?, ?)";

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (
                Connection conexao = ConexaoMySQL.conectar(); PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setString(1, produto.getNome());
            comando.setString(2, produto.getDataValidade().format(formato));
            comando.setInt(3, produto.getQuantidadeEstoque());
            comando.setDouble(4, produto.getPreco());
            comando.setInt(5, produto.getFornecedorId());

            return comando.executeUpdate() > 0;

        } catch (SQLException erro) {
            System.out.println("Erro ao cadastrar produto: "
                    + erro.getMessage());
            return false;
        }
    }

    public java.util.List<Produto> listar() {

        java.util.List<Produto> produtos = new java.util.ArrayList<>();

        String sql = "SELECT id, nome, data_validade, quantidade_estoque, "
                + "preco, fornecedor_id FROM produto ORDER BY nome";

        try (
                Connection conexao = ConexaoMySQL.conectar(); PreparedStatement comando = conexao.prepareStatement(sql); java.sql.ResultSet resultado = comando.executeQuery()) {
            while (resultado.next()) {

                Produto produto = new Produto(
                        resultado.getInt("id"),
                        resultado.getString("nome"),
                        resultado.getDate("data_validade").toLocalDate(),
                        resultado.getInt("quantidade_estoque"),
                        resultado.getDouble("preco"),
                        resultado.getInt("fornecedor_id")
                );

                produtos.add(produto);
            }

        } catch (SQLException erro) {
            System.out.println("Erro ao listar produtos: "
                    + erro.getMessage());
        }

        return produtos;
    }
}
