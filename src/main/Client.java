package main;

import main.entities.VendingMachine;
import main.state.VendingMachineState;

public class Client {
    public static void main(String[] args) {
        try{
            VendingMachine vendingMachine = VendingMachine.getVendingMachineInstance();
            VendingMachineState vendingMachineState = vendingMachine.getCurrentState();
            vendingMachineState.insertMoney(3);
            vendingMachineState.selectProduct(1);
            vendingMachineState.dispenseProduct();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
