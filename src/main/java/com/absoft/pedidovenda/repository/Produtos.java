package com.absoft.pedidovenda.repository;

import com.absoft.pedidovenda.model.Produto;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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
        EntityTransaction trx = entityManager.getTransaction();
        trx.begin();

        produto = entityManager.merge(produto);

        trx.commit();
        return produto;
    }

}
