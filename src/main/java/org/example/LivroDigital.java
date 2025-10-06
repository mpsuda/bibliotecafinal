package org.example;

public class LivroDigital extends Livro {
    private final double tamanhoMB;

    public LivroDigital(String titulo, Autor autor, Categoria categoria, double tamanhoMB) {
        super(titulo, autor, categoria);
        this.tamanhoMB = tamanhoMB;
    }

    public double getTamanhoMB() {
        return tamanhoMB;
    }

    @Override
    public String getTipo() {
        return "Digital";
    }
}