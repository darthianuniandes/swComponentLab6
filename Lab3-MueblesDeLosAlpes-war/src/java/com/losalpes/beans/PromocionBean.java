/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.beans;

import com.losalpes.entities.Mueble;
import com.losalpes.entities.Promocion;
import com.losalpes.servicios.IServicioCatalogoMockLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author darthian
 */
@SessionScoped
@ManagedBean(name="promocionBean")
public class PromocionBean implements Serializable {
    
    /**
     * 
     */
    private Promocion promocion;
    /**
     * Representa el mueble seleccionado para una promocion
     */
    private Mueble muebleSelec = new Mueble();
    
    @EJB
    private IServicioCatalogoMockLocal catalogo;

    public PromocionBean() {
        muebleSelec = new Mueble();
    }   
    
    @PostConstruct
    public void init() {
        muebleSelec = new Mueble();
    }
    
    public String guardarPromocion() {
        System.out.println("ISM al intentar guardar la promocion" + muebleSelec);        
        catalogo.agregarPromocionMueble(promocion, Long.parseLong((String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("muebleId")));
        return "catalogo";
    }

    public Promocion getPromocion() {
        return promocion;
    }

    public void setPromocion(Promocion promocion) {
        this.promocion = promocion;
    }

    public Mueble getMuebleSelec() {
        return muebleSelec;
    }

    public void setMuebleSelec(Mueble muebleSelec) {
        this.muebleSelec = muebleSelec;
    }

    public IServicioCatalogoMockLocal getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(IServicioCatalogoMockLocal catalogo) {
        this.catalogo = catalogo;
    }
    
    
}
