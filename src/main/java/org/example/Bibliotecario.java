package org.example;

import java.util.List;

public final class Bibliotecario extends Usuario {
    public Bibliotecario(String nome) {
        super(nome);
    }

    @Override
    public String gerarRelatorio(List<Catalogavel> itens) {
        var builder = new StringBuilder("""
        Relatorio Geral da Biblioteca
        Usuario: %s (Bibliotecario)
        ----------------------------------
        """.formatted(nome));

        for (var item : itens) {
            builder.append("""
            - Titulo: %s
              Autor: %s
              Categoria: %s
              Estado: %s
            """.formatted(item.getTitulo(), item.getAutor(), item.getCategoria(), item.getEstadoEmprestimo().descricao()));
        }

        builder.append("\nItens emprestados pelo bibliotecario:\n");
        if (itensEmprestados.isEmpty()) {
            builder.append("Nenhum item emprestado.\n");
        } else {
            for (var item : itensEmprestados) {
                builder.append("- %s (%s)\n".formatted(item.getTitulo(), item.getCategoria()));
            }
        }

        return builder.toString();
    }


}
