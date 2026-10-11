package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {

    private static final String URL = "jdbc:mysql://localhost:3306/gestao_hair";
    private static final String USUARIO = "root";
    private static final String SENHA = "LadyGa13@";

    public static Connection conectar() {
        try {
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conexão com o banco realizada com sucesso!");
            return conexao;
        } catch (SQLException erro) {
            System.out.println("Erro ao conectar ao banco: " + erro.getMessage());
            return null;
        }
    }
}