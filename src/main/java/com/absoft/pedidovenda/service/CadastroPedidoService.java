package com.absoft.pedidovenda.service;

import com.absoft.pedidovenda.model.Pedido;
import com.absoft.pedidovenda.model.StatusPedido;
import com.absoft.pedidovenda.repository.Pedidos;
import com.absoft.pedidovenda.util.jpa.Transactional;
import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Regras de Negócio Cadastro de Pedidos
 *
 * @author Diego Arantes
 */
@Named
@RequestScoped
public class CadastroPedidoService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Pedidos pedidos;

    @Transactional
    public Pedido salvar(Pedido pedido) {
        if (pedido.isNovo()) {
            pedido.setDataCriacao(new Date());
            pedido.setStatus(StatusPedido.ORCAMENTO);
        }

        pedido.recalcularValorTotal(); //Certifica que realmente vai o valor recalculado

        if (pedido.getItens().isEmpty()) { //Verifica se há itens
            throw new NegocioException("O pedido deve possuir pelomenos um item.");
        }

        if (pedido.isValorTotalNegativo()) {
            throw new NegocioException("Valor total do pedido não pode ser negativo.");
        }

        pedido = this.pedidos.guardar(pedido);
        return pedido;
    }

}
