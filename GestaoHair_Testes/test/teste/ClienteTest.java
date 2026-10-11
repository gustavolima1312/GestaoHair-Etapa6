package teste;

import gestaohair.Cliente;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ClienteTest {

    @Test
    public void deveValidarClienteComNomeETelefone() {

        Cliente cliente = new Cliente(
                0,
                "Ana Souza",
                "(11) 99999-0000",
                "Rua das Flores, 100"
        );

        assertTrue(cliente.validarDados());
    }

    @Test
    public void naoDeveValidarClienteSemNome() {

        Cliente cliente = new Cliente(
                0,
                "",
                "(11) 99999-0000",
                "Rua das Flores, 100"
        );

        assertFalse(cliente.validarDados());
    }

    @Test
    public void naoDeveValidarClienteSemTelefone() {

        Cliente cliente = new Cliente(
                0,
                "Ana Souza",
                "",
                "Rua das Flores, 100"
        );

        assertFalse(cliente.validarDados());
    }
}