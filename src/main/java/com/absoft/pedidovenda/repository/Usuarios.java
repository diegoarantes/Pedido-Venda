package com.absoft.pedidovenda.repository;

import com.absoft.pedidovenda.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.Dependent;

import javax.inject.Inject;
import javax.persistence.EntityManager;

@Dependent
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    public Usuario porId(Long id) {
        return this.manager.find(Usuario.class, id);
    }

    public List<Usuario> vendedores() {
        // TODO filtrar apenas vendedores (por um grupo específico)
        return this.manager.createQuery("from Usuario", Usuario.class)
                .getResultList();
    }

}
