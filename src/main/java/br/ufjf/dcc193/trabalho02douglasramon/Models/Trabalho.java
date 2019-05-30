package br.ufjf.dcc193.trabalho02douglasramon.Models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 *
 * @authors douglas e ramon
 */
@Entity
@Table(name = "trabalho")
public class Trabalho {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank(message = "É preciso um título!")
    private String titulo;
    @NotBlank(message = "É preciso uma descrição!")
    private String descricao;
    @NotBlank(message = "É preciso uma url!")
    private String url;    
    @OneToOne(fetch = FetchType.EAGER)
    private AreaConhecimento areaConhecimento;

    /**
     *
     */
    public Trabalho() {
    }

    /**
     *
     * @param titulo
     * @param descricao
     * @param url
     * @param areaConhecimento
     */
    public Trabalho(String titulo, String descricao, String url, AreaConhecimento areaConhecimento) {
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

    /**
     *
     * @return
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     *
     * @param titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
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
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     */
    public AreaConhecimento getAreaConhecimento() {
        return areaConhecimento;
    }

    /**
     *
     * @param areaConhecimento
     */
    public void setAreaConhecimento(AreaConhecimento areaConhecimento) {
        this.areaConhecimento = areaConhecimento;
    }

    @Override
    public String toString() {
        return "Trabalho{" + "titulo=" + titulo + ", descricao=" + descricao + ", url=" + url + ", areaConhecimento="
                + areaConhecimento + '}';
    }

}
