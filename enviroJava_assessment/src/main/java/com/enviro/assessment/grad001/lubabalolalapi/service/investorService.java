package com.enviro.assessment.grad001.lubabalolalapi.service;

import com.enviro.assessment.grad001.lubabalolalapi.model.Investor;
import com.enviro.assessment.grad001.lubabalolalapi.repository.investorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class investorService {
    @Autowired
    private investorRepository investorRepository;

    public Investor getInvestor(Long investorId) {
        return investorRepository.findById(investorId).orElse(null);
    }
}
