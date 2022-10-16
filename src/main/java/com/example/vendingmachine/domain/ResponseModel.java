package com.example.vendingmachine.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResponseModel {
    public int insertedCents;
    public String beverage;
    public String currentState;
}
