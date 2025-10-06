package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginUser {

    boolean sessaoEstaAtiva;

    public Usuario RealizarLogin(){
        Scanner scanner = new Scanner(System.in);
        Map<String, Usuario> usuarios = new HashMap<>();
        System.out.println("Ola, bem vindo ao Sistema Suzart de Gerenciamento de Biblioteca interativa");

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

        sessaoEstaAtiva = true;
        return usuario;
    }

    public void AlterarUsuario(Usuario usuario){

        sessaoEstaAtiva = false;
        System.out.println("Trocando de usuário...");
    };

    public void EncerrarSessao(Usuario usuario){
        System.out.println("Encerrando o sistema. Ate mais ver, " + usuario.getNome() + "!");
        System.exit(0);
    }
}
