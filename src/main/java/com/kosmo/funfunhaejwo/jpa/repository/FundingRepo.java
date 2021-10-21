package com.kosmo.funfunhaejwo.jpa.repository;

import com.kosmo.funfunhaejwo.jpa.domain.Funding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FundingRepo extends JpaRepository<Funding, Long> {

    Optional<Funding> findFundingById(Long funding_id);

}
