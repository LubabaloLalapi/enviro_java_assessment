package com.enviro.assessment.grad001.lubabalolalapi.controller;

import com.enviro.assessment.grad001.lubabalolalapi.model.product;
import com.enviro.assessment.grad001.lubabalolalapi.model.withdrawalNotice;
import com.enviro.assessment.grad001.lubabalolalapi.repository.withdrawalNoticeRepository;
import com.enviro.assessment.grad001.lubabalolalapi.service.ValidationException;
import com.enviro.assessment.grad001.lubabalolalapi.service.withdrawalNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/withdrawals")
public class withdrawalNoticeController {
    @Autowired
    private withdrawalNoticeService withdrawalNoticeService;

    @PostMapping("/create")
    public ResponseEntity<String> createWithdrawalNotice(@RequestBody withdrawalNotice withdrawalNotice) {
        try {
            withdrawalNoticeService.createWithdrawalNotice(withdrawalNotice);
            return ResponseEntity.ok("Withdrawal notice created successfully.");
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadStatement(
            @RequestParam Long productId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        // Implement logic to generate and download the CSV statement
        return null;
    }
}
