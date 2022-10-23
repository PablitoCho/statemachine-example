package com.example.vendingmachine.service;

import com.example.vendingmachine.domain.ResponseModel;
import com.example.vendingmachine.enums.Events;
import com.example.vendingmachine.enums.States;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.statemachine.StateMachine;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@AllArgsConstructor
public class VendingMachineService {

    private final StateMachine<States, Events> stateMachine;

    @Getter @Setter
    public static int insertedCents = 0;

    @Getter @Setter
    public static String beverage;

    @PostConstruct
    private void init() {
        stateMachine.start();
        log.info("vending machine created");
    }

    public ResponseModel insertNickel() {
        stateMachine.sendEvent(Events.InsertNickel);
        return new ResponseModel(insertedCents, null, stateMachine.getState().getId().toString());
    }

    public ResponseModel insertDime() {
        stateMachine.sendEvent(Events.InsertDime);
        return new ResponseModel(insertedCents, null, stateMachine.getState().getId().toString());
    }

    public ResponseModel insertQuarter() {
        stateMachine.sendEvent(Events.InsertQuarter);
        return new ResponseModel(insertedCents, null, stateMachine.getState().getId().toString());
    }

    public ResponseModel pushOrangeJuice() {
        stateMachine.sendEvent(Events.PushOrangeJuice);
        return new ResponseModel(insertedCents, beverage, stateMachine.getState().getId().toString());
    }

    public ResponseModel pushAppleJuice() {
        stateMachine.sendEvent(Events.PushAppleJuice);
        return new ResponseModel(insertedCents, beverage, stateMachine.getState().getId().toString());
    }
}
