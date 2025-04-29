package main.state.impl;

import main.consts.VendingMachineStateEnum;
import main.entities.VendingMachine;
import main.factory.VendingMachineStateFactory;
import main.state.VendingMachineState;

public class ProductSelectState extends VendingMachineState {

    public ProductSelectState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    public ProductSelectState() {
        super();
    }

    @Override
    public void selectProduct(int productId) throws Exception {
        vendingMachine.selectProduct(productId);
        vendingMachine.setCurrentState(VendingMachineStateFactory.getVendingMachineState(
                VendingMachineStateEnum.PRODUCT_SELECT));
    }

    @Override
    public void cancelProcess() throws Exception {
        vendingMachine.cancelProcess();
        vendingMachine.setCurrentState(VendingMachineStateFactory.getVendingMachineState(
                VendingMachineStateEnum.IDLE));
    }

}
