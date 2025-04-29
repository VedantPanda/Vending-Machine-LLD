package main.state;

import main.entities.Product;
import main.entities.VendingMachine;

public abstract class VendingMachineState {

    protected VendingMachine vendingMachine;

    public VendingMachineState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    public VendingMachineState() {

    }

    public void insertMoney(int money) throws Exception {
        throw new Exception("Cannot insert money when chosen a product");
    }

    public void selectProduct(int productId) throws Exception {
        throw new Exception("Cannot select product before inserting money or once selected a product already");
    }

    public Product dispenseProduct() throws Exception {
        throw new Exception("Cannot dispense product...please select a product first that is available");
    }

    public void cancelProcess() throws Exception {
        throw new Exception("Cannot cancel transaction when money has not been inserted or product is being dispensed");
    }

}
