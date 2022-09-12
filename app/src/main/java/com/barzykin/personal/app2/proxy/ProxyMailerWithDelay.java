package com.barzykin.personal.app2.proxy;

public class ProxyMailerWithDelay implements IMailer {
    private volatile static ProxyMailerWithDelay instance;

    private ProxyMailerWithDelay() {

    }
    public static ProxyMailerWithDelay getInstance() {
        if (instance == null) {
            synchronized (ProxyMailerWithDelay.class) {
                if (instance == null) {
                    instance = new ProxyMailerWithDelay();
                }
            }

        }
        return instance;
    }


    private Mailer mailer = new Mailer();

    @Override
    public boolean sendMail(String pack) {
        System.out.println("Delay 5 seconds");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return mailer.sendMail(pack);
    }
}
