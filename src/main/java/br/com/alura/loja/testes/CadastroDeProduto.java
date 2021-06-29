package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.CategoriaId;
import br.com.alura.loja.utils.JPAUtil;
import br.com.alura.loja.modelo.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {

    public static void main(String[] args) {
        cadastrarProduto();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);

        Produto p = produtoDao.buscarPorId(1L);
        System.out.println(p.getPreco());

        List<Produto> todos = produtoDao.buscarTodos();
        todos.forEach(p2 -> System.out.println(p2.getNome()));

        List<Produto> nome = produtoDao.buscarPorNome("Xiaomi Redmi");
        nome.forEach(p3 -> System.out.println(p3.getNome()));

        List<Produto> nome1 = produtoDao.buscarPorNomeDaCategoria("CELULARES");
        nome1.forEach(p4 -> System.out.println(p4.getNome()));

        BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("Xiaomi Redmi");
        System.out.println("Preco do Produto : " + precoDoProduto);
    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);

        em.getTransaction().commit();

        em.find(Categoria.class, new CategoriaId("CELULARES", "xpto"));

        em.close();
//        em.persist(celulares);
//        celulares.setNome("XPTO");
//
//        em.flush();
//        em.clear();

//        celulares = em.merge(celulares);
//        celulares.setNome("1234");
//        em.flush();
//        em.remove(celulares);
//        em.flush();
    }

}
