package com.justifai.coreservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.justifai.coreservice.domain.LoanApplication;

public interface LoanApplicationRepository extends CrudRepository<LoanApplication, Long> {
}
