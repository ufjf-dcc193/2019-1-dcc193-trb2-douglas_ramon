import java.util.List;

public class Avaliador {
    private String nome;
    private String email;
    private int codigo;
    List areaConhecimento;

    public Avaliador() {
    }

    public Avaliador(String nome, String email, int codigo, List areaConhecimento) {
        this.nome = nome;
        this.email = email;
        this.codigo = codigo;
        this.areaConhecimento = areaConhecimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public List getAreaConhecimento() {
        return areaConhecimento;
    }

    public void setAreaConhecimento(List areaConhecimento) {
        this.areaConhecimento = areaConhecimento;
    }

    @Override
    public String toString() {
        return "Avaliador{" + "nome=" + nome + ", email=" + email + ", codigo=" + codigo + ", areaConhecimento=" + areaConhecimento + '}';
    }
    
    
    
}
