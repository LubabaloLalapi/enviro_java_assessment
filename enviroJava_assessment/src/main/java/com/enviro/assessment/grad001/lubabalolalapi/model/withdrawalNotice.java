package com.enviro.assessment.grad001.lubabalolalapi.model;

import com.enviro.assessment.grad001.lubabalolalapi.repository.investorRepository;
import com.enviro.assessment.grad001.lubabalolalapi.service.withdrawalNoticeService;
import jakarta.persistence.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Entity
public class withdrawalNotice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal withdrawalAmount;
    private LocalDate noticeDate;

    @ManyToOne
    private product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "investor_id")
    private Investor investor;  // Many-to-one relationship with Investor

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }

    //getInvestor() method to retrieve the associated investor
    public Investor getInvestor() {
        return investor;
    }

    public BigDecimal getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(BigDecimal withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    public LocalDate getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(LocalDate noticeDate) {
        this.noticeDate = noticeDate;
    }

    public product getProduct() {
        return product;
    }

    public void setProduct(product product) {
        this.product = product;
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadStatement(
            @RequestParam Long productId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        try {
            byte[] csvData = withdrawalNoticeService.generateCSVStatement(productId, startDate, endDate);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "statement.csv");
            return ResponseEntity.ok().headers(headers).body(csvData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
