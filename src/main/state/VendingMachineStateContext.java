package main.state;

import main.consts.VendingMachineStateEnum;
import main.factory.VendingMachineStateFactory;

public class VendingMachineStateContext {

    private VendingMachineState currentState;

    public VendingMachineStateContext() {
        currentState = VendingMachineStateFactory.getVendingMachineState(VendingMachineStateEnum.IDLE);
    }

    public void setNextState() {
        currentState.setNextState(this);
    }

    public VendingMachineState getCurrentState() {
        return currentState;
    }
}
