package com.absoft.pedidovenda.controller;

import com.absoft.pedidovenda.model.Produto;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Diego Arantes
 */
@Named(value = "cadastroProdutoBean")
@ViewScoped
public class CadastroProdutoBean implements Serializable {

    private static final long serialVersionUID = 1L;

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
