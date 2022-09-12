package com.barzykin.personal.app2.proxy;

public class Mailer implements IMailer {
    public boolean sendMail(String pack) {
        System.out.println("Packet '" + pack + "' has been sent.");
        return true;
    }
}
