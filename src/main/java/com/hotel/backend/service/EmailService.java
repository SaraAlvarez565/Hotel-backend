package com.hotel.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    //@Autowired
    //private JavaMailSender mailSender;

    public void sendReservationEmail(String to, String product, String start, String end) {

        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo(to);
        msg.setSubject("Reserva confirmada");

        msg.setText(
                "Tu reserva fue confirmada:\n\n" +
                        "Producto: " + product + "\n" +
                        "Desde: " + start + "\n" +
                        "Hasta: " + end
        );

       // mailSender.send(msg);
    }
}