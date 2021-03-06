/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.utilerias;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author Cortes Lopez Jaime Alejandro
 * @author Godinez Montero Esmeralda
 * @author Fernando Mtz
 */

public class EnviarMail {
    public void enviarMail(String correoDestinatario, String asunto, String textoCorreo) {
        try {
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");

            // Inicializar la sesion
            Session session = Session.getDefaultInstance(props);
            // el mensaje
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDestinatario));
            message.addRecipient(Message.RecipientType.BCC, new InternetAddress(correoDestinatario));
            //CC A quien se le envia una copia 
            //BCC A quien se le envia una copia Oculta
            message.setSubject(asunto);
            message.setText(textoCorreo);
            // envio Mensaje.
            Transport trasporte = session.getTransport("smtp");
            trasporte.connect("escom.fej@gmail.com", "ezzynayijxdmtsdo");
            trasporte.sendMessage(message, message.getAllRecipients());
            // Cierre.
            trasporte.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
