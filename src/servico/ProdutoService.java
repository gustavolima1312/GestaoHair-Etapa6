package servico;

import dao.ProdutoDAO;
import gestaohair.Produto;
import java.time.LocalDate;
import java.util.List;

public class ProdutoService {

    private final ProdutoDAO produtoDAO;

    public ProdutoService() {
        produtoDAO = new ProdutoDAO();
    }

    public boolean cadastrarProduto(
            String nome,
            int quantidade,
            double preco,
            int fornecedorId
    ) {
        Produto produto = new Produto(
                0,
                nome,
                LocalDate.of(2027, 12, 31),
                quantidade,
                preco,
                fornecedorId
        );

        if (!produto.validarDados()) {
            return false;
        }

        return produtoDAO.cadastrar(produto);
    }

    public List<Produto> listarProdutos() {
        return produtoDAO.listar();
    }
}