import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static GerenciadorEventos gerenciador = new GerenciadorEventos();
    private static Usuario usuarioLogado = null;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("============================================");
        System.out.println("   SISTEMA DE EVENTOS - COTIA/SP");
        System.out.println("============================================");

        // Carrega eventos de Cotia se ainda não foram salvos
        if (gerenciador.getTotalEventos() == 0) {
            carregarEventosCotia();
        }

        cadastrarUsuario();
        menuPrincipal();
    }

    // -------------------------------------------------------
    // Eventos reais de Cotia (março a junho 2026)
    // -------------------------------------------------------
    private static void carregarEventosCotia() {
        System.out.println("\nCarregando eventos de Cotia/SP...\n");

        gerenciador.adicionarEvento(new Evento(
            "13º Passeio Ciclístico - Malucos do Pedal",
            "Praça da Matriz - Cotia/SP",
            Categoria.EVENTO_ESPORTIVO,
            LocalDateTime.of(2026, 3, 15, 8, 13),
            "Passeio ciclístico de 23km pela região de Cotia. Uso obrigatório de capacete. Apoio da Prefeitura de Cotia."
        ));

        gerenciador.adicionarEvento(new Evento(
            "Circuito Sesc de Artes - Cotia",
            "Praça da Matriz - Cotia/SP",
            Categoria.CULTURAL,
            LocalDateTime.of(2026, 5, 24, 10, 0),
            "Apresentação circense, oficina de artes, apresentações culturais e muito mais. Entrada gratuita para todas as idades."
        ));

        gerenciador.adicionarEvento(new Evento(
            "Festival Cultural - Secretaria de Cultura Cotia",
            "Teatro Municipal Regente Pio - Cotia/SP",
            Categoria.CULTURAL,
            LocalDateTime.of(2026, 5, 22, 19, 0),
            "Atividades culturais gratuitas para todas as idades, promovidas pela Secretaria de Cultura e Lazer de Cotia."
        ));

        gerenciador.adicionarEvento(new Evento(
            "Orquestra Musicistas in Concert (Minc)",
            "Parque CEMUCAM - Cotia/SP",
            Categoria.SHOW,
            LocalDateTime.of(2026, 4, 13, 16, 0),
            "Apresentação especial da Orquestra Feminina Musicistas in Concert no Parque CEMUCAM. Entrada gratuita."
        ));

        gerenciador.adicionarEvento(new Evento(
            "Revelando SP 2026 - Representação de Cotia",
            "Centro Cultural - Cotia/SP",
            Categoria.CULTURAL,
            LocalDateTime.of(2026, 4, 25, 9, 0),
            "Festival de valorização da cultura tradicional paulista. Artesanato, culinária e manifestações artístico-culturais."
        ));

        gerenciador.adicionarEvento(new Evento(
            "Show de Dia do Trabalhador",
            "R. Esmeralda, 21 - Jardim Nomura, Cotia/SP",
            Categoria.SHOW,
            LocalDateTime.of(2026, 5, 1, 20, 0),
            "Evento comemorativo ao Dia do Trabalhador com apresentações musicais na cidade de Cotia."
        ));

        gerenciador.adicionarEvento(new Evento(
            "Cinema Pontos MIS - Sessão Gratuita",
            "Biblioteca Batista Cepelos - Cotia/SP",
            Categoria.CULTURAL,
            LocalDateTime.of(2026, 4, 11, 15, 0),
            "Sessão de cinema gratuita com filmes de classificação livre e 12 anos. Parceria Pontos MIS e Secretaria de Cultura de Cotia."
        ));

        gerenciador.adicionarEvento(new Evento(
            "Festa Junina de Cotia",
            "Praça da Matriz - Cotia/SP",
            Categoria.FESTA,
            LocalDateTime.of(2026, 6, 13, 17, 0),
            "Tradicional festa junina com forró, quadrilha, comidas típicas e muito mais para toda a família."
        ));

        gerenciador.adicionarEvento(new Evento(
            "Feira de Artesanato e Gastronomia",
            "Parque CEMUCAM - Cotia/SP",
            Categoria.GASTRONOMICO,
            LocalDateTime.of(2026, 4, 19, 9, 0),
            "Feira com produtos artesanais e gastronomia regional da cidade de Cotia. Entrada gratuita."
        ));

        gerenciador.adicionarEvento(new Evento(
            "Semana Santa - Encenação da Paixão de Cristo",
            "Igreja Matriz - Cotia/SP",
            Categoria.RELIGIOSO,
            LocalDateTime.of(2026, 4, 3, 19, 30),
            "Encenação especial da Paixão de Cristo na Semana Santa. Evento aberto a toda a comunidade."
        ));

        System.out.println("\n10 eventos de Cotia carregados com sucesso!\n");
    }

    // -------------------------------------------------------
    // Cadastro do usuário
    // -------------------------------------------------------
    private static void cadastrarUsuario() {
        System.out.println("\n--- CADASTRO DE USUÁRIO ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = Integer.parseInt(scanner.nextLine());

        usuarioLogado = new Usuario(nome, email, cidade, idade);
        System.out.println("\nBem-vindo(a), " + nome + "! Cadastro realizado com sucesso.");
    }

    // -------------------------------------------------------
    // Menu principal
    // -------------------------------------------------------
    private static void menuPrincipal() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n============================================");
            System.out.println("            MENU PRINCIPAL");
            System.out.println("============================================");
            System.out.println("1. Listar todos os eventos");
            System.out.println("2. Ver próximos eventos (ordenados por data)");
            System.out.println("3. Filtrar eventos por categoria");
            System.out.println("4. Confirmar presença em um evento");
            System.out.println("5. Cancelar presença em um evento");
            System.out.println("6. Ver meus eventos confirmados");
            System.out.println("7. Ver eventos ocorrendo agora");
            System.out.println("8. Cadastrar novo evento");
            System.out.println("0. Sair");
            System.out.print("\nEscolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1: listarTodos(); break;
                case 2: listarProximos(); break;
                case 3: filtrarPorCategoria(); break;
                case 4: confirmarPresenca(); break;
                case 5: cancelarPresenca(); break;
                case 6: verMeusEventos(); break;
                case 7: eventosOcorrendo(); break;
                case 8: cadastrarNovoEvento(); break;
                case 0: System.out.println("\nAté logo, " + usuarioLogado.getNome() + "!"); break;
                default: System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // -------------------------------------------------------
    // Funcionalidades do menu
    // -------------------------------------------------------
    private static void listarTodos() {
        List<Evento> lista = gerenciador.listarTodos();
        System.out.println("\n--- TODOS OS EVENTOS ---");
        if (lista.isEmpty()) {
            System.out.println("Nenhum evento cadastrado.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("[" + i + "] " + lista.get(i));
        }
    }

    private static void listarProximos() {
        List<Evento> lista = gerenciador.listarProximos();
        System.out.println("\n--- PRÓXIMOS EVENTOS (ordenados por data) ---");
        if (lista.isEmpty()) {
            System.out.println("Nenhum evento futuro encontrado.");
            return;
        }
        for (Evento e : lista) {
            System.out.println(e);
        }
    }

    private static void filtrarPorCategoria() {
        System.out.println("\nCategorias disponíveis:");
        Categoria[] categorias = Categoria.values();
        for (int i = 0; i < categorias.length; i++) {
            System.out.println(i + ". " + categorias[i]);
        }
        System.out.print("Escolha o número da categoria: ");
        try {
            int idx = Integer.parseInt(scanner.nextLine());
            Categoria cat = categorias[idx];
            List<Evento> lista = gerenciador.listarPorCategoria(cat);
            System.out.println("\n--- EVENTOS: " + cat + " ---");
            if (lista.isEmpty()) {
                System.out.println("Nenhum evento encontrado nessa categoria.");
            } else {
                lista.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Opção inválida.");
        }
    }

    private static void confirmarPresenca() {
        listarTodos();
        System.out.print("\nDigite o número do evento para confirmar presença: ");
        try {
            int idx = Integer.parseInt(scanner.nextLine());
            Evento evento = gerenciador.buscarPorIndice(idx);
            if (evento != null) {
                usuarioLogado.confirmarPresenca(evento);
            } else {
                System.out.println("Evento não encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
        }
    }

    private static void cancelarPresenca() {
        List<Evento> confirmados = usuarioLogado.getEventosConfirmados();
        if (confirmados.isEmpty()) {
            System.out.println("Você não tem presenças confirmadas.");
            return;
        }
        System.out.println("\nSeus eventos confirmados:");
        for (int i = 0; i < confirmados.size(); i++) {
            System.out.println("[" + i + "] " + confirmados.get(i).getNome());
        }
        System.out.print("Digite o número para cancelar: ");
        try {
            int idx = Integer.parseInt(scanner.nextLine());
            if (idx >= 0 && idx < confirmados.size()) {
                usuarioLogado.cancelarPresenca(confirmados.get(idx));
            } else {
                System.out.println("Índice inválido.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
        }
    }

    private static void verMeusEventos() {
        List<Evento> lista = usuarioLogado.getEventosConfirmados();
        System.out.println("\n--- MEUS EVENTOS CONFIRMADOS ---");
        if (lista.isEmpty()) {
            System.out.println("Você não confirmou presença em nenhum evento.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    private static void eventosOcorrendo() {
        List<Evento> lista = gerenciador.getEventosOcorrendo();
        System.out.println("\n--- EVENTOS OCORRENDO AGORA ---");
        if (lista.isEmpty()) {
            System.out.println("Nenhum evento está ocorrendo no momento.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    private static void cadastrarNovoEvento() {
        System.out.println("\n--- CADASTRAR NOVO EVENTO ---");
        System.out.print("Nome do evento: ");
        String nome = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        System.out.println("Categorias: ");
        Categoria[] cats = Categoria.values();
        for (int i = 0; i < cats.length; i++) {
            System.out.println(i + ". " + cats[i]);
        }
        System.out.print("Escolha a categoria: ");
        Categoria categoria;
        try {
            categoria = cats[Integer.parseInt(scanner.nextLine())];
        } catch (Exception e) {
            System.out.println("Categoria inválida.");
            return;
        }

        System.out.print("Data e hora (dd/MM/yyyy HH:mm): ");
        String dataStr = scanner.nextLine();
        LocalDateTime horario;
        try {
            horario = LocalDateTime.parse(dataStr,
                java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        } catch (Exception e) {
            System.out.println("Formato de data inválido.");
            return;
        }

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        gerenciador.adicionarEvento(new Evento(nome, endereco, categoria, horario, descricao));
    }
}
