package org.example;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();
        Map<String, Usuario> usuarios = new HashMap<>();
        System.out.println("Ola, bem vindo ao Sistema Suzart de Gerenciamento de Biblioteca interativa");

        while (true) {
            // Login do usuário
            System.out.println("\nLogin de usuario");
            System.out.print("Digite seu nome: ");
            String nome = scanner.nextLine();

            System.out.print("Voce eh bibliotecario? (s/n): ");
            boolean isBibliotecario = scanner.nextLine().trim().equalsIgnoreCase("s");

            Usuario usuario;

            if (usuarios.containsKey(nome)) {
                usuario = usuarios.get(nome);
                System.out.println("🔄 Usuário reconhecido. Dados carregados.");
            } else {
                usuario = isBibliotecario
                        ? new Bibliotecario(nome)
                        : new UsuarioComum(nome);
                usuarios.put(nome, usuario);
                System.out.println("✅ Novo usuário criado.");
            }


//            Usuario usuario = isBibliotecario
//                    ? new Bibliotecario(nome)
//                    : new UsuarioComum(nome);

            boolean continuar = true;
            while (continuar) {
                System.out.println("\nMenu de acoes:");
                if (usuario instanceof Bibliotecario) {
                    System.out.println("1 - Cadastrar item");
                }
                System.out.println("2 - Emprestar item");
                System.out.println("3 - Gerar relatorio");
                System.out.println("4 - Processar todos os itens");
                System.out.println("5 - Trocar de usuario");
                System.out.println("6 - Devolver item");
                System.out.println("0 - Sair do sistema");

                System.out.print("Escolha uma opcao: ");
                String escolha = scanner.nextLine();

                switch (escolha) {
                    case "1":
                        if (usuario instanceof Bibliotecario) {
                            System.out.print("Quantos itens deseja cadastrar? ");
                            var total = Integer.parseInt(scanner.nextLine());

                            for (int i = 0; i < total; i++) {
                                System.out.println("\nCadastro do item #" + (i + 1));
                                var tipo = "";
                                while (true) {
                                    System.out.print("Tipo (livro fisico/livro digital/revista): ");
                                    tipo = scanner.nextLine().trim().toLowerCase();

                                    if (tipo.equals("livro fisico") || tipo.equals("livro digital") || tipo.equals("revista")) {
                                        break; // tipo válido, sai do loop
                                    } else {
                                        System.out.println("te orienta rapah. Faz o trem certo.");
                                    }
                                }
                                System.out.print("Titulo: ");
                                var titulo = scanner.nextLine();

                                System.out.print("Categoria: ");
                                var categoria = new Categoria(scanner.nextLine());

                                switch (tipo) {
                                    case "livro fisico" -> {
                                        System.out.print("Autor: ");
                                        Autor autor = new Autor(scanner.nextLine());
                                        System.out.print("Numero de paginas: ");
                                        int paginas = Integer.parseInt(scanner.nextLine());
                                        LivroFisico livroFisico = new LivroFisico(titulo, autor, categoria, paginas);
                                        biblioteca.adicionarItem(livroFisico);
                                        System.out.println("✅ Livro fisico cadastrado.");
                                    }
                                    case "livro digital" -> {
                                        System.out.print("Autor: ");
                                        Autor autor = new Autor(scanner.nextLine());
                                        System.out.print("Tamanho em MB: ");
                                        double tamanho = Double.parseDouble(scanner.nextLine());
                                        LivroDigital livroDigital = new LivroDigital(titulo, autor, categoria, tamanho);
                                        biblioteca.adicionarItem(livroDigital);
                                        System.out.println("✅ Livro digital cadastrado.");
                                    }
                                    case "revista" -> {
//                                        System.out.print("Editora: ");
//                                        String editora = scanner.nextLine();
                                        Revista revista = new Revista(titulo, categoria);
                                        biblioteca.adicionarItem(revista);
                                        System.out.println("✅ Revista cadastrada.");
                                    }
                                    default -> System.out.println("Tipo invalido. Item ignorado.");
                                }
                            }
                        } else {
                            System.out.println("Apenas bibliotecarios podem cadastrar itens.");
                        }
                        break;

                    case "2":
                        System.out.print("Digite o titulo do item para emprestimo: ");
                        String titulo = scanner.nextLine();

                        Optional<Catalogavel> itemOpt = biblioteca.getItens().stream()
                                .filter(i -> i.getTitulo().equalsIgnoreCase(titulo))
                                .findFirst();

                        if (itemOpt.isPresent()) {
                            Catalogavel item = itemOpt.get();
                            if (item instanceof Emprestavel emprestavel) {
                                if (!emprestavel.isEmprestado()) {
                                    emprestavel.emprestar();
                                    usuario.registrarEmprestimo(item); // registra o empréstimo no usuário
                                    System.out.println("Item emprestado com sucesso.");

                                } else {
                                    System.out.println("Item ja esta emprestado.");
                                }
                            } else {
                                System.out.println("Este item não pode ser emprestado.");
                            }
                        } else {
                            System.out.println("Item não encontrado.");
                        }
                        break;

                    case "3":
                        System.out.println("\nRelatorio do usuario:");
                        System.out.println(usuario.gerarRelatorio(biblioteca.getItens()));
                        break;

                    case "4":
                        System.out.println("\nProcessando todos os itens:");
                        for (Catalogavel item : biblioteca.getItens()) {
                            ProcessadorDeItem.processar(item);
                        }
                        break;

                    case "5":
                        System.out.println("Trocando de usuário...");
                        continuar = false;
                        break;

                    case "6":
                        List<Catalogavel> emprestados = usuario.getItensEmprestados();

                        if (emprestados == null || emprestados.isEmpty()) {
                            System.out.println("Você não possui itens emprestados.");
                            break;
                        }

                        System.out.println("\nItens emprestados:");
                        for (int i = 0; i < emprestados.size(); i++) {
                            Catalogavel item = emprestados.get(i);
                            System.out.printf("%d - %s%n", i + 1, item.getTitulo());
                        }

                        System.out.print("Digite o número do item que deseja devolver: ");
                        String entrada = scanner.nextLine();
                        int escolhaItem;

                        try {
                            escolhaItem = Integer.parseInt(entrada);
                        } catch (NumberFormatException e) {
                            System.out.println("Entrada inválida. Digite um número.");
                            break;
                        }

                        if (escolhaItem < 1 || escolhaItem > emprestados.size()) {
                            System.out.println("Número fora do intervalo.");
                            break;
                        }

                        Catalogavel itemParaDevolver = emprestados.get(escolhaItem - 1);

                        if (itemParaDevolver instanceof Emprestavel emprestavel) {
                            if (emprestavel.isEmprestado()) {
                                emprestavel.devolver(); // atualiza o estado
                                usuario.devolverItem(itemParaDevolver); // remove da lista do usuário
                                System.out.println("Item devolvido com sucesso.");
                            } else {
                                System.out.println("Este item já está disponível.");
                            }
                        } else {
                            System.out.println("Este item não pode ser devolvido.");
                        }
                        break;

                    case "0":
                        System.out.println("Encerrando o sistema. Ate mais ver, " + usuario.getNome() + "!");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }
            }
        }
    }
}