package com.assignment.pharmapartners.repository;


import com.assignment.pharmapartners.models.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


@Repository
@EnableJpaRepositories
public interface CurrencyRepository extends JpaRepository<Currency,Long> {

    Currency findCurrencyById(Long Id);







}
