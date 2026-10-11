package gestaohair;

public class Fornecedor {

    private int id;
    private String nome;
    private String telefone;
    private String email;

    public Fornecedor() {
    }

    public Fornecedor(int id, String nome, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public boolean validarDados() {
        return nome != null && !nome.trim().isEmpty();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Fornecedor{"
                + "id=" + id
                + ", nome='" + nome + '\''
                + ", telefone='" + telefone + '\''
                + ", email='" + email + '\''
                + '}';
    }
}