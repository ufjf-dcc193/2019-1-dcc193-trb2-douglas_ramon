package br.ufjf.dcc193.trabalho02douglasramon.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "trabalho")
public class Trabalho {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String titulo;
    private String descricao;
    private String url;
    private String areaConhecimento;

    public Trabalho() {
    }

    public Trabalho(String titulo, String descricao, String url, String areaConhecimento) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
        this.areaConhecimento = areaConhecimento;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAreaConhecimento() {
        return areaConhecimento;
    }

    public void setAreaConhecimento(String areaConhecimento) {
        this.areaConhecimento = areaConhecimento;
    }

    @Override
    public String toString() {
        return "Trabalho{" + "titulo=" + titulo + ", descricao=" + descricao + ", url=" + url + ", areaConhecimento="
                + areaConhecimento + '}';
    }

}
