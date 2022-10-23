package com.example.vendingmachine.config.guard;

import com.example.vendingmachine.enums.Events;
import com.example.vendingmachine.enums.States;
import com.example.vendingmachine.service.VendingMachineService;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;

public class PushGuard implements Guard<States, Events> {
    @Override
    public boolean evaluate(StateContext<States, Events> stateContext) {
        int currentCents = VendingMachineService.getInsertedCents();
        if(currentCents < 30)
            return false;
        return true;
    }
}