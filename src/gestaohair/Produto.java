package gestaohair;

import java.time.LocalDate;

public class Produto {

    private int id;
    private String nome;
    private LocalDate dataValidade;
    private int quantidadeEstoque;
    private double preco;
    private int fornecedorId;

    public Produto() {
    }

    public Produto(
            int id,
            String nome,
            LocalDate dataValidade,
            int quantidadeEstoque,
            double preco,
            int fornecedorId
    ) {
        this.id = id;
        this.nome = nome;
        this.dataValidade = dataValidade;
        this.quantidadeEstoque = quantidadeEstoque;
        this.preco = preco;
        this.fornecedorId = fornecedorId;
    }

    public Produto(
            int id,
            String nome,
            int quantidadeEstoque,
            double preco
    ) {
        this.id = id;
        this.nome = nome;
        this.quantidadeEstoque = quantidadeEstoque;
        this.preco = preco;

        this.dataValidade = LocalDate.now().plusYears(1);
        this.fornecedorId = 1;
    }

    public boolean validarDados() {
        return nome != null && !nome.trim().isEmpty()
                && dataValidade != null
                && quantidadeEstoque >= 0
                && preco >= 0
                && fornecedorId > 0;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public double getPreco() {
        return preco;
    }

    public int getFornecedorId() {
        return fornecedorId;
    }
}
