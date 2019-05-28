package br.ufjf.dcc193.trabalho02douglasramon.Models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @authors douglas e ramon
 */
@Entity
@Table(name = "avaliador")
public class Avaliador {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nome;
    private String email;
    private int codigo;
    @OneToMany(fetch = FetchType.EAGER)
    private List<AreaConhecimento> areaConhecimento;

    /**
     *
     */
    public Avaliador() {
    }

    /**
     *
     * @param nome
     * @param email
     * @param codigo
     */
    public Avaliador(String nome, String email, int codigo) {
        this.nome = nome;
        this.email = email;
        this.codigo = codigo;
    }

    /**
     *
     * @param nome
     * @param email
     * @param codigo
     * @param areaConhecimento
     */
    public Avaliador(String nome, String email, int codigo, List<AreaConhecimento> areaConhecimento) {
        this.nome = nome;
        this.email = email;
        this.codigo = codigo;
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
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     *
     * @param codigo
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     *
     * @return
     */
    public List<AreaConhecimento> getAreaConhecimento() {
        return areaConhecimento;
    }

    /**
     *
     * @param areaConhecimento
     */
    public void setAreaConhecimento(List<AreaConhecimento> areaConhecimento) {
        this.areaConhecimento = areaConhecimento;
    }

    @Override
    public String toString() {
        return "Avaliador{" + "nome=" + nome + ", email=" + email + ", codigo=" + codigo + ", areaConhecimento="
                + areaConhecimento + '}';
    }

}
