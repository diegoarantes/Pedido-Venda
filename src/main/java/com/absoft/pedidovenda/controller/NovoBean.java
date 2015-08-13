/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.absoft.pedidovenda.controller;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Diego Arantes
 */
@Named
@RequestScoped
public class NovoBean {

    /**
     * Creates a new instance of NovoBean
     */
    public NovoBean() {
    }

    public void testar() {
        throw new RuntimeException("LOUCURAAAAAAAAAAAAAAAAA TOTALLL");
    }
}
