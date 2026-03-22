import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GerenciadorEventos {

    private List<Evento> eventos;
    private static final String ARQUIVO = "events.data";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public GerenciadorEventos() {
        this.eventos = new ArrayList<>();
        carregarEventos();
    }

    public void adicionarEvento(Evento evento) {
        eventos.add(evento);
        salvarEventos();
        System.out.println("Evento '" + evento.getNome() + "' cadastrado com sucesso!");
    }

    public List<Evento> listarTodos() {
        return new ArrayList<>(eventos);
    }

    public List<Evento> listarProximos() {
        return eventos.stream()
                .filter(Evento::isProximo)
                .sorted(Comparator.comparing(Evento::getHorario))
                .collect(Collectors.toList());
    }

    public List<Evento> listarPorCategoria(Categoria categoria) {
        return eventos.stream()
                .filter(e -> e.getCategoria() == categoria)
                .collect(Collectors.toList());
    }

    public Evento buscarPorNome(String nome) {
        return eventos.stream()
                .filter(e -> e.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }

    public Evento buscarPorIndice(int indice) {
        if (indice >= 0 && indice < eventos.size()) {
            return eventos.get(indice);
        }
        return null;
    }

    public List<Evento> getEventosOcorrendo() {
        return eventos.stream()
                .filter(Evento::estaOcorrendo)
                .collect(Collectors.toList());
    }

    public void salvarEventos() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO))) {
            for (Evento e : eventos) {
                writer.println(e.toFileString());
            }
        } catch (IOException ex) {
            System.out.println("Erro ao salvar eventos: " + ex.getMessage());
        }
    }

    public void carregarEventos() {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 5) {
                    String nome = partes[0];
                    String endereco = partes[1];
                    Categoria categoria = Categoria.valueOf(partes[2]);
                    LocalDateTime horario = LocalDateTime.parse(partes[3], FORMATTER);
                    String descricao = partes[4];
                    eventos.add(new Evento(nome, endereco, categoria, horario, descricao));
                }
            }
        } catch (IOException ex) {
            System.out.println("Erro ao carregar eventos: " + ex.getMessage());
        }
    }

    public int getTotalEventos() {
        return eventos.size();
    }
}
