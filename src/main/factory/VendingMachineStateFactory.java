package main.factory;

import main.consts.VendingMachineStateEnum;
import main.state.VendingMachineState;
import main.state.impl.*;

public class VendingMachineStateFactory {

    public static VendingMachineState getVendingMachineState(VendingMachineStateEnum vendingMachineStateEnum) {
        if(VendingMachineStateEnum.ACCEPT_MONEY.equals(vendingMachineStateEnum)) {
            return new AcceptMoneyState();
        }
        else if(VendingMachineStateEnum.CANCEL.equals(vendingMachineStateEnum)) {
            return new CancelState();
        }
        else if(VendingMachineStateEnum.IDLE.equals(vendingMachineStateEnum)) {
            return new IdleState();
        }
        else if(VendingMachineStateEnum.DISPENSE.equals(vendingMachineStateEnum)) {
            return new DispenseState();
        }
        else if(VendingMachineStateEnum.PRODUCT_SELECT.equals(vendingMachineStateEnum)) {
            return new ProductSelectState();
        }
        else if(VendingMachineStateEnum.OUT_OF_STOCK.equals(vendingMachineStateEnum)) {
            return new OutOfStockState();
        }
        else {
            return null;
        }
    }

}
