package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Biblioteca {
    private final List<Catalogavel> itens = new ArrayList<>();

    public void adicionarItem(Catalogavel item) {
        itens.add(item);
    }

    public List<Catalogavel> getItens() {
        return itens;
    }


    public void CadastrarLivro (Usuario usuario){
        Scanner scanner = new Scanner(System.in);


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
                        this.adicionarItem(livroFisico);
                        System.out.println("✅ Livro fisico cadastrado.");
                    }
                    case "livro digital" -> {
                        System.out.print("Autor: ");
                        Autor autor = new Autor(scanner.nextLine());
                        System.out.print("Tamanho em MB: ");
                        double tamanho = Double.parseDouble(scanner.nextLine());
                        LivroDigital livroDigital = new LivroDigital(titulo, autor, categoria, tamanho);
                        this.adicionarItem(livroDigital);
                        System.out.println("✅ Livro digital cadastrado.");
                    }
                    case "revista" -> {
//                                        System.out.print("Editora: ");
//                                        String editora = scanner.nextLine();
                        Revista revista = new Revista(titulo, categoria);
                        this.adicionarItem(revista);
                        System.out.println("✅ Revista cadastrada.");
                    }
                    default -> System.out.println("Tipo invalido. Item ignorado.");
                }
            }
        } else {
            System.out.println("Apenas bibliotecarios podem cadastrar itens.");
        }
    }

    public void EmprestarLivro (Usuario usuario){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o titulo do item para emprestimo: ");
        String titulo = scanner.nextLine();

        Optional<Catalogavel> itemOpt = this.getItens().stream()
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
    }

    public void GerarRelatorio (Usuario usuario){
        System.out.println("\nRelatorio do usuario:");
        System.out.println(usuario.gerarRelatorio(this.getItens()));
    }

    public void ProcessarItens (Usuario usuario){
        System.out.println("\nProcessando todos os itens:");
        for (Catalogavel item : this.getItens()) {
            ProcessadorDeItem.processar(item);
        }
    }

    public void DevolverItens (Usuario usuario){
        Scanner scanner = new Scanner(System.in);

        List<Catalogavel> emprestados = usuario.getItensEmprestados();

        if (emprestados == null || emprestados.isEmpty()) {
            System.out.println("Você não possui itens emprestados.");
        }

        System.out.println("\nItens emprestados:");
        for (int i = 0; i < emprestados.size(); i++) {
            Catalogavel item = emprestados.get(i);
            System.out.printf("%d - %s%n", i + 1, item.getTitulo());
        }

        System.out.print("Digite o número do item que deseja devolver: ");
        String entrada = scanner.nextLine();
        int escolhaItem = 0;

        try {
            escolhaItem = Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Digite um número.");
        }

        if (escolhaItem < 1 || escolhaItem > emprestados.size()) {
            System.out.println("Número fora do intervalo.");
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

    }

}

