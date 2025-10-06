package org.example;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BibliotecaTest {

    private Biblioteca biblioteca;
    private Usuario bibliotecario;

    @Mock
    private Scanner scanner;

    @BeforeEach
    void setUp() {
        biblioteca = new Biblioteca();
        bibliotecario = new Bibliotecario("Bibliotecario Teste");
    }

    @Test
    void testCadastrarLivro() {
        when(scanner.nextLine()).thenReturn("livro fisico", "Teste Livro", "Categoria Teste", "Autor Teste", "100");
        biblioteca.CadastrarLivro(bibliotecario);
        List<Catalogavel> itens = biblioteca.getItens();
        assertEquals(1, itens.size(), "O número de itens cadastrados deve ser 1");
        assertEquals("Teste Livro", itens.get(0).getTitulo(), "Título do livro deve ser 'Teste Livro'");
        assertEquals("Categoria Teste", itens.get(0).getCategoria(), "Categoria do livro deve ser 'Categoria Teste'");
    }

    @Test
    void testEmprestarLivro() {
        LivroFisico livro = new LivroFisico("Teste Livro", new Autor("Autor Teste"), new Categoria("Categoria Teste"), 100);
        biblioteca.adicionarItem(livro);
        when(scanner.nextLine()).thenReturn("Teste Livro");
        biblioteca.EmprestarLivro(bibliotecario);
        assertTrue(livro.isEmprestado(), "O livro deve aparecer como emprestado após a operação");
    }

    @Test
    void testGerarRelatorio() {
        LivroFisico livro = new LivroFisico("Teste Livro", new Autor("Autor Teste"), new Categoria("Categoria Teste"), 100);
        biblioteca.adicionarItem(livro);
        String relatorio = bibliotecario.gerarRelatorio(biblioteca.getItens());
        assertTrue(relatorio.contains("Teste Livro"), "O relatório deve conter o título do livro");
        assertTrue(relatorio.contains("Autor Teste"), "O relatório deve conter o autor do livro");
    }

    @Test
    void testProcessarItens() {
        LivroFisico livro = new LivroFisico("Teste Livro", new Autor("Autor Teste"), new Categoria("Categoria Teste"), 100);
        biblioteca.adicionarItem(livro);
        assertDoesNotThrow(() -> biblioteca.ProcessarItens(bibliotecario), "Não deve haver exceção ao processar itens");
    }

    @Test
    void testDevolverItens() {
        LivroFisico livro = new LivroFisico("Teste Livro", new Autor("Autor Teste"), new Categoria("Categoria Teste"), 100);
        biblioteca.adicionarItem(livro);
        biblioteca.EmprestarLivro(bibliotecario);
        when(scanner.nextLine()).thenReturn("1");
        biblioteca.DevolverItens(bibliotecario);
        assertFalse(livro.isEmprestado(), "O livro deve ficar disponível após a devolução");
    }
}