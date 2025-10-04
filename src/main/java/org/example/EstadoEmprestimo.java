package org.example;

public enum EstadoEmprestimo {
    DISPONIVEL, EMPRESTADO, ATRASADO, RESERVADO;

    public String descricao() {
        return switch (this) {
            case DISPONIVEL -> "Disponivel para emprestimo";
            case EMPRESTADO -> "Emprestado no momento";
            case ATRASADO -> "Emprestimo atrasado";
            case RESERVADO -> "Reservado por outro usuario";
        };
    }
}