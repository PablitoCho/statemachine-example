package com.example.vendingmachine.service;

import com.example.vendingmachine.domain.ResponseModel;
import com.example.vendingmachine.enums.Events;
import com.example.vendingmachine.enums.States;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.statemachine.StateMachine;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@AllArgsConstructor
public class VendingMachineService {


    private final StateMachine<States, Events> stateMachine;

    public static int insertedCents = 0;

    @PostConstruct
    private void init() {
        stateMachine.start();
        log.info("vending machine created");
    }

    public ResponseModel insertNickel() {
        insertedCents += 5;
        stateMachine.sendEvent(Events.InsertNickel);
        return new ResponseModel(insertedCents, null, stateMachine.getState().getId().toString());
    }

    public ResponseModel insertDime() {
        insertedCents += 10;
        stateMachine.sendEvent(Events.InsertDime);
        return new ResponseModel(insertedCents, null, stateMachine.getState().getId().toString());
    }

}
