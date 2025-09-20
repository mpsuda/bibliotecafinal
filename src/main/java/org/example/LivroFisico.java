package org.example;

public class LivroFisico extends Livro {
    private final int numeroPaginas;

    public LivroFisico(String titulo, Autor autor, Categoria categoria, int numeroPaginas) {
        super(titulo, autor, categoria);
        this.numeroPaginas = numeroPaginas;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    @Override
    public String getTipo() {
        return "Fisico";
    }
}


