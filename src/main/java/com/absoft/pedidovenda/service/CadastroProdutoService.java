package com.absoft.pedidovenda.service;

import com.absoft.pedidovenda.model.Produto;
import com.absoft.pedidovenda.repository.Produtos;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Regras de Negócio Cadastro de Produtos
 *
 * @author Diego Arantes
 */
@Named
@RequestScoped
public class CadastroProdutoService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Produtos produtos;

    public Produto salvar(Produto produto) {
        // TODO implementar regra de negocio
        return produtos.guardar(produto);
    }

}
