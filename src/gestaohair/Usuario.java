package gestaohair;

public class Usuario {

    private int id;
    private String nome;
    private String login;
    private String senha;
    private String perfil;

    public Usuario() {
    }

    public boolean autenticar(String login, String senha) {
        return this.login != null
                && this.senha != null
                && this.login.equals(login)
                && this.senha.equals(senha);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}