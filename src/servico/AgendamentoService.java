package servico;

import dao.AgendamentoDAO;
import dao.ClienteDAO;
import gestaohair.Agendamento;
import gestaohair.Cliente;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AgendamentoService {

    private final AgendamentoDAO agendamentoDAO;
    private final ClienteDAO clienteDAO;

    public AgendamentoService() {
        agendamentoDAO = new AgendamentoDAO();
        clienteDAO = new ClienteDAO();
    }

    public boolean cadastrarAgendamento(
            String nomeCliente,
            LocalDate data,
            LocalTime horario,
            String servico,
            int usuarioId
    ) {
        Cliente cliente = buscarClientePorNome(nomeCliente);

        if (cliente == null) {
            return false;
        }

        Agendamento agendamento = new Agendamento(
                0,
                cliente.getId(),
                usuarioId,
                data,
                horario,
                servico,
                "Agendado"
        );

        if (!agendamento.validarDados()) {
            return false;
        }

        return agendamentoDAO.cadastrar(agendamento);
    }

    public List<Agendamento> listarAgendamentos() {
        return agendamentoDAO.listar();
    }

    public Cliente buscarClientePorNome(String nome) {

        List<Cliente> clientes = clienteDAO.listar();

        for (Cliente cliente : clientes) {
            if (cliente.getNome().equalsIgnoreCase(nome)) {
                return cliente;
            }
        }

        return null;
    }
}