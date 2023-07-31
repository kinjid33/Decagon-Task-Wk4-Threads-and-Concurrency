package services;

import entity.Cashier;
import entity.Customer;
import entity.Product;
import entity.Store;
import interfaces.CashierInterface;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class CashierServiceImpl implements CashierInterface {
    Store store;
//    cashier to make use of implementation
    private Cashier cashier;
//    Customers original balance (initialised to 20,000 in customer class)
    private int customerOriginalBalance = 0;

    public PriorityQueue<Customer> getCustomerPQueue() {
        return customerPQueue;
    }

    public Queue<Customer> getCustomerNQueue() {
        return customerNQueue;
    }

    //    Priority queue
    private PriorityQueue<Customer> customerPQueue = new PriorityQueue<>();
//    FIFO queue
    private Queue<Customer> customerNQueue = new LinkedList<>();
//    constructor
    public CashierServiceImpl(Cashier cashier) {
        this.cashier = cashier;
    }

//    method to make sales
@Override
public void makeSale(Customer customer){
    for(Product productInStore : store.getProductList()){
        for(Product productInCustomer : customer.getProductListInCustomer()) {
            if(productInStore.getName().equals(productInCustomer.getName())){
                customerOriginalBalance = customer.getCashInHand();
                if (customer.getCashInHand() > (productInCustomer.getUnitPrice() * customer.getQuantityToBuy())) {
                    customer.setCashInHand(productInStore.getUnitPrice() * productInCustomer.getQtyInStock());
                    productInStore.setQtyInStock(productInCustomer.getQtyInStock());
                    if(productInStore.getQtyInStock() < 0){
                        System.out.println(productInCustomer.getName().toUpperCase() + " IS OUT OF STOCK");
                    }
                    else {
                        System.out.println(customer.getUserName() + " bought: " + productInCustomer.getName()
                                + "\nQuantity: " + productInCustomer.getQtyInStock()
                                + "\n@ $" + productInStore.getUnitPrice() + " per unit"
                                + "\n" + customer.getUserName() + " paid: $" + customerOriginalBalance
                                + "\nChange: $" + customer.getCashInHand());
                        System.out.println("=============================================================");
                        cashier.setCheckOut(true);
                    }
                }
            }
        }
    }
}

    public void setStore(Store store) {
        this.store = store;
    }

    //    method to add to priority queue
    @Override
    public void addToPriorityQ (Customer customer){
        customerPQueue.add(customer);
    }
//    method to add to FIFO queue
    @Override
    public void addToNQ (Customer customer){
        customerNQueue.add(customer);
    }
    @Override
    public void makeQSales(){
//        Logic to make sale based on priority
        while(customerPQueue.peek() != null){
            makeSale(customerPQueue.poll());
        }
//        Logic to make sale based on FIFO
        while(customerNQueue.peek() != null){
            makeSale(customerNQueue.poll());
        }
    }
}