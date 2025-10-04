package org.example;

public class Revista implements Emprestavel, Catalogavel {
    private final String titulo;
    private final Categoria categoria;
    //private final String editora;
    private EstadoEmprestimo estado;

    public Revista(String titulo, Categoria categoria) {
        this.titulo = titulo;
        this.categoria = categoria;
       // this.editora = editora;
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
        return "N/A";
    }

    @Override
    public EstadoEmprestimo getEstadoEmprestimo() {
        return estado;
    }
}


