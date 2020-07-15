package com.petproject.store.model;

public class Seller extends Thread {

    public void serveTheBuyer(Buyer buyer) {
        new Thread(() -> {
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                buyer.isServed = true;
            }
        }).start();
    }

}
