package main.entities;

import main.state.VendingMachineStateContext;
import main.state.impl.AcceptMoneyState;
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
        if(vendingMachineStateContext.getCurrentState() instanceof IdleState ||
                vendingMachineStateContext.getCurrentState() instanceof AcceptMoneyState) {
            this.money+=money;
            vendingMachineStateContext.setNextState();
        }
        else {
            throw new Exception("Cannot insert money when machine is not in Idle state or Accepting Money State");
        }
    }

    public void selectProduct(int productId) throws Exception{
        if(vendingMachineStateContext.getCurrentState() instanceof AcceptMoneyState) {
            if(inventory.getProductPrice(productId) < this.money) {
                throw new Exception("Not enough money inserted to get the product...insert more money");
            }
            vendingMachineStateContext.setNextState();
            inventory.selectProduct(productId);
        }
        else {
            throw new Exception("Product is not available or cannot select product when machine is not in select " +
                    "product state");
        }
    }

    public Product dispenseProduct() throws Exception {
        if(vendingMachineStateContext.getCurrentState() instanceof ProductSelectState) {
            return inventory.dispenseProduct();
        }
        else {
            throw new Exception("Cannot dispense product when machine is not in dispense product state");
        }
    }

}
