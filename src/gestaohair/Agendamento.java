package gestaohair;

import java.time.LocalDate;
import java.time.LocalTime;

public class Agendamento {

    private int id;
    private int clienteId;
    private int usuarioId;
    private LocalDate data;
    private LocalTime horario;
    private String servico;
    private String status;
    private String cliente;

    public Agendamento() {
    }

    public Agendamento(
            int id,
            int clienteId,
            int usuarioId,
            LocalDate data,
            LocalTime horario,
            String servico,
            String status
    ) {
        this.id = id;
        this.clienteId = clienteId;
        this.usuarioId = usuarioId;
        this.data = data;
        this.horario = horario;
        this.servico = servico;
        this.status = status;
    }

    public Agendamento(
            int id,
            String cliente,
            String data,
            String horario,
            String servico,
            String status
    ) {
        this.id = id;

        this.clienteId = 1;
        this.usuarioId = 1;

        this.data = LocalDate.parse(
                data,
                java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")
        );

        this.horario = LocalTime.parse(horario);

        this.servico = servico;
        this.status = status;
    }

    public boolean validarDados() {
        return clienteId > 0
                && usuarioId > 0
                && data != null
                && horario != null
                && servico != null
                && !servico.trim().isEmpty();
    }

    public int getId() {
        return id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public String getServico() {
        return servico;
    }

    public String getStatus() {
        return status;
    }
    
    public String getCliente() {
    return "Cliente ID: " + clienteId;
    }
    
    public void setCliente(String cliente) {
    this.cliente = cliente;
}
}

