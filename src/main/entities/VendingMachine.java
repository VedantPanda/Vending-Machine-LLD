package main.entities;

import main.state.VendingMachineStateContext;
import main.state.impl.AcceptMoneyState;
import main.state.impl.DispenseState;
import main.state.impl.IdleState;
import main.state.impl.ProductSelectState;

public class VendingMachine {

    private final Inventory inventory;

    private int money;

    private Product currentProduct;

    private final VendingMachineStateContext vendingMachineStateContext;

    private static class VendingMachineSingleton {
        private static VendingMachine VENDING_MACHINE_INSTANCE = new VendingMachine();
    }

    public static VendingMachine getVendingMachineInstance() {
        return VendingMachineSingleton.VENDING_MACHINE_INSTANCE;
    }

    private VendingMachine() {
        inventory = new Inventory();
        vendingMachineStateContext = new VendingMachineStateContext();
    }

    public void insertMoney(int money) throws Exception {
        if(vendingMachineStateContext.getCurrentState() instanceof IdleState) {
            vendingMachineStateContext.setNextState();
            this.money+=money;
        }
        else if(vendingMachineStateContext.getCurrentState() instanceof AcceptMoneyState) {
            this.money+=money;
        }
        else{
            throw new Exception("Cannot insert money when machine is not in idle state or accepting money state");
        }
    }

    public void selectProduct(int productId) throws Exception{
        if(vendingMachineStateContext.getCurrentState() instanceof AcceptMoneyState) {
            if(inventory.isProductAvailable(productId)) {
                int productPrice = inventory.getProductPrice(productId);
                if(productPrice > this.money) {
                    throw new Exception("Price of product is: "+productPrice+", money inserted is "+this.money);
                }
                else if(productPrice < this.money) {
                    int changePrice = this.money - productPrice;
                    System.out.println("Here's the change "+changePrice);
                    this.money = productPrice;
                }
                this.currentProduct = inventory.getProduct(productId);
                vendingMachineStateContext.setNextState();
            }
            else {
                throw new Exception("Product with id: "+productId+" is not available");
            }
        }
        else {
            throw new Exception("Product can only be selected when money is inserted");
        }
    }

    public Product dispenseProduct() throws Exception {
        if(vendingMachineStateContext.getCurrentState() instanceof ProductSelectState) {
            vendingMachineStateContext.setNextState();
            Product dispensedProduct = inventory.dispenseProduct(this.currentProduct);
            vendingMachineStateContext.setNextState();
            return dispensedProduct;
        }
        else {
            throw new Exception("Cannot dispense product when machine is not in dispense product state");
        }
    }


    public void cancelProcess() throws Exception {
        if(!(vendingMachineStateContext.getCurrentState() instanceof IdleState) ||
                !(vendingMachineStateContext.getCurrentState() instanceof DispenseState)) {
            System.out.println("Here's your money "+this.money);
            this.money = 0;
        }
        else {
            throw new Exception("Cannot cancel transaction");
        }
    }

}
