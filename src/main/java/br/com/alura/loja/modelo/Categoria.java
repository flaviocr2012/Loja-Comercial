package br.com.alura.loja.modelo;

import javax.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private CategoriaId id;

    @Column(insertable = false, updatable = false)
    private String nome;

    public Categoria(String nome) {
        this.id =  new CategoriaId(nome, "xpto");
    }

    public Categoria() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}


