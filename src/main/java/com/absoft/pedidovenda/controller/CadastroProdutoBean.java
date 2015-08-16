package com.absoft.pedidovenda.controller;

import com.absoft.pedidovenda.model.Categoria;
import com.absoft.pedidovenda.model.Produto;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Diego Arantes
 */
@Named(value = "cadastroProdutoBean")
@ViewScoped
public class CadastroProdutoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Produto produto;

    private List<Categoria> categoriaRaizes;

    public CadastroProdutoBean() {
        produto = new Produto();
    }

    public void inicializar() {
        System.out.println("Inicializando ....");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PedidoPU");
        EntityManager manager = factory.createEntityManager();

        categoriaRaizes = manager.createQuery("from Categoria", Categoria.class).getResultList();

        manager.close(); //Fechar Entity Manager
    }

    public void salvar() {

    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Categoria> getCategoriaRaizes() {
        return categoriaRaizes;
    }

}
