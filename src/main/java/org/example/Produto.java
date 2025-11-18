package org.example;

public class Produto {

    private int valor; // Se campoNome for o nome do produto
    private String descricao;
    // O seu BD está salvando 'preco', então vamos incluir um campo para ele.
    //private double preco;

    // Construtor
    public Produto(String descricao, int valor) {
        this.valor = valor;
        this.descricao = descricao;
        //não usaremos agora -----this.preco = preco;
    }

    public int getValor() {
        return valor;
    }
    public void setValor(int valor) {
        this.valor = valor;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
