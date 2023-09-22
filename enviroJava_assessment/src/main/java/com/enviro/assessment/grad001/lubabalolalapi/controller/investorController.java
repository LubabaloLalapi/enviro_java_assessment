package com.enviro.assessment.grad001.lubabalolalapi.controller;

import com.enviro.assessment.grad001.lubabalolalapi.model.Investor;
import com.enviro.assessment.grad001.lubabalolalapi.repository.investorRepository;
import com.enviro.assessment.grad001.lubabalolalapi.service.investorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/investors")
public class investorController {
    @Autowired
    private com.enviro.assessment.grad001.lubabalolalapi.service.investorService investorService;

    @GetMapping("/{investorId}")
    public ResponseEntity<Investor> getInvestor(@PathVariable Long investorId) {
        Investor investor = investorService.getInvestor(investorId);
        if (investor != null) {
            return ResponseEntity.ok(investor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
