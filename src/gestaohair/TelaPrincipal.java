package gestaohair;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import servico.ClienteService;
import servico.ProdutoService;
import servico.AgendamentoService;

public class TelaPrincipal extends JFrame {

    private final CardLayout layout;
    private final JPanel painelCentral;
    private final CardLayout layoutPrincipal;
    private final JPanel painelPrincipal;

    private JTextField campoNome;
    private JTextField campoTelefone;
    private JTextField campoEndereco;

    private DefaultTableModel modeloTabela;

    private final List<Cliente> clientes;
    private int proximoId;

    private JTextField campoProdutoNome;
    private JTextField campoProdutoQuantidade;
    private JTextField campoProdutoPreco;

    private DefaultTableModel modeloTabelaProdutos;

    private final List<Produto> produtos;
    private int proximoIdProduto;

    private JComboBox<String> comboCliente;
    private JTextField campoData;
    private JTextField campoHorario;
    private JTextField campoServico;

    private DefaultTableModel modeloTabelaAgendamentos;

    private final List<Agendamento> agendamentos;
    private int proximoIdAgendamento;

    private final ClienteService clienteService;
    private final ProdutoService produtoService;
    private final AgendamentoService agendamentoService;

    private JTextField campoLogin;
    private JPasswordField campoSenha;

    public TelaPrincipal() {

        setTitle("Gestao Hair");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        layoutPrincipal = new CardLayout();
        painelPrincipal = new JPanel(layoutPrincipal);

        clientes = new ArrayList<>();
        proximoId = 1;

        produtos = new ArrayList<>();
        proximoIdProduto = 1;

        agendamentos = new ArrayList<>();
        proximoIdAgendamento = 1;

        clienteService = new ClienteService();
        produtoService = new ProdutoService();
        agendamentoService = new AgendamentoService();

        layout = new CardLayout();
        painelCentral = new JPanel(layout);

        painelCentral.add(criarInicio(), "inicio");
        painelCentral.add(criarClientes(), "clientes");
        painelCentral.add(criarProdutos(), "produtos");
        painelCentral.add(criarAgendamentos(), "agendamentos");

        JPanel painelSistema = new JPanel(new BorderLayout());
        painelSistema.add(criarCabecalho(), BorderLayout.NORTH);
        painelSistema.add(criarMenu(), BorderLayout.WEST);
        painelSistema.add(painelCentral, BorderLayout.CENTER);

        painelPrincipal.add(criarLogin(), "login");
        painelPrincipal.add(painelSistema, "sistema");

        add(painelPrincipal);

        layoutPrincipal.show(painelPrincipal, "login");
    }

