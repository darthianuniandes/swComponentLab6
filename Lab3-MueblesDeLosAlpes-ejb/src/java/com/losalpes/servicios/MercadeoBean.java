/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.Mueble;
import com.losalpes.entities.Promocion;
import com.losalpes.excepciones.OperacionInvalidaException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author darthian
 */
@Stateless
public class MercadeoBean implements IMercadeoBeanLocal, IMercadeoBeanRemote {
    
    @EJB
    private IServicioPersistenciaMockLocal persistencia;

    @Override
    public void agregarPromocionMueble(Promocion promocion, long idMueble) {
        
        Mueble mueble = (Mueble) persistencia.findById(Mueble.class, idMueble);
        mueble.setPromocion(promocion);
        try {
            persistencia.create(mueble);
        } catch (OperacionInvalidaException ex) {
            Logger.getLogger(MercadeoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
