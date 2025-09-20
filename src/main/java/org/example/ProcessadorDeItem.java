package org.example;

public class ProcessadorDeItem {
    public static void processar(Catalogavel item) {
        switch (item) {
            case LivroFisico lf -> System.out.printf("""
                Livro Físico: %s
                Autor: %s
                Categoria: %s
                Paginas: %d
                Estado: %s
                ------------------------
                """, lf.getTitulo(), lf.getAutor(), lf.getCategoria(), lf.getNumeroPaginas(), lf.getEstadoEmprestimo().descricao());

            case LivroDigital ld -> System.out.printf("""
                Livro Digital: %s
                Autor: %s
                Categoria: %s
                Tamanho: %.2fMB
                Estado: %s
                ------------------------
                """, ld.getTitulo(), ld.getAutor(), ld.getCategoria(), ld.getTamanhoMB(), ld.getEstadoEmprestimo().descricao());

            case Revista revista -> System.out.printf("""
                Revista: %s
                Categoria: %s
                Estado: %s
                ------------------------
                """, revista.getTitulo(), revista.getCategoria(), revista.getEstadoEmprestimo().descricao());

            default -> System.out.println("Tipo de item desconhecido.");
        }
    }
}
