package teste;

import gestaohair.Agendamento;
import gestaohair.Cliente;
import gestaohair.Produto;
import java.util.List;
import servico.AgendamentoService;
import servico.ClienteService;
import servico.ProdutoService;

public class TesteSistema {

    public static void main(String[] args) {

        ClienteService clienteService = new ClienteService();
        ProdutoService produtoService = new ProdutoService();
        AgendamentoService agendamentoService = new AgendamentoService();

        System.out.println("=== TESTE DO SISTEMA GESTAO HAIR ===");

        System.out.println("\n--- CLIENTES ---");
        List<Cliente> clientes = clienteService.listarClientes();

        for (Cliente cliente : clientes) {
            System.out.println(
                    cliente.getId()
                    + " - "
                    + cliente.getNome()
                    + " - "
                    + cliente.getTelefone()
            );
        }

        System.out.println("\n--- PRODUTOS ---");
        List<Produto> produtos = produtoService.listarProdutos();

        for (Produto produto : produtos) {
            System.out.println(
                    produto.getId()
                    + " - "
                    + produto.getNome()
                    + " - Estoque: "
                    + produto.getQuantidadeEstoque()
                    + " - R$ "
                    + produto.getPreco()
            );
        }

        System.out.println("\n--- AGENDAMENTOS ---");
        List<Agendamento> agendamentos =
                agendamentoService.listarAgendamentos();

        for (Agendamento agendamento : agendamentos) {
            System.out.println(
                    agendamento.getId()
                    + " - Cliente: "
                    + agendamento.getCliente()
                    + " - Data: "
                    + agendamento.getData()
                    + " - Horario: "
                    + agendamento.getHorario()
                    + " - Servico: "
                    + agendamento.getServico()
                    + " - Status: "
                    + agendamento.getStatus()
            );
        }

        System.out.println("\n=== TESTE FINALIZADO COM SUCESSO ===");
    }
}