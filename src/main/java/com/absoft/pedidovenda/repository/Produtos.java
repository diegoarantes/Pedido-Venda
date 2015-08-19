package com.absoft.pedidovenda.repository;

import com.absoft.pedidovenda.model.Produto;
import com.absoft.pedidovenda.repository.filter.ProdutoFilter;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

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

    public List<Produto> filtrados(ProdutoFilter filtro) {
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Produto.class); //Criar um criterio

        if (!filtro.getSku().equals("")) {
            criteria.add(Restrictions.eq("sku", filtro.getSku()));
        }

        if (!filtro.getNome().equals("")) {
            criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE)); //ANYWHERE = %joao%
        }

        criteria.addOrder(Order.asc("nome")); //Ordenar pelo 'nome' de forma Ascendente

        return criteria.list();
    }

    public Produto porId(Long id) {
        return entityManager.find(Produto.class, id);
    }

}
