package org.example;

import java.util.ArrayList;
import java.util.List;

public sealed abstract class Usuario permits Bibliotecario, UsuarioComum {
    protected final String nome;

    public Usuario(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public abstract String gerarRelatorio(List<Catalogavel> itens);

    protected final List<Catalogavel> itensEmprestados = new ArrayList<>();

    public void registrarEmprestimo(Catalogavel item) {
        itensEmprestados.add(item);
    }
    public void devolverItem(Catalogavel item) {
        itensEmprestados.remove(item);
    }
    public List<Catalogavel> getItensEmprestados() {
        return itensEmprestados;
    }
}