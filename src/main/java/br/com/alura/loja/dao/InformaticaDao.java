package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Informatica;
import br.com.alura.loja.modelo.Livro;

import javax.persistence.EntityManager;

public class InformaticaDao {

    private EntityManager em;

    public InformaticaDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Informatica informatica) {
        this.em.persist(informatica);
    }

    public Informatica buscarPorId(Long id) {
        return em.find(Informatica.class, id);
    }

}