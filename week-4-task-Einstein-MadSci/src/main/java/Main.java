import entity.*;
import exceptions.ApplicantUnderageException;
import exceptions.ProductInStockIsLessThanOrderException;
import exceptions.ProductOutOfStockException;
import services.CashierServiceImpl;
import services.CustomerServiceImpl;
import services.ManagerServiceImpl;
import services.StoreServiceImpl;
import threads.BuyGoods;
import threads.MakeSales;

import javax.swing.plaf.basic.BasicIconFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        //        create new store object to relate to objects
        Store store = new Store();
        StoreServiceImpl storeServ = new StoreServiceImpl(store);

        //        read products from csv file
        storeServ.readProduct();

        System.out.println(store.getProductList());

        //        create manager object
        Manager manager = new Manager("Ade", store);

        //        pass manager object to manager service implementation object
        ManagerServiceImpl managerServ = new ManagerServiceImpl(manager);

        //        create new cashier object
        Cashier cashier1 = new Cashier("Bola", store);
        Cashier cashier2 = new Cashier("Olu", store);
        Cashier cashier3 = new Cashier("Bolu", store);
        Cashier cashier4 = new Cashier("Bob", store);
        Cashier cashier5 = new Cashier("Maggie", store);
        Cashier cashier6 = new Cashier("Tunji", store);
        Cashier cashier7 = new Cashier("Toyo", store);
        Cashier cashier8 = new Cashier("Tosin", store);
        Cashier cashier9 = new Cashier("Funmi", store);
        Cashier cashier10 = new Cashier("Boye", store);

        //        pass cashier object to cashier service implementation object
        CashierServiceImpl cashierServ1 = new CashierServiceImpl(cashier1);
        CashierServiceImpl cashierServ2 = new CashierServiceImpl(cashier2);
        CashierServiceImpl cashierServ3 = new CashierServiceImpl(cashier3);
        CashierServiceImpl cashierServ4 = new CashierServiceImpl(cashier4);
        CashierServiceImpl cashierServ5 = new CashierServiceImpl(cashier5);
        CashierServiceImpl cashierServ6 = new CashierServiceImpl(cashier6);
        CashierServiceImpl cashierServ7 = new CashierServiceImpl(cashier7);
        CashierServiceImpl cashierServ8 = new CashierServiceImpl(cashier8);
        CashierServiceImpl cashierServ9 = new CashierServiceImpl(cashier9);
        CashierServiceImpl cashierServ10 = new CashierServiceImpl(cashier10);

        //Set store
        cashierServ1.setStore(store);
        cashierServ2.setStore(store);
        cashierServ3.setStore(store);
        cashierServ4.setStore(store);
        cashierServ5.setStore(store);
        cashierServ6.setStore(store);
        cashierServ7.setStore(store);
        cashierServ8.setStore(store);
        cashierServ9.setStore(store);
        cashierServ10.setStore(store);

        //        create three customer objects
        Customer customer1 = new Customer("Chris", store);
        Customer customer2 = new Customer("Fade", store);
        Customer customer3 = new Customer("Felix", store);
        Customer customer4 = new Customer("Daniel", store);
        Customer customer5 = new Customer("Jide", store);
        Customer customer6 = new Customer("Laide", store);
        Customer customer7 = new Customer("Olu", store);
        Customer customer8 = new Customer("Mide", store);
        Customer customer9 = new Customer("Tunde", store);
        Customer customer10 = new Customer("Babzy", store);

        //        pass three customers above to customer service implementation objects
        CustomerServiceImpl customerServ1 = new CustomerServiceImpl(customer1, store);
        CustomerServiceImpl customerServ2 = new CustomerServiceImpl(customer2, store);
        CustomerServiceImpl customerServ3 = new CustomerServiceImpl(customer3, store);
        CustomerServiceImpl customerServ4 = new CustomerServiceImpl(customer4, store);
        CustomerServiceImpl customerServ5 = new CustomerServiceImpl(customer5, store);
        CustomerServiceImpl customerServ6 = new CustomerServiceImpl(customer6, store);
        CustomerServiceImpl customerServ7 = new CustomerServiceImpl(customer7, store);
        CustomerServiceImpl customerServ8 = new CustomerServiceImpl(customer8, store);
        CustomerServiceImpl customerServ9 = new CustomerServiceImpl(customer9, store);
        CustomerServiceImpl customerServ10 = new CustomerServiceImpl(customer10, store);

        //        create applicant (potential cashier) object
        PotentialCashier applicant = new PotentialCashier("Kale", 20, store);
        PotentialCashier applicant2 = new PotentialCashier("Femi", 16, store);

        //        create array list of customers
        List<Customer> customerList = new ArrayList<>();

        //        hire potential cashier
        try {
            managerServ.hireCashier(applicant);
            managerServ.hireCashier(applicant2);
        } catch (ApplicantUnderageException e) {
            System.out.println(e.getMessage());
        }


//        strike and fire cashier (applicant)
        managerServ.strikeCashier(applicant);
        managerServ.strikeCashier(applicant);
        managerServ.strikeCashier(applicant);
        managerServ.fireCashier(applicant);

//                customers buy products
//        try {
//            customerServ.buyProducts("Sprite", 10);
//            customerServ.buyProducts("Fanta", 10);
//            customerServ.buyProducts("Electric Iron", 1);
//            customerServ1.buyProducts("Sprite", 2);
//            customerServ1.buyProducts("7Up", 3);
//            customerServ2.buyProducts("Maltina", 4);
//        } catch (ProductOutOfStockException e) {
//            System.out.println(e.getMessage());
//        } catch (ProductInStockIsLessThanOrderException e) {
//            throw new RuntimeException(e);
//        }

