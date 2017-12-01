/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.mdbs;

import com.losalpes.entities.Promocion;
import com.losalpes.servicios.IMercadeoBeanLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author darthian
 */
@JMSDestinationDefinition(name = "java:app/jms/javaee7/Topic", interfaceName = "javax.jms.Topic", resourceAdapter = "jmsra", destinationName = "Topic")
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "java:app/jms/javaee7/Topic")
    ,
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:app/jms/javaee7/Topic")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "java:app/jms/javaee7/Topic")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class MercadeoMDB implements MessageListener {
    
    @EJB
    private IMercadeoBeanLocal mercadeoService;
    
    public MercadeoMDB() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            Promocion promo = message.getBody(Promocion.class);
            mercadeoService.agregarPromocionMueble(promo, message.getJMSDeliveryTime());
        } catch (JMSException ex) {
            Logger.getLogger(VentasMDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
