package br.ufjf.dcc193.trabalho02douglasramon.Models;

public class Revisao {
    private Avaliador avaliador;
    private Trabalho trabalho;
    private int nota;
    private String descricao;
    private String opcoes;

    public Revisao() {
    }

    public Revisao(Avaliador avaliador, Trabalho trabalho, int nota, String descricao, String opcoes) {
        this.avaliador = avaliador;
        this.trabalho = trabalho;
        this.nota = nota;
        this.descricao = descricao;
        this.opcoes = opcoes;
    }

    public Revisao(Avaliador avaliador, Trabalho trabalho, int nota, String opcoes) {
        this.avaliador = avaliador;
        this.trabalho = trabalho;
        this.nota = nota;        
        this.opcoes = opcoes;
    }

    public Avaliador getAvaliador() {
        return avaliador;
    }

    public void setAvaliador(Avaliador avaliador) {
        this.avaliador = avaliador;
    }

    public Trabalho getTrabalho() {
        return trabalho;
    }

    public void setTrabalho(Trabalho trabalho) {
        this.trabalho = trabalho;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getOpcoes() {
        return opcoes;
    }

    public void setOpcoes(String opcoes) {
        this.opcoes = opcoes;
    }

    @Override
    public String toString() {
        return "Revisao{" + "avaliador=" + avaliador + ", trabalho=" + trabalho + ", nota=" + nota + ", descricao=" + descricao + ", opcoes=" + opcoes + '}';
    }
    
    
    
}
