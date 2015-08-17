package com.absoft.pedidovenda.controller;

import com.absoft.pedidovenda.model.Categoria;
import com.absoft.pedidovenda.model.Produto;
import com.absoft.pedidovenda.repository.Categorias;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Diego Arantes
 */
@Named(value = "cadastroProdutoBean")
@ViewScoped
public class CadastroProdutoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Categorias categorias;

    private Produto produto;

    @NotNull
    private Categoria categoriaPai;

    private List<Categoria> categoriaRaizes;

    public CadastroProdutoBean() {
        produto = new Produto();
    }

    public void inicializar() {
        System.out.println("Inicializando ....");
        categoriaRaizes = categorias.raizes();

    }

    public void salvar() {
        System.out.println("Categoria pai selecionada: "+categoriaPai.getDescricao());
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

    public Categoria getCategoriaPai() {
        return categoriaPai;
    }

    public void setCategoriaPai(Categoria categoriaPai) {
        this.categoriaPai = categoriaPai;
    }

}
