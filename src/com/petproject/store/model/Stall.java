package com.petproject.store.model;

import com.petproject.store.services.StorePerformanceService;

import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class Stall {

    Logger log;
    List<Seller> sellers;
    AtomicInteger servedBuyers = new AtomicInteger(0);
    StorePerformanceService performanceService = new StorePerformanceService();

    public Stall(Logger log, List<Seller> sellers) {
        this.log = log;
        this.sellers = sellers;
    }

    public void trade(Queue<Buyer> buyers) {

        servedBuyers.set(0);
        performanceService.startServeBuyers();

        ExecutorService executorService = Executors.newFixedThreadPool(sellers.size());

        sellers.stream().forEach(seller -> {
            seller.setBuyer(buyers.poll());
            executorService.submit(seller);
            servedBuyers.incrementAndGet();
        });
        executorService.shutdown();
        try {
            executorService.awaitTermination(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                System.out.println("got interrupted!");
//            }
            log.info(performanceService.checkPerformance(servedBuyers.get()));
        }

}
