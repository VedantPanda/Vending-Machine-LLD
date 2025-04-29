package main.state.impl;

import main.consts.VendingMachineStateEnum;
import main.entities.VendingMachine;
import main.factory.VendingMachineStateFactory;
import main.state.VendingMachineState;

public class IdleState extends VendingMachineState {

    public IdleState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    public IdleState() {
    }

    @Override
    public void insertMoney(int money) throws Exception {
        vendingMachine.insertMoney(money);
        vendingMachine.setCurrentState(VendingMachineStateFactory.getVendingMachineState(
                VendingMachineStateEnum.ACCEPT_MONEY));
    }

}
