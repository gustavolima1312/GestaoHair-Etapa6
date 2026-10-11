package dao;

import conexao.ConexaoMySQL;
import gestaohair.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FornecedorDAO {

    public boolean cadastrar(Fornecedor fornecedor) {

        String sql = "INSERT INTO fornecedor "
                + "(nome, telefone, email) VALUES (?, ?, ?)";

        try (
                Connection conexao = ConexaoMySQL.conectar();
                PreparedStatement comando = conexao.prepareStatement(sql)
        ) {
            comando.setString(1, fornecedor.getNome());
            comando.setString(2, fornecedor.getTelefone());
            comando.setString(3, fornecedor.getEmail());

            return comando.executeUpdate() > 0;

        } catch (SQLException erro) {
            System.out.println("Erro ao cadastrar fornecedor: "
                    + erro.getMessage());
            return false;
        }
    }
}