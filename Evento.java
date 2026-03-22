import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Evento {
    private String nome;
    private String endereco;
    private Categoria categoria;
    private LocalDateTime horario;
    private String descricao;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Evento(String nome, String endereco, Categoria categoria, LocalDateTime horario, String descricao) {
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.horario = horario;
        this.descricao = descricao;
    }

    public boolean estaOcorrendo() {
        LocalDateTime agora = LocalDateTime.now();
        return agora.isAfter(horario) && agora.isBefore(horario.plusHours(4));
    }

    public boolean isProximo() {
        LocalDateTime agora = LocalDateTime.now();
        return horario.isAfter(agora);
    }

    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public Categoria getCategoria() { return categoria; }
    public LocalDateTime getHorario() { return horario; }
    public String getDescricao() { return descricao; }

    @Override
    public String toString() {
        return String.format(
            "Evento: %-45s | Categoria: %-18s | Horário: %s | Local: %s",
            nome, categoria, horario.format(FORMATTER), endereco
        );
    }

    public String toFileString() {
        return nome + ";" + endereco + ";" + categoria + ";" + horario.format(FORMATTER) + ";" + descricao;
    }
}
