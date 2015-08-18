package com.absoft.pedidovenda.controller;

import com.absoft.pedidovenda.model.Categoria;
import com.absoft.pedidovenda.model.Produto;
import com.absoft.pedidovenda.repository.Categorias;
import com.absoft.pedidovenda.service.CadastroProdutoService;
import com.absoft.pedidovenda.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
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

    @Inject
    private CadastroProdutoService cadastroProdutoService;

    private Produto produto;

    @NotNull
    private Categoria categoriaPai;

    private List<Categoria> categoriaRaizes;
    private List<Categoria> subcategorias;

    public CadastroProdutoBean() {
        limpar();
    }

    public void inicializar() {
        if (FacesUtil.isNotPostBack()) { //Se não for post back não faça a consulta
            categoriaRaizes = categorias.raizes();
        }
    }

    public void carregarSubcategorias() {
        subcategorias = categorias.subCategoriasDe(categoriaPai);
    }

    public void salvar() {
        this.produto = cadastroProdutoService.salvar(produto);
        limpar();
        FacesUtil.addInfoMessage("Produto salvo com sucesso!");
    }

    private void limpar() {
        produto = new Produto();
        categoriaPai = null;
        subcategorias = new ArrayList<>();
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

    public List<Categoria> getSubcategorias() {
        return subcategorias;
    }

}
