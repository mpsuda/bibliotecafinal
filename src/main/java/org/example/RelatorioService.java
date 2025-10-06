package org.example;

import java.util.List;

public class RelatorioService {
    public void gerarRelatorios(List<Usuario> usuarios, List<Catalogavel> itens) {
        for (var usuario : usuarios) {
            var relatorio = usuario.gerarRelatorio(itens);
            System.out.println(relatorio);
            System.out.println("-".repeat(40));
        }
    }
}