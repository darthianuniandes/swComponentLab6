/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.Promocion;
import javax.ejb.Local;
import javax.ejb.Remote;

/**
 *
 * @author darthian
 */
@Remote
public interface IMercadeoBeanRemote {
    
    public void agregarPromocionMueble(Promocion promocion, long idMueble);
    
}
