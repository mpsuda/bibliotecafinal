package org.example;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private final List<Catalogavel> itens = new ArrayList<>();

    public void adicionarItem(Catalogavel item) {
        itens.add(item);
    }

    public List<Catalogavel> getItens() {
        return itens;
    }
}

