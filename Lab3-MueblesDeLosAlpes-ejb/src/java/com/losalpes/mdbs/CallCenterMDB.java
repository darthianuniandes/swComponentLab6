/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.mdbs;

import com.losalpes.entities.Promocion;
import com.losalpes.servicios.ICallCenterLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author darthian
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:app/jms/javaee7/Topic")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class CallCenterMDB implements MessageListener {
    
    @EJB
    private ICallCenterLocal callCenterService;
    
    public CallCenterMDB() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            Promocion promo = message.getBody(Promocion.class);
            callCenterService.agregarPromocionMueble(promo, message.getJMSDeliveryTime());
        } catch (JMSException ex) {
            Logger.getLogger(VentasMDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
