package com.example.vendingmachine.domain;

import com.example.vendingmachine.enums.Events;
import com.example.vendingmachine.enums.States;
import lombok.AllArgsConstructor;
import org.springframework.statemachine.state.State;

@AllArgsConstructor
public class ResponseModel {
    public int insertedCents;
    public String beverage;
    public String currentState;
}
