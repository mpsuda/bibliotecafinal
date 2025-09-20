package org.example;

public abstract class Livro implements Emprestavel, Catalogavel {
    protected final String titulo;
    protected final Autor autor;
    protected final Categoria categoria;
    protected EstadoEmprestimo estado;

    public Livro(String titulo, Autor autor, Categoria categoria) {
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.estado = EstadoEmprestimo.DISPONIVEL;
    }

    @Override
    public void emprestar() {
        estado = EstadoEmprestimo.EMPRESTADO;
    }

    @Override
    public void devolver() {
        estado = EstadoEmprestimo.DISPONIVEL;
    }

    @Override
    public boolean isEmprestado() {
        return estado == EstadoEmprestimo.EMPRESTADO;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public String getCategoria() {
        return categoria.nome();
    }

    @Override
    public String getAutor() {
        return autor.nome();
    }

    @Override
    public EstadoEmprestimo getEstadoEmprestimo() {
        return estado;
    }

    public abstract String getTipo();
}
