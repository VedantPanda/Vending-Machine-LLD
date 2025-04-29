package main.entities;

import main.state.VendingMachineState;
import main.state.impl.IdleState;

public class VendingMachine {

    private final Inventory inventory;

    private int money;

    private Product currentProduct;

    private VendingMachineState currentState;

    private static class VendingMachineSingleton {
        private static VendingMachine VENDING_MACHINE_INSTANCE = new VendingMachine();
    }

    public static VendingMachine getVendingMachineInstance() {
        return VendingMachineSingleton.VENDING_MACHINE_INSTANCE;
    }

    public VendingMachineState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(VendingMachineState currentState) {
        this.currentState = currentState;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    private VendingMachine() {
        inventory = new Inventory();
        currentState = new IdleState();
    }

    public void insertMoney(int money) throws Exception {
        this.money += money;
    }

    public void selectProduct(int productId) throws Exception {
        if (inventory.isProductAvailable(productId)) {
            int productPrice = inventory.getProductPrice(productId);
            if (productPrice > this.money) {
                throw new Exception("Price of product is: " + productPrice + ", money inserted is " + this.money);
            } else if (productPrice < this.money) {
                int changePrice = this.money - productPrice;
                System.out.println("Here's the change " + changePrice);
                this.money = productPrice;
            }
            this.currentProduct = inventory.getProduct(productId);
        } else {
            throw new Exception("Product with id: " + productId + " is not available");
        }
    }

    public Product dispenseProduct() throws Exception {
        return inventory.dispenseProduct(this.currentProduct);
    }


    public void cancelProcess() throws Exception {
        System.out.println("Here's your money " + this.money);
        this.money = 0;
    }

}
