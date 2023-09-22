package com.enviro.assessment.grad001.lubabalolalapi.repository;

import com.enviro.assessment.grad001.lubabalolalapi.model.withdrawalNotice;
import org.springframework.data.jpa.repository.JpaRepository;

;import java.time.LocalDate;
import java.util.List;

public interface withdrawalNoticeRepository extends JpaRepository<withdrawalNotice, Long> {
    List<withdrawalNotice> findByProductIdAndNoticeDateBetween(
            Long productId,
            LocalDate startDate,
            LocalDate endDate
    );
}

