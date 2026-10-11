package dao;

import conexao.ConexaoMySQL;
import gestaohair.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDAO {

    public boolean cadastrar(Cliente cliente) {

        String sql = "INSERT INTO cliente (nome, telefone, endereco) "
                + "VALUES (?, ?, ?)";

        try (
                Connection conexao = ConexaoMySQL.conectar(); PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setString(1, cliente.getNome());
            comando.setString(2, cliente.getTelefone());
            comando.setString(3, cliente.getEndereco());

            int linhasAfetadas = comando.executeUpdate();

            return linhasAfetadas > 0;

        } catch (SQLException erro) {
            System.out.println("Erro ao cadastrar cliente: "
                    + erro.getMessage());
            return false;
        }
    }

    public void listarTodos() {

        String sql = "SELECT id, nome, telefone, endereco FROM cliente";

        try (
                Connection conexao = ConexaoMySQL.conectar(); PreparedStatement comando = conexao.prepareStatement(sql); java.sql.ResultSet resultado = comando.executeQuery()) {
            boolean encontrouCliente = false;

            System.out.println("\n========= CLIENTES CADASTRADOS =========");

            while (resultado.next()) {
                encontrouCliente = true;

                System.out.println("ID: " + resultado.getInt("id"));
                System.out.println("Nome: " + resultado.getString("nome"));
                System.out.println("Telefone: " + resultado.getString("telefone"));
                System.out.println("Endereco: " + resultado.getString("endereco"));
                System.out.println("----------------------------------------");
            }

            if (!encontrouCliente) {
                System.out.println("Nenhum cliente cadastrado.");
            }

        } catch (SQLException erro) {
            System.out.println("Erro ao listar clientes: " + erro.getMessage());
        }
    }

    public java.util.List<Cliente> listar() {

        java.util.List<Cliente> clientes = new java.util.ArrayList<>();

        String sql = "SELECT id, nome, telefone, endereco "
                + "FROM cliente ORDER BY nome";

        try (
                Connection conexao = ConexaoMySQL.conectar(); PreparedStatement comando = conexao.prepareStatement(sql); java.sql.ResultSet resultado = comando.executeQuery()) {
            while (resultado.next()) {

                Cliente cliente = new Cliente(
                        resultado.getInt("id"),
                        resultado.getString("nome"),
                        resultado.getString("telefone"),
                        resultado.getString("endereco")
                );

                clientes.add(cliente);
            }

        } catch (SQLException erro) {
            System.out.println("Erro ao listar clientes: "
                    + erro.getMessage());
        }

        return clientes;
    }
}
