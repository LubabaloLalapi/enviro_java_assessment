package com.enviro.assessment.grad001.lubabalolalapi.service;

import com.enviro.assessment.grad001.lubabalolalapi.model.Investor;
import com.enviro.assessment.grad001.lubabalolalapi.model.product;
import com.enviro.assessment.grad001.lubabalolalapi.model.withdrawalNotice;
import com.enviro.assessment.grad001.lubabalolalapi.repository.productRepository;
import com.enviro.assessment.grad001.lubabalolalapi.repository.withdrawalNoticeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.opencsv.CSVWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class withdrawalNoticeService {
    @Autowired
    private withdrawalNoticeRepository withdrawalNoticeRepository;

    @Autowired
    private productRepository productRepository;

    public byte[] generateCSVStatement(Long productId, LocalDate startDate, LocalDate endDate) {
        try (Writer writer = new StringWriter();
             CSVWriter csvWriter = new CSVWriter(writer)) {

            // Retrieve withdrawal notices for the selected product and date range
            List<withdrawalNotice> withdrawalNotices = withdrawalNoticeRepository
                    .findByProductIdAndNoticeDateBetween(productId, startDate, endDate);

            // Define the CSV header
            String[] header = {"Notice ID", "Withdrawal Amount", "Notice Date"};

            // Write the header to the CSV
            csvWriter.writeNext(header);

            // Write withdrawal notices to the CSV
            for (withdrawalNotice notice : withdrawalNotices) {
                String[] row = {
                        String.valueOf(notice.getId()),
                        notice.getWithdrawalAmount().toString(),
                        notice.getNoticeDate().toString()
                };
                csvWriter.writeNext(row);
            }

            // Flush the writer and convert CSV data to bytes
            writer.flush();
            byte[] csvBytes = writer.toString().getBytes();

            return csvBytes;
        } catch (Exception e) {
            // Handle exceptions here
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public void createWithdrawalNotice(withdrawalNotice withdrawalNotice) throws ValidationException {
        // Retrieve the associated product
        product product = productRepository.findById(withdrawalNotice.getProduct().getId())
                .orElseThrow(() -> new ValidationException("Product not found"));

        // Validate withdrawal amount
        BigDecimal withdrawalAmount = withdrawalNotice.getWithdrawalAmount();
        if (withdrawalAmount.compareTo(product.getCurrentBalance()) > 0) {
            throw new ValidationException("Withdrawal amount exceeds the current balance");
        }

        // Validate retirement product
        if ("RETIREMENT".equals(product.getType())) {
            Investor investor = withdrawalNotice.getInvestor();
            if (investor.getAge() <= 65) {
                throw new ValidationException("Investor's age must be greater than 65 for RETIREMENT withdrawal");
            }
        }

        // Save the withdrawal notice if validation passes
        withdrawalNoticeRepository.save(withdrawalNotice);
    }



    }




