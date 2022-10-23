package com.example.vendingmachine.controller;

import com.example.vendingmachine.domain.ResponseModel;
import com.example.vendingmachine.service.VendingMachineService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class VendingMachineController {

    private final VendingMachineService service;

    @GetMapping("/insert/nickel")
    public ResponseModel insertNickel() {
        return service.insertNickel();
    }

    @GetMapping("/insert/dime")
    public ResponseModel insertDime() {
        return service.insertDime();
    }

    @GetMapping("/insert/quarter")
    public ResponseModel insertQuarter() {
        return service.insertQuarter();
    }

    @GetMapping("/push/orange")
    public ResponseModel pushOrangeJuice() {
        return service.pushOrangeJuice();
    }

    @GetMapping("/push/apple")
    public ResponseModel pushAppleJuice() {
        return service.pushAppleJuice();
    }
}
