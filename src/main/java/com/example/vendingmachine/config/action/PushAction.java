package com.example.vendingmachine.config.action;

import com.example.vendingmachine.enums.Events;
import com.example.vendingmachine.enums.States;
import com.example.vendingmachine.service.VendingMachineService;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

public class PushAction implements Action<States, Events> {
    @Override
    public void execute(StateContext<States, Events> stateContext) {
        int currentCents = VendingMachineService.getInsertedCents();
        VendingMachineService.setInsertedCents(currentCents - 30);
        switch (stateContext.getEvent()) {
            case PushAppleJuice:
                VendingMachineService.setBeverage("Apple Juice");
                break;
            case PushOrangeJuice:
                VendingMachineService.setBeverage("Orange Juice");
                break;
        }
    }
}
