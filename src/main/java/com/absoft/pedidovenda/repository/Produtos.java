package com.absoft.pedidovenda.repository;

import com.absoft.pedidovenda.model.Produto;
import com.absoft.pedidovenda.repository.filter.ProdutoFilter;
import com.absoft.pedidovenda.service.NegocioException;
import com.absoft.pedidovenda.util.jpa.Transactional;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Diego Arantes
 */
@Dependent
public class Produtos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager entityManager;

    public Produto guardar(Produto produto) {
        return entityManager.merge(produto);
    }

    @Transactional
    public void remover(Produto produto) {
        try {
            produto = porId(produto.getId()); //Busca produto
            entityManager.remove(produto);
            entityManager.flush(); //Faz todas as transações
        } catch (PersistenceException ex) {
            throw new NegocioException("Produto não pode ser excluído.");
        }

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
