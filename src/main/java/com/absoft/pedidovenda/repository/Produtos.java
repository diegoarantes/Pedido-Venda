package com.absoft.pedidovenda.repository;

import com.absoft.pedidovenda.model.Produto;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author Diego Arantes
 */
@Named
@RequestScoped
public class Produtos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager entityManager;

    public Produto guardar(Produto produto) {
        return entityManager.merge(produto);
    }

    public Produto porSku(String sku) {
        try {
            return entityManager.createQuery("from Produto where upper(sku) = :sku", Produto.class)
                    .setParameter("sku", sku.toUpperCase())
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
