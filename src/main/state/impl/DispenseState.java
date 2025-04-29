package main.state.impl;

import main.consts.VendingMachineStateEnum;
import main.entities.Product;
import main.entities.VendingMachine;
import main.factory.VendingMachineStateFactory;
import main.state.VendingMachineState;

public class DispenseState extends VendingMachineState {

    public DispenseState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    public DispenseState() {
    }

    public Product dispenseProduct() throws Exception {
        vendingMachine.setCurrentState(VendingMachineStateFactory.getVendingMachineState(VendingMachineStateEnum.IDLE));
        return vendingMachine.dispenseProduct();
    }

}
