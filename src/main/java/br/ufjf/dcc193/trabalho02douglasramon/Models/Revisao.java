package br.ufjf.dcc193.trabalho02douglasramon.Models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "revisao")
public class Revisao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    private Avaliador avaliador;
    @OneToOne(fetch = FetchType.EAGER)
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

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
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
        return "Revisao{" + "avaliador=" + avaliador + ", trabalho=" + trabalho + ", nota=" + nota + ", descricao="
                + descricao + ", opcoes=" + opcoes + '}';
    }

}
