package com.barzykin.personal.app2.proxy;

public class Application {
    public static void main(String[] args) {
        IMailer mailer = ProxyMailerWithDelay.getInstance();
        boolean helloResult = mailer.sendMail("Hello");
        if (helloResult) {
            System.out.println("Successful sent");
        }
    }
}
