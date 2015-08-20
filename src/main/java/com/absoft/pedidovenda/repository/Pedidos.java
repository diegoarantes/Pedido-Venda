package com.absoft.pedidovenda.repository;

import com.absoft.pedidovenda.model.Pedido;
import com.absoft.pedidovenda.repository.filter.PedidoFilter;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.Dependent;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

@Dependent
public class Pedidos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    @SuppressWarnings("unchecked")
    public List<Pedido> filtrados(PedidoFilter filtro) {
        Session session = this.manager.unwrap(Session.class);

        Criteria criteria = session.createCriteria(Pedido.class)
                // fazemos uma associação (join) com cliente e nomeamos como "c"
                .createAlias("cliente", "c")
                // fazemos uma associação (join) com vendedor e nomeamos como "v"
                .createAlias("vendedor", "v");

        if (filtro.getNumeroDe() != null) {
            // id deve ser maior ou igual (ge = greater or equals) a filtro.numeroDe
            criteria.add(Restrictions.ge("id", filtro.getNumeroDe()));
        }

        if (filtro.getNumeroAte() != null) {
            // id deve ser menor ou igual (le = lower or equal) a filtro.numeroDe
            criteria.add(Restrictions.le("id", filtro.getNumeroAte()));
        }

        if (filtro.getDataCriacaoDe() != null) {
            criteria.add(Restrictions.ge("dataCriacao", filtro.getDataCriacaoDe()));
        }

        if (filtro.getDataCriacaoAte() != null) {
            criteria.add(Restrictions.le("dataCriacao", filtro.getDataCriacaoAte()));
        }

        if (!filtro.getNomeCliente().equals("")) {
            // acessamos o nome do cliente associado ao pedido pelo alias "c", criado anteriormente
            criteria.add(Restrictions.ilike("c.nome", filtro.getNomeCliente(), MatchMode.ANYWHERE));
        }

        if (!filtro.getNomeVendedor().equals("")) {
            // acessamos o nome do vendedor associado ao pedido pelo alias "v", criado anteriormente
            criteria.add(Restrictions.ilike("v.nome", filtro.getNomeVendedor(), MatchMode.ANYWHERE));
        }

        if (filtro.getStatuses() != null && filtro.getStatuses().length > 0) {
            // adicionamos uma restrição "in", passando um array de constantes da enum StatusPedido
            criteria.add(Restrictions.in("status", filtro.getStatuses()));
        }

        return criteria.addOrder(Order.asc("id")).list();
    }

    public Pedido guardar(Pedido pedido) {
        return manager.merge(pedido);
    }

}
