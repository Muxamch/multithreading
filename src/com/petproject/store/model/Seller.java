package com.petproject.store.model;

public class Seller implements Runnable {

    private Buyer buyer;

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    @Override
    public void run() {
        try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                buyer.isServed = true;
            }
        //        new Thread(() -> {
//            try {
//                Thread.currentThread().sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                buyer.isServed = true;
//            }
//        }).start();
    }
}
