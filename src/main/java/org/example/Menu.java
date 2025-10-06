package org.example;

import java.util.*;

public class Menu {

    public int DefinirOpcao(Usuario usuariologado){

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nMenu de acoes:");
        if (usuariologado instanceof Bibliotecario) {
            System.out.println("1 - Cadastrar item");
        }
        System.out.println("2 - Emprestar item");
        System.out.println("3 - Gerar relatorio");
        System.out.println("4 - Processar todos os itens");
        System.out.println("5 - Trocar de usuario");
        System.out.println("6 - Devolver item");
        System.out.println("0 - Sair do sistema");

        System.out.print("Escolha uma opcao: ");
        Integer escolha = scanner.nextInt();

        return escolha;
    }

    public void RodarSistema(){
        LoginUser sessao = new LoginUser();
        Usuario usuario = sessao.RealizarLogin();
        Biblioteca biblioteca = new Biblioteca();

        while (true) {


            while (sessao.sessaoEstaAtiva) {


                switch (DefinirOpcao(usuario)) {
                    case 1:
                        biblioteca.CadastrarLivro(usuario);
                        break;

                    case 2:
                        biblioteca.EmprestarLivro(usuario);
                        break;

                    case 3:
                        biblioteca.GerarRelatorio(usuario);
                        break;

                    case 4:
                        biblioteca.ProcessarItens(usuario);
                        break;

                    case 5:
                        sessao.AlterarUsuario(usuario);
                        break;

                    case 6:
                        biblioteca.DevolverItens(usuario);
                        break;

                    case 0:
                        sessao.EncerrarSessao(usuario);
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }
            }
        }
    }
}
