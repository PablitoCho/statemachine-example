package com.example.vendingmachine.config.action;

import com.example.vendingmachine.enums.Events;
import com.example.vendingmachine.enums.States;
import com.example.vendingmachine.service.VendingMachineService;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

public class InsertAction implements Action<States, Events> {
    @Override
    public void execute(StateContext<States, Events> stateContext) {
        int currentCents = VendingMachineService.getInsertedCents();
        switch(stateContext.getEvent()) {
            case InsertNickel:
                VendingMachineService.setInsertedCents(currentCents + 5);
                break;
            case InsertDime:
                VendingMachineService.setInsertedCents(currentCents + 10);
                break;
            case InsertQuarter:
                VendingMachineService.setInsertedCents(currentCents + 25);
                break;
        }
    }
}