//        cashierServ1.makeSale(customer1);
//        cashierServ1.makeSale(customer2);
//        cashierServ1.makeSale(customer3);


        BuyGoods buyGoods1 = new BuyGoods(customerServ1, "Sprite", 151, 1);
        BuyGoods buyGoods2 = new BuyGoods(customerServ2, "Sprite", 2, 2);
        BuyGoods buyGoods3 = new BuyGoods(customerServ3, "7Up", 3, 3);
        BuyGoods buyGoods4 = new BuyGoods(customerServ4, "Maltina", 4, 4);
        BuyGoods buyGoods5 = new BuyGoods(customerServ5, "Coca Cola", 10, 5);
        BuyGoods buyGoods6 = new BuyGoods(customerServ6, "Electric Kettle", 50, 6);
        BuyGoods buyGoods7 = new BuyGoods(customerServ7, "7Up", 10, 7);
        BuyGoods buyGoods8 = new BuyGoods(customerServ8, "Maltina", 10, 8);
        BuyGoods buyGoods9 = new BuyGoods(customerServ9, "Standing Fan", 10, 9);
        BuyGoods buyGoods10 = new BuyGoods(customerServ10, "Television", 10, 10);

        Thread buyThread1 = new Thread(buyGoods1);
        Thread buyThread2 = new Thread(buyGoods2);
        Thread buyThread3 = new Thread(buyGoods3);
        Thread buyThread4 = new Thread(buyGoods4);
        Thread buyThread5 = new Thread(buyGoods5);
        Thread buyThread6 = new Thread(buyGoods6);
        Thread buyThread7 = new Thread(buyGoods7);
        Thread buyThread8 = new Thread(buyGoods8);
        Thread buyThread9 = new Thread(buyGoods9);
        Thread buyThread10 = new Thread(buyGoods10);

        buyThread1.start();
        buyThread2.start();
        buyThread3.start();
        buyThread4.start();
        buyThread5.start();
        buyThread6.start();
        buyThread7.start();
        buyThread8.start();
        buyThread9.start();
        buyThread10.start();

        try {
            buyThread1.join();
            buyThread2.join();
            buyThread3.join();
            buyThread4.join();
            buyThread5.join();
            buyThread6.join();
            buyThread7.join();
            buyThread8.join();
            buyThread9.join();
            buyThread10.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        MakeSales mkSales1 = new MakeSales(cashierServ1, customer1, 1,store);
        MakeSales mkSales2 = new MakeSales(cashierServ2, customer2, 2,store);
        MakeSales mkSales3 = new MakeSales(cashierServ3, customer3, 3,store);
        MakeSales mkSales4 = new MakeSales(cashierServ4, customer4, 4,store);
        MakeSales mkSales5 = new MakeSales(cashierServ5, customer5, 5,store);
        MakeSales mkSales6 = new MakeSales(cashierServ6, customer6, 6,store);
        MakeSales mkSales7 = new MakeSales(cashierServ7, customer7, 7,store);
        MakeSales mkSales8 = new MakeSales(cashierServ8, customer8, 8,store);
        MakeSales mkSales9 = new MakeSales(cashierServ9, customer9, 9,store);
        MakeSales mkSales10 = new MakeSales(cashierServ10, customer10, 10,store);

        Thread saleThread1 = new Thread(mkSales1);
        Thread saleThread2 = new Thread(mkSales2);
        Thread saleThread3 = new Thread(mkSales3);
        Thread saleThread4 = new Thread(mkSales4);
        Thread saleThread5 = new Thread(mkSales5);
        Thread saleThread6 = new Thread(mkSales6);
        Thread saleThread7 = new Thread(mkSales7);
        Thread saleThread8 = new Thread(mkSales8);
        Thread saleThread9 = new Thread(mkSales9);
        Thread saleThread10 = new Thread(mkSales10);

//        saleThread1.start();
//        saleThread2.start();
//        saleThread3.start();
//        saleThread4.start();
//        saleThread5.start();
//        saleThread6.start();
//        saleThread7.start();
//        saleThread8.start();
//        saleThread9.start();
//        saleThread10.start();
//
//        try {
//            saleThread1.join();
//            saleThread2.join();
//            saleThread3.join();
//            saleThread4.join();
//            saleThread5.join();
//            saleThread6.join();
//            saleThread7.join();
//            saleThread8.join();
//            saleThread9.join();
//            saleThread10.join();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }


//        Create thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(2);

//        execute thread pool
        executorService.execute(saleThread1);
        executorService.execute(saleThread2);
        executorService.execute(saleThread3);
        executorService.execute(saleThread4);
        executorService.execute(saleThread5);
        executorService.execute(saleThread6);
        executorService.execute(saleThread7);
        executorService.execute(saleThread8);
        executorService.execute(saleThread9);
        executorService.execute(saleThread10);

//        shutdown thread pool
        executorService.shutdown();

        System.out.println(store.getProductList());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Main thread finishes");


//        cashierServ.makeSale(customer);

        //        add customers to priority queue
//        cashierServ.addToPriorityQ(customer);
//        cashierServ.addToPriorityQ(customer1);
//        cashierServ.addToPriorityQ(customer2);
//        System.out.println("Customer priority queue: " + cashierServ.getCustomerPQueue().size());

        //        add customers to FIFO queue
//        cashierServ.addToNQ(customer2);
//        cashierServ.addToNQ(customer1);
//        cashierServ.addToNQ(customer);

        //        make sales based on Priority (first set of transactions) and FIFO (second set of transaction)
//        cashierServ.makeQSales();
    }
}
