package main.state;

public interface VendingMachineState {

    VendingMachineState setNextState(VendingMachineStateContext vendingMachineStateContext);

}
