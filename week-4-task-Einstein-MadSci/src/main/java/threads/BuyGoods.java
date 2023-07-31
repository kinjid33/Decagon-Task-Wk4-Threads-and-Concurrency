package threads;

import entity.Customer;
import exceptions.ProductInStockIsLessThanOrderException;
import exceptions.ProductOutOfStockException;
import services.CustomerServiceImpl;

public class BuyGoods implements Runnable {
    CustomerServiceImpl customerService;
    String productToBuy;
    int qtyToBuy;
    int threadNo;

    public BuyGoods(CustomerServiceImpl customerService, String productToBuy, int qtyToBuy, int threadNo) {
        this.customerService = customerService;
        this.productToBuy = productToBuy;
        this.qtyToBuy = qtyToBuy;
        this.threadNo = threadNo;
    }

    @Override
    public void run() {
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        System.out.println("Buy thread" + threadNo + " started");
        try {
            customerService.buyProducts(productToBuy, qtyToBuy);
        } catch (ProductOutOfStockException e) {
            throw new RuntimeException(e);
        } catch (ProductInStockIsLessThanOrderException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Buy thread" + threadNo + " finished");
    }
}
