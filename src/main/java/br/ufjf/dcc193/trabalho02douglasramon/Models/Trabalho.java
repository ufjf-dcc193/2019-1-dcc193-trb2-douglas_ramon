
public class Trabalho {
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
        return "Trabalho{" + "titulo=" + titulo + ", descricao=" + descricao + ", url=" + url + ", areaConhecimento=" + areaConhecimento + '}';
    }
    
    
    
}
