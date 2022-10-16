package com.example.vendingmachine.service;

import com.example.vendingmachine.enums.Events;
import com.example.vendingmachine.enums.States;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.StateMachine;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class VendingMachineService {

    @Autowired
    private StateMachineFactory<States, Events> factory;

    private StateMachine<States, Events> stateMachine;

    @PostConstruct
    private void init() {
        stateMachine = factory.getStateMachine();
        stateMachine.start();
        log.info("vending machine created");
    }
}
