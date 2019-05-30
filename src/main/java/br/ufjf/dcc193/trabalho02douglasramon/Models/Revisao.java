package br.ufjf.dcc193.trabalho02douglasramon.Models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @authors douglas e ramon
 */
@Entity
@Table(name = "revisao")
public class Revisao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private int nota;
    private String descricao;
    private String status;
    @OneToOne(fetch = FetchType.EAGER)
    private Avaliador avaliador;
    @OneToOne(fetch = FetchType.EAGER)
    private Trabalho trabalho;

    /**
     *
     */
    public Revisao() {
    }

    /**
     *
     * @param nota
     * @param descricao
     * @param status
     */
    public Revisao(int nota, String descricao, String status) {
        this.nota = nota;
        this.descricao = descricao;
        this.status = status;
    }

    /**
     *
     * @param nota
     * @param status
     */
    public Revisao(int nota, String status) {
        this.nota = nota;
        this.status = status;
    }

    /**
     *
     * @param nota
     * @param descricao
     * @param status
     * @param trabalho
     * @param avaliador
     */
    public Revisao(int nota, String descricao, String status, Trabalho trabalho, Avaliador avaliador) {
        this.avaliador = avaliador;
        this.trabalho = trabalho;
        this.nota = nota;
        this.descricao = descricao;
        this.status = status;
    }

    /**
     *
     * @param nota
     * @param status
     * @param trabalho
     * @param avaliador
     */
    public Revisao(int nota, String status, Trabalho trabalho, Avaliador avaliador) {
        this.avaliador = avaliador;
        this.trabalho = trabalho;
        this.nota = nota;
        this.status = status;
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

    /**
     *
     * @return
     */
    public Avaliador getAvaliador() {
        return avaliador;
    }

    /**
     *
     * @param avaliador
     */
    public void setAvaliador(Avaliador avaliador) {
        this.avaliador = avaliador;
    }

    /**
     *
     * @return
     */
    public Trabalho getTrabalho() {
        return trabalho;
    }

    /**
     *
     * @param trabalho
     */
    public void setTrabalho(Trabalho trabalho) {
        this.trabalho = trabalho;
    }

    /**
     *
     * @return
     */
    public int getNota() {
        return nota;
    }

    /**
     *
     * @param nota
     */
    public void setNota(int nota) {
        this.nota = nota;
    }

    /**
     *
     * @return
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     *
     * @param descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     *
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Revisao{" + "avaliador=" + avaliador + ", trabalho=" + trabalho + ", nota=" + nota + ", descricao="
                + descricao + ", status=" + status + '}';
    }

}
