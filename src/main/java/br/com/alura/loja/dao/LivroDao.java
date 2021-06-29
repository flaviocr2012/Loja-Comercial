package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.Livro;

import javax.persistence.EntityManager;

public class LivroDao {

    private EntityManager em;

    public LivroDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Livro livro) {
        this.em.persist(livro);
    }

    public Livro buscarPorId(Long id) {
        return em.find(Livro.class, id);
    }


}