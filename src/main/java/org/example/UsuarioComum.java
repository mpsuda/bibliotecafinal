package org.example;

import java.util.List;

public final class UsuarioComum extends Usuario {
    public UsuarioComum(String nome) {
        super(nome);
    }

    @Override
    public String gerarRelatorio(List<Catalogavel> itens) {
        var builder = new StringBuilder("""
        Relatorio Pessoal
        Usuario: %s (Comum)
        ----------------------------------
        """.formatted(nome));

        builder.append("Itens emprestados:\n");
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

