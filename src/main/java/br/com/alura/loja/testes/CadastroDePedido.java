package br.com.alura.loja.testes;

import br.com.alura.loja.dao.*;
import br.com.alura.loja.modelo.*;
import br.com.alura.loja.utils.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDePedido {

    public static void main(String[] args) {

        popularBancoDeDados();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        ClienteDao clienteDao = new ClienteDao(em);
        LivroDao livroDao = new LivroDao(em);
        InformaticaDao informaticaDao = new InformaticaDao(em);

        Produto produto = produtoDao.buscarPorId(1L);
        Cliente cliente = clienteDao.buscarPorId(1L);
        Livro livro = livroDao.buscarPorId(1L);
        Informatica informatica = informaticaDao.buscarPorId(1L);
        em.getTransaction().begin();


        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10, pedido, produto));

        PedidoDao pedidoDao = new PedidoDao(em);
        pedidoDao.cadastrar(pedido);

        em.getTransaction().commit();

        BigDecimal totalVendido = pedidoDao.valorTotalVendido();
        System.out.println("VALOR TOTAL: " + totalVendido);

        List<Object[]> relatorio = pedidoDao.relatorioDeVendas();
        for (Object[] obj: relatorio) {
            System.out.println(obj[0]);
            System.out.println(obj[1]);
            System.out.println(obj[2]);
        }

    }

    private static void popularBancoDeDados() {
        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
        Livro livro = new Livro("Dom Casmurro", 300);
        Informatica informatica = new Informatica("Dell", "D35");

        Cliente cliente = new Cliente("Rodrigo", "123456");

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ClienteDao clienteDao = new ClienteDao(em);
        LivroDao livroDao = new LivroDao(em);
        InformaticaDao informaticaDao = new InformaticaDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);
        clienteDao.cadastrar(cliente);
        livroDao.cadastrar(livro);
        informaticaDao.cadastrar(informatica);
        em.getTransaction().commit();
}
}