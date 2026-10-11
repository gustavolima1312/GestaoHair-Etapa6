package servico;

import dao.ClienteDAO;
import gestaohair.Cliente;
import java.util.List;

public class ClienteService {

    private final ClienteDAO clienteDAO;

    public ClienteService() {
        clienteDAO = new ClienteDAO();
    }

    public boolean cadastrarCliente(
            String nome,
            String telefone,
            String endereco
    ) {
        Cliente cliente = new Cliente(0, nome, telefone, endereco);

        if (!cliente.validarDados()) {
            return false;
        }

        return clienteDAO.cadastrar(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteDAO.listar();
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