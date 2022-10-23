package com.example.vendingmachine.config.guard;

import com.example.vendingmachine.enums.Events;
import com.example.vendingmachine.enums.States;
import com.example.vendingmachine.service.VendingMachineService;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;

public class InsertGuard implements Guard<States, Events> {
    @Override
    public boolean evaluate(StateContext<States, Events> stateContext) {
        int currentCents = VendingMachineService.getInsertedCents();
        switch(stateContext.getEvent()) {
            case InsertNickel:
                return currentCents + 5 <= 30;
            case InsertDime:
                return currentCents + 10 <= 30;
            case InsertQuarter:
                return currentCents + 25 <= 30;
        }
        return false;
    }
}