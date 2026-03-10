package br.com.alura.literalura.Principal;

import br.com.alura.literalura.Controller.Client;
import br.com.alura.literalura.Model.*;
import br.com.alura.literalura.Model.Autor.AutorEntity;
import br.com.alura.literalura.Model.Livro.Livro;
import br.com.alura.literalura.Model.Livro.LivroEntity;
import br.com.alura.literalura.Repository.AutorRepository;
import br.com.alura.literalura.Repository.LivroRepository;
import br.com.alura.literalura.Service.ConverteDados;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner scan = new Scanner(System.in);
    private final Client cliente = new Client();
    private final ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=";
    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    public Principal(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void menuExibir() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("""
                    Escolha o número de sua opção:
                    
                    1- buscar livro pelo título
                    2- listar livros registrados
                    3- listar autores registrados
                    4- listar autores vivos em um determinado ano
                    5- listar livros em um determinado idioma
                    
                    0 - sair
                    """);
            try {
                opcao = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Digite um número.");
                continue;
            }
            switch (opcao) {
                case 1 -> buscarLivroPeloTitulo();
                case 2 -> listarLivrosRegistrados();
                case 3 -> listarAutoresRegistrados();
                case 4 -> listarAutoresVivos();
                case 5 -> listarLivrosPorIdioma();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void buscarLivroPeloTitulo() {
        System.out.print("Digite o título que deseja buscar: ");
        var titulo = scan.nextLine();
        String tituloRefatorado = titulo.replace(" ", "%20").toLowerCase();

        try {
            String json = cliente.obterObra(ENDERECO + tituloRefatorado);
            Resposta resposta = conversor.obterDados(json, Resposta.class);

            if (resposta.resultados() == null || resposta.resultados().isEmpty()) {
                System.out.println("Livro não encontrado.");
                return;
            }
            Livro livroDTO = resposta.resultados().get(0);

            //verifica se o livro já está registrado
            Optional<LivroEntity> livroExistente = livroRepository.findByTituloIgnoreCase(livroDTO.titulo());
            if (livroExistente.isPresent()) {
                System.out.println("Este livro já está registrado!");
                System.out.println(livroExistente.get());
                return;
            }

            // cria a entidade e verifica se o autor já existe
            LivroEntity livroEntity = new LivroEntity(livroDTO);
            if (livroEntity.getAutor() != null) {
                Optional<AutorEntity> autorExistente = autorRepository.findByNome(livroEntity.getAutor().getNome());
                if (autorExistente.isPresent()) {
                    livroEntity.setAutor(autorExistente.get());
                }
            }
            livroRepository.save(livroEntity);
            System.out.println(livroEntity);

        } catch (IOException | InterruptedException e) {
            System.out.println("Erro ao buscar livro: " + e.getMessage());
        }
    }

    private void listarLivrosRegistrados() {
        List<LivroEntity> livros = livroRepository.findAll();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro registrado.");
            return;
        }
        livros.forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        List<AutorEntity> autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor registrado.");
            return;
        }
        autores.forEach(a -> {
            System.out.println(a);
            System.out.println("  Livros: " +
                    a.getLivros().stream().map(LivroEntity::getTitulo).toList());
            System.out.println();
        });
    }

    private void listarAutoresVivos() {
        System.out.print("Digite o ano para buscar autores vivos: ");
        try {
            int ano = Integer.parseInt(scan.nextLine());
            List<AutorEntity> autores = autorRepository
                    .findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual(ano, ano);
            if (autores.isEmpty()) {
                System.out.println("Nenhum autor vivo encontrado no ano " + ano + ".");
                return;
            }
            autores.forEach(a -> {
                System.out.println(a);
                System.out.println("Livros: " +
                        a.getLivros().stream().map(LivroEntity::getTitulo).toList());
                System.out.println();
            });
        } catch (NumberFormatException e) {
            System.out.println("Ano inválido!");
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.println("""
                Digite um idioma para realizar a buscar:
                es - espanhol
                en - inglês
                fr - francês
                pt - português
                """);
        var idioma = scan.nextLine().trim();
        List<LivroEntity> livros = livroRepository.findByIdiomaIgnoreCase(idioma);
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado no idioma informado.");
            return;
        }
        livros.forEach(System.out::println);
    }
}
