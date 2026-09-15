package io.github.gbrandrade;

import io.github.gbrandrade.model.*;
import io.github.gbrandrade.repository.*;
import io.github.gbrandrade.service.RegistroJogoService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static PlataformaRepository plataformaRepository = new PlataformaRepository();
    static EmpresaRepository empresaRepository = new EmpresaRepository();
    static JogoRepository jogoRepository = new JogoRepository();
    static UsuarioRepository usuarioRepository = new UsuarioRepository();
    static RegistroJogoService registroJogoService = new RegistroJogoService();

    public static void main(String[] args) throws SQLException {
        exibirBanner();
        int opcao;

        do {
            System.out.println("\n=== GAME BACKLOG ===");
            System.out.println("1 - Cadastrar plataforma");
            System.out.println("2 - Listar plataformas");
            System.out.println("3 - Cadastrar empresa");
            System.out.println("4 - Cadastrar jogo");
            System.out.println("5 - Listar jogos");
            System.out.println("6 - Cadastrar usuário");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> cadastrarPlataforma();
                case 2 -> listarPlataformas();
                case 3 -> cadastrarEmpresa();
                case 4 -> cadastrarJogo();
                case 5 -> listarJogos();
                case 6 -> cadastrarUsuario();
                case 0 -> System.out.println("Até mais!");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    static void exibirBanner() {
        String azul = "\u001B[34m";
        String reset = "\u001B[0m";

        System.out.println(azul + """
             ██████╗  █████╗ ███╗   ███╗███████╗
            ██╔════╝ ██╔══██╗████╗ ████║██╔════╝
            ██║  ███╗███████║██╔████╔██║█████╗
            ██║   ██║██╔══██║██║╚██╔╝██║██╔══╝
            ╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗
             ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝
             ██████╗  █████╗  ██████╗██╗  ██╗██╗      ██████╗  ██████╗
            ██╔══██╗██╔══██╗██╔════╝██║ ██╔╝██║     ██╔═══██╗██╔════╝
            ██████╔╝███████║██║     █████╔╝ ██║     ██║   ██║██║  ███╗
            ██╔══██╗██╔══██║██║     ██╔═██╗ ██║     ██║   ██║██║   ██║
            ██████╔╝██║  ██║╚██████╗██║  ██╗███████╗╚██████╔╝╚██████╔╝
            ╚═════╝ ╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝╚══════╝ ╚═════╝  ╚═════╝
            """ + reset);
    }

    static void cadastrarPlataforma() throws SQLException {
        System.out.print("Nome da plataforma: ");
        String nome = scanner.nextLine();
        plataformaRepository.salvar(new Plataforma(nome));
        System.out.println("Plataforma salva!");
    }

    static void listarPlataformas() throws SQLException {
        List<Plataforma> plataformas = plataformaRepository.listarTodas();
        plataformas.forEach(System.out::println);
    }

    static void cadastrarEmpresa() throws SQLException {
        System.out.print("Nome da empresa: ");
        String nome = scanner.nextLine();
        empresaRepository.salvar(new Empresa(nome));
        System.out.println("Empresa salva!");
    }

    static void cadastrarJogo() throws SQLException {
        System.out.print("Nome do jogo: ");
        String nome = scanner.nextLine();

        System.out.println("Gêneros disponíveis: ");
        for (Genero g : Genero.values()) System.out.println("- " + g);
        System.out.print("Gênero: ");
        Genero genero = Genero.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Ano de lançamento: ");
        int ano = Integer.parseInt(scanner.nextLine());

        System.out.print("ID da empresa: ");
        int empresaId = Integer.parseInt(scanner.nextLine());
        Empresa empresa = empresaRepository.buscarPorId(empresaId);

        jogoRepository.salvar(new Jogo(nome, genero, ano, empresa));
        System.out.println("Jogo salvo!");
    }

    static void listarJogos() throws SQLException {
        List<Jogo> jogos = jogoRepository.listarTodos();
        jogos.forEach(System.out::println);
    }

    static void cadastrarUsuario() throws SQLException {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        usuarioRepository.salvar(new Usuario(nome, email, senha));
        System.out.println("Usuário salvo!");
    }
}