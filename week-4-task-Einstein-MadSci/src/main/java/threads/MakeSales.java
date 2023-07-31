package threads;

import entity.Customer;
import entity.Store;
import services.CashierServiceImpl;

public class MakeSales implements Runnable {
    CashierServiceImpl cashierService;
    Customer customer;
    private int threadNo;
    private Store store;


    public MakeSales(CashierServiceImpl cashierService, Customer customer, int threadNo, Store store) {
        this.cashierService = cashierService;
        this.customer = customer;
        this.threadNo = threadNo;
        this.store = store;
    }

    @Override
    public void run(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Sale thread" + threadNo + " started");
        cashierService.makeSale(customer);
        System.out.println("Sale thread" + threadNo + " finished");
    }
}
