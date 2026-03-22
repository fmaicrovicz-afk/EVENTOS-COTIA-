import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private String email;
    private String cidade;
    private int idade;
    private List<Evento> eventosConfirmados;

    public Usuario(String nome, String email, String cidade, int idade) {
        this.nome = nome;
        this.email = email;
        this.cidade = cidade;
        this.idade = idade;
        this.eventosConfirmados = new ArrayList<>();
    }

    public void confirmarPresenca(Evento evento) {
        if (!eventosConfirmados.contains(evento)) {
            eventosConfirmados.add(evento);
            System.out.println("Presença confirmada no evento: " + evento.getNome());
        } else {
            System.out.println("Você já confirmou presença neste evento.");
        }
    }

    public void cancelarPresenca(Evento evento) {
        if (eventosConfirmados.remove(evento)) {
            System.out.println("Presença cancelada no evento: " + evento.getNome());
        } else {
            System.out.println("Você não estava inscrito neste evento.");
        }
    }

    public List<Evento> getEventosConfirmados() {
        return eventosConfirmados;
    }

    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getCidade() { return cidade; }
    public int getIdade() { return idade; }

    @Override
    public String toString() {
        return "Usuario{nome='" + nome + "', email='" + email + "', cidade='" + cidade + "', idade=" + idade + "}";
    }
}
