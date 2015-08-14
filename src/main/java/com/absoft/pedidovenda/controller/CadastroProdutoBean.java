package com.absoft.pedidovenda.controller;

import com.absoft.pedidovenda.model.Produto;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Diego Arantes
 */
@Named(value = "cadastroProdutoBean")
@RequestScoped
public class CadastroProdutoBean {

    private Produto produto;

    public CadastroProdutoBean() {
        produto = new Produto();
    }

    public void salvar() {

    }

    public Produto getProduto() {
        return produto;
    }

}