    private JPanel criarLogin() {

        JPanel painel = new JPanel(new GridLayout(7, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(110, 280, 110, 280));

        JLabel titulo = new JLabel("Gestao Hair", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));

        JLabel subtitulo = new JLabel(
                "Sistema de Gerenciamento",
                SwingConstants.CENTER
        );
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 16));

        campoLogin = new JTextField();
        campoSenha = new JPasswordField();

        JButton entrar = new JButton("Entrar");

        entrar.addActionListener(e -> realizarLogin());

        campoSenha.addActionListener(e -> realizarLogin());

        painel.add(titulo);
        painel.add(subtitulo);
        painel.add(new JLabel("Login:"));
        painel.add(campoLogin);
        painel.add(new JLabel("Senha:"));
        painel.add(campoSenha);
        painel.add(entrar);

        return painel;
    }

    private JPanel criarCabecalho() {

        JPanel painel = new JPanel(new BorderLayout());
        painel.setPreferredSize(new Dimension(0, 70));
        painel.setBackground(new Color(210, 210, 210));
        painel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel titulo = new JLabel("Gestao Hair");
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        JLabel usuario = new JLabel("Usuario: Admin");
        usuario.setFont(new Font("Arial", Font.PLAIN, 16));
        usuario.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

        painel.add(titulo, BorderLayout.WEST);
        painel.add(usuario, BorderLayout.EAST);

        return painel;
    }

    private JPanel criarMenu() {

        JPanel painel = new JPanel(new GridLayout(7, 1, 5, 5));
        painel.setPreferredSize(new Dimension(190, 0));
        painel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        painel.setBackground(new Color(225, 225, 225));

        JButton inicio = new JButton("Inicio");
        JButton clientesBotao = new JButton("Clientes");
        JButton produtos = new JButton("Produtos");
        JButton agendamentos = new JButton("Agendamentos");
        JButton fornecedores = new JButton("Fornecedores");
        JButton sair = new JButton("Sair");

        inicio.addActionListener(e -> layout.show(painelCentral, "inicio"));

        clientesBotao.addActionListener(e -> {
            atualizarTabelaClientes();
            layout.show(painelCentral, "clientes");
        });

        produtos.addActionListener(e -> {
            atualizarTabelaProdutos();
            layout.show(painelCentral, "produtos");
        });

        agendamentos.addActionListener(e -> {
            atualizarComboClientes();
            atualizarTabelaAgendamentos();
            layout.show(painelCentral, "agendamentos");
        });

        fornecedores.addActionListener(e
                -> mensagemEmDesenvolvimento("Fornecedores"));

        sair.addActionListener(e -> System.exit(0));

        painel.add(inicio);
        painel.add(clientesBotao);
        painel.add(produtos);
        painel.add(agendamentos);
        painel.add(fornecedores);
        painel.add(new JLabel());
        painel.add(sair);

        return painel;
    }

    private JPanel criarInicio() {

        JPanel painel = new JPanel(new GridLayout(2, 1, 15, 15));
        painel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        JLabel titulo = new JLabel("Resumo do sistema", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        int totalClientes = clienteService.listarClientes().size();
        int totalProdutos = produtoService.listarProdutos().size();
        int totalAgendamentos = agendamentoService.listarAgendamentos().size();

        JLabel resumo = new JLabel(
                "<html><center>"
                + "Clientes cadastrados: " + totalClientes + "<br><br>"
                + "Produtos cadastrados: " + totalProdutos + "<br><br>"
                + "Agendamentos registrados: " + totalAgendamentos
                + "</center></html>",
                SwingConstants.CENTER
        );

        painel.add(titulo);
        painel.add(resumo);

        return painel;
    }

    private JPanel criarClientes() {

        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Clientes");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        painel.add(titulo, BorderLayout.NORTH);

        JPanel formulario = new JPanel(new GridLayout(4, 2, 10, 10));
        formulario.setBorder(BorderFactory.createTitledBorder(
                "Cadastro de cliente"
        ));

        campoNome = new JTextField();
        campoTelefone = new JTextField();
        campoEndereco = new JTextField();

        formulario.add(new JLabel("Nome:"));
        formulario.add(campoNome);

        formulario.add(new JLabel("Telefone:"));
        formulario.add(campoTelefone);

        formulario.add(new JLabel("Endereco:"));
        formulario.add(campoEndereco);

        JButton salvar = new JButton("Salvar");
        JButton limpar = new JButton("Limpar");

        salvar.addActionListener(e -> cadastrarCliente());
        limpar.addActionListener(e -> limparFormulario());

        formulario.add(salvar);
        formulario.add(limpar);

        painel.add(formulario, BorderLayout.CENTER);

        modeloTabela = new DefaultTableModel(
                new Object[]{"ID", "Nome", "Telefone", "Endereco"},
                0
        ) {
            @Override
            public boolean isCellEditable(int linha, int coluna) {
                return false;
            }
        };

        JTable tabelaClientes = new JTable(modeloTabela);

        JScrollPane rolagem = new JScrollPane(tabelaClientes);
        rolagem.setBorder(BorderFactory.createTitledBorder(
                "Clientes cadastrados"
        ));
        rolagem.setPreferredSize(new Dimension(0, 220));

        painel.add(rolagem, BorderLayout.SOUTH);

        atualizarTabelaClientes();

        return painel;
    }

    private JPanel criarProdutos() {

        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Produtos");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        painel.add(titulo, BorderLayout.NORTH);

        JPanel formulario = new JPanel(new GridLayout(4, 2, 10, 10));
        formulario.setBorder(BorderFactory.createTitledBorder(
                "Cadastro de produto"
        ));

        campoProdutoNome = new JTextField();
        campoProdutoQuantidade = new JTextField();
        campoProdutoPreco = new JTextField();

        formulario.add(new JLabel("Nome:"));
        formulario.add(campoProdutoNome);

        formulario.add(new JLabel("Quantidade em estoque:"));
        formulario.add(campoProdutoQuantidade);

        formulario.add(new JLabel("Preco:"));
        formulario.add(campoProdutoPreco);

        JButton salvar = new JButton("Salvar");
        JButton limpar = new JButton("Limpar");

        salvar.addActionListener(e -> cadastrarProduto());
        limpar.addActionListener(e -> limparFormularioProduto());

        formulario.add(salvar);
        formulario.add(limpar);

        painel.add(formulario, BorderLayout.CENTER);

        modeloTabelaProdutos = new DefaultTableModel(
                new Object[]{"ID", "Nome", "Quantidade", "Preco"},
                0
        ) {
            @Override
            public boolean isCellEditable(int linha, int coluna) {
                return false;
            }
        };

        JTable tabelaProdutos = new JTable(modeloTabelaProdutos);

        JScrollPane rolagem = new JScrollPane(tabelaProdutos);
        rolagem.setBorder(BorderFactory.createTitledBorder(
                "Produtos cadastrados"
        ));
        rolagem.setPreferredSize(new Dimension(0, 220));

        painel.add(rolagem, BorderLayout.SOUTH);

        atualizarTabelaProdutos();

        return painel;
    }

    private JPanel criarAgendamentos() {

        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Agendamentos");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        painel.add(titulo, BorderLayout.NORTH);

        JPanel formulario = new JPanel(new GridLayout(5, 2, 10, 10));
        formulario.setBorder(BorderFactory.createTitledBorder(
                "Novo agendamento"
        ));

        comboCliente = new JComboBox<>();
        campoData = new JTextField();
        campoHorario = new JTextField();
        campoServico = new JTextField();

        formulario.add(new JLabel("Cliente:"));
        formulario.add(comboCliente);

        formulario.add(new JLabel("Data (DD/MM/AAAA):"));
        formulario.add(campoData);

        formulario.add(new JLabel("Horario (HH:MM):"));
        formulario.add(campoHorario);

        formulario.add(new JLabel("Servico:"));
        formulario.add(campoServico);

        JButton salvar = new JButton("Salvar");
        JButton limpar = new JButton("Limpar");

        salvar.addActionListener(e -> cadastrarAgendamento());
        limpar.addActionListener(e -> limparFormularioAgendamento());

        formulario.add(salvar);
        formulario.add(limpar);

        painel.add(formulario, BorderLayout.CENTER);

        modeloTabelaAgendamentos = new DefaultTableModel(
                new Object[]{
                    "ID", "Cliente", "Data", "Horario", "Servico", "Status"
                },
                0
        ) {
            @Override
            public boolean isCellEditable(int linha, int coluna) {
                return false;
            }
        };

        JTable tabelaAgendamentos = new JTable(modeloTabelaAgendamentos);

        JScrollPane rolagem = new JScrollPane(tabelaAgendamentos);
        rolagem.setBorder(BorderFactory.createTitledBorder(
                "Agendamentos cadastrados"
        ));
        rolagem.setPreferredSize(new Dimension(0, 220));

        painel.add(rolagem, BorderLayout.SOUTH);

        atualizarComboClientes();
        atualizarTabelaAgendamentos();

        return painel;
    }

    private void cadastrarCliente() {

        String nome = campoNome.getText().trim();
        String telefone = campoTelefone.getText().trim();
        String endereco = campoEndereco.getText().trim();

        if (clienteService.cadastrarCliente(nome, telefone, endereco)) {
            JOptionPane.showMessageDialog(
                    this,
                    "Cliente cadastrado com sucesso no banco de dados!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
            );

            limparFormulario();
            atualizarTabelaClientes();

        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Preencha os dados do cliente corretamente.",
                    "Dados invalidos",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void atualizarTabelaClientes() {

        if (modeloTabela == null) {
            return;
        }

        modeloTabela.setRowCount(0);

        List<Cliente> clientesBanco = clienteService.listarClientes();

        for (Cliente cliente : clientesBanco) {
            modeloTabela.addRow(new Object[]{
                cliente.getId(),
                cliente.getNome(),
                cliente.getTelefone(),
                cliente.getEndereco()
            });
        }
    }

    private void limparFormulario() {
        campoNome.setText("");
        campoTelefone.setText("");
        campoEndereco.setText("");
        campoNome.requestFocus();
    }

    private void cadastrarProduto() {

        String nome = campoProdutoNome.getText().trim();
        String textoQuantidade = campoProdutoQuantidade.getText().trim();
        String textoPreco = campoProdutoPreco.getText().trim()
                .replace(",", ".");

        try {
            int quantidade = Integer.parseInt(textoQuantidade);
            double preco = Double.parseDouble(textoPreco);

            if (produtoService.cadastrarProduto(nome, quantidade, preco, 1)) {
                JOptionPane.showMessageDialog(
                        this,
                        "Produto cadastrado com sucesso no banco de dados!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE
                );

                limparFormularioProduto();
                atualizarTabelaProdutos();

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Preencha os dados do produto corretamente.",
                        "Dados invalidos",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(
                    this,
                    "Quantidade deve ser um numero inteiro e preco deve ser numerico.",
                    "Dados invalidos",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void atualizarTabelaProdutos() {

        if (modeloTabelaProdutos == null) {
            return;
        }

        modeloTabelaProdutos.setRowCount(0);

        List<Produto> produtosBanco = produtoService.listarProdutos();

        for (Produto produto : produtosBanco) {
            modeloTabelaProdutos.addRow(new Object[]{
                produto.getId(),
                produto.getNome(),
                produto.getQuantidadeEstoque(),
                String.format("R$ %.2f", produto.getPreco())
            });
        }
    }

    private void limparFormularioProduto() {
        campoProdutoNome.setText("");
        campoProdutoQuantidade.setText("");
        campoProdutoPreco.setText("");
        campoProdutoNome.requestFocus();
    }

    private void cadastrarAgendamento() {

        String clienteSelecionado = (String) comboCliente.getSelectedItem();
        String textoData = campoData.getText().trim();
        String textoHorario = campoHorario.getText().trim();
        String servico = campoServico.getText().trim();

        if (clienteSelecionado == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Cadastre um cliente antes de criar um agendamento.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {
            DateTimeFormatter formatoData
                    = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate data = LocalDate.parse(textoData, formatoData);
            LocalTime horario = LocalTime.parse(textoHorario);

            if (agendamentoService.cadastrarAgendamento(
                    clienteSelecionado,
                    data,
                    horario,
                    servico,
                    1
            )) {
                JOptionPane.showMessageDialog(
                        this,
                        "Agendamento cadastrado com sucesso no banco de dados!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE
                );

                limparFormularioAgendamento();
                atualizarTabelaAgendamentos();

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Preencha os dados corretamente ou verifique o cliente.",
                        "Dados invalidos",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (DateTimeParseException erro) {
            JOptionPane.showMessageDialog(
                    this,
                    "Use os formatos: Data DD/MM/AAAA e horario HH:MM.",
                    "Dados invalidos",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void atualizarComboClientes() {

        if (comboCliente == null) {
            return;
        }

        comboCliente.removeAllItems();

        List<Cliente> clientesBanco = clienteService.listarClientes();

        for (Cliente cliente : clientesBanco) {
            comboCliente.addItem(cliente.getNome());
        }
    }

    private void atualizarTabelaAgendamentos() {

        if (modeloTabelaAgendamentos == null) {
            return;
        }

        modeloTabelaAgendamentos.setRowCount(0);

        List<Agendamento> agendamentosBanco
                = agendamentoService.listarAgendamentos();

        for (Agendamento agendamento : agendamentosBanco) {
            modeloTabelaAgendamentos.addRow(new Object[]{
                agendamento.getId(),
                agendamento.getCliente(),
                agendamento.getData().format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy")
                ),
                agendamento.getHorario(),
                agendamento.getServico(),
                agendamento.getStatus()
            });
        }
    }

    private void limparFormularioAgendamento() {
        campoData.setText("");
        campoHorario.setText("");
        campoServico.setText("");

        if (comboCliente.getItemCount() > 0) {
            comboCliente.setSelectedIndex(0);
        }

        campoData.requestFocus();
    }

    private void mensagemEmDesenvolvimento(String funcionalidade) {
        JOptionPane.showMessageDialog(
                this,
                funcionalidade + " ainda esta em desenvolvimento.",
                "Informacao",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void realizarLogin() {

        String login = campoLogin.getText().trim();
        String senha = new String(campoSenha.getPassword());

        if (login.equals("admin") && senha.equals("1234")) {
            campoLogin.setText("");
            campoSenha.setText("");

            layout.show(painelCentral, "inicio");
            layoutPrincipal.show(painelPrincipal, "sistema");

        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Usuario ou senha invalidos.",
                    "Erro de login",
                    JOptionPane.ERROR_MESSAGE
            );

            campoSenha.setText("");
            campoSenha.requestFocus();
        }
    }
}
