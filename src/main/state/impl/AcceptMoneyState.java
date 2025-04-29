package main.state.impl;

import main.consts.VendingMachineStateEnum;
import main.entities.VendingMachine;
import main.factory.VendingMachineStateFactory;
import main.state.VendingMachineState;

public class AcceptMoneyState extends VendingMachineState {

    public AcceptMoneyState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    public AcceptMoneyState() {
    }

    @Override
    public void insertMoney(int money) throws Exception {
        vendingMachine.insertMoney(money);
        vendingMachine.setCurrentState(VendingMachineStateFactory.getVendingMachineState(
                VendingMachineStateEnum.ACCEPT_MONEY));
    }

    @Override
    public void cancelProcess() throws Exception {
        vendingMachine.cancelProcess();
        vendingMachine.setCurrentState(VendingMachineStateFactory.getVendingMachineState(
                VendingMachineStateEnum.IDLE));
    }

}
