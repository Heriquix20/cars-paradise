package br.com.hcgv.carsparadise.carsparadise.models;

public class Modelo {

    private String nome;
    private Integer codigo;

    public Modelo(String nome, Integer codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Modelo{" +
                "nome='" + nome + '\'' +
                ", codigo=" + codigo +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
}
