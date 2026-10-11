package dao;

import conexao.ConexaoMySQL;
import gestaohair.Agendamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AgendamentoDAO {

    public boolean cadastrar(Agendamento agendamento) {

        String sql = "INSERT INTO agendamento "
                + "(cliente_id, usuario_id, data, horario, servico, status) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (
                Connection conexao = ConexaoMySQL.conectar(); PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setInt(1, agendamento.getClienteId());
            comando.setInt(2, agendamento.getUsuarioId());
            comando.setDate(3, java.sql.Date.valueOf(agendamento.getData()));
            comando.setTime(4, java.sql.Time.valueOf(agendamento.getHorario()));
            comando.setString(5, agendamento.getServico());
            comando.setString(6, agendamento.getStatus());

            return comando.executeUpdate() > 0;

        } catch (SQLException erro) {
            System.out.println("Erro ao cadastrar agendamento: "
                    + erro.getMessage());
            return false;
        }
    }

    public java.util.List<Agendamento> listar() {

        java.util.List<Agendamento> agendamentos = new java.util.ArrayList<>();

        String sql = "SELECT "
                + "a.id, "
                + "a.cliente_id, "
                + "a.usuario_id, "
                + "a.data, "
                + "a.horario, "
                + "a.servico, "
                + "a.status, "
                + "c.nome AS cliente_nome "
                + "FROM agendamento a "
                + "INNER JOIN cliente c ON a.cliente_id = c.id "
                + "ORDER BY a.data, a.horario";

        try (
                Connection conexao = ConexaoMySQL.conectar(); PreparedStatement comando = conexao.prepareStatement(sql); java.sql.ResultSet resultado = comando.executeQuery()) {
            while (resultado.next()) {

                Agendamento agendamento = new Agendamento(
                        resultado.getInt("id"),
                        resultado.getInt("cliente_id"),
                        resultado.getInt("usuario_id"),
                        resultado.getDate("data").toLocalDate(),
                        resultado.getTime("horario").toLocalTime(),
                        resultado.getString("servico"),
                        resultado.getString("status")
                );

                agendamento.setCliente(resultado.getString("cliente_nome"));

                agendamentos.add(agendamento);
            }

        } catch (SQLException erro) {
            System.out.println("Erro ao listar agendamentos: "
                    + erro.getMessage());
        }

        return agendamentos;
    }
}
