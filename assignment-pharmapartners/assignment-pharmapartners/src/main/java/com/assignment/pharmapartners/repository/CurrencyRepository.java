package com.assignment.pharmapartners.repository;


import com.assignment.pharmapartners.models.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


/**
 * It contains the full API of CrudRepository and PagingAndSortingRepository.
 * So it contains API for basic CRUD operations and also API for pagination and sorting of the Entity Currency
 */
@Repository
@EnableJpaRepositories
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    Currency findCurrencyById(Long Id);

}
