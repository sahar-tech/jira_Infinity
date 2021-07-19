package com.example.paymentTransaction.Controllers;

import com.sendgrid.helpers.mail.Mail;

import java.util.List;

public interface emailService {

    public boolean validEmail(String email);
    public boolean sendEmail(Mail email);
    public Mail genererEmail(String from, String to, String subject, String text, String num_commande, List<String> files, String willaya, String commune, String code_postal, String num_rue, String Téléphone, String mail, String link_GPS, String point_livraison, String Delai, String Frais_transport, String lien_confirm, String lien_cancel, String num_compte, String lien_banque);

}
