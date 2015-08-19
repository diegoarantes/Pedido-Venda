package com.absoft.pedidovenda.service;

import com.absoft.pedidovenda.model.Produto;
import com.absoft.pedidovenda.repository.Produtos;
import com.absoft.pedidovenda.util.jpa.Transactional;
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

    @Transactional
    public Produto salvar(Produto produto) {
        Produto produtoExistente = produtos.porSku(produto.getSku());

        if (produtoExistente != null && !produtoExistente.equals(produto)) { //E se não for o mesmo produto
            throw new NegocioException("Já existe um produto com o SKU informado.");
        }
        return produtos.guardar(produto);
    }

}
