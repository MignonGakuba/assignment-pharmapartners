package com.assignment.pharmapartners.service;


import com.assignment.pharmapartners.interfaces.ICurrencyService;
import com.assignment.pharmapartners.models.Currency;
import com.assignment.pharmapartners.repository.CurrencyRepository;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Allows developers to add business functionalities for the @RestController
 */
@Service
public class CurrencyService implements ICurrencyService {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyService.class);

    @Autowired
    private CurrencyRepository repository;

    @Override
    public Currency create(Currency currencyDto) {
        try {
            if (checkIfCurrencyExist(currencyDto))
                throw new IllegalArgumentException("Currency already exists with this id");
            else
                repository.save(currencyDto);
            return currencyDto;
        } catch (HibernateException e) {
            logger.warn("Error while getting the currency" + currencyDto.getId());
            throw e;
        }
    }

    @Override
    public List<Currency> read() {
        List<Currency> currencies = new ArrayList<>();
        try {
            currencies = repository.findAll(Sort.by(Sort.Direction.ASC, "ticker"));
        } catch (Exception exception) {
            logger.warn(exception.getMessage());
        }
        return currencies;
    }

    @Override
    public Currency update(Currency currencyDto) {
        try {
            Currency currencyToUpdate = repository.findCurrencyById(currencyDto.getId());
            currencyToUpdate.setName(currencyDto.getName());
            currencyToUpdate.setTicker(currencyDto.getTicker());
            currencyToUpdate.setNumber_of_coins(currencyDto.getNumber_of_coins());
            currencyToUpdate.setMarket_cap(currencyDto.getMarket_cap());
            repository.save(currencyToUpdate);

        } catch (HibernateException e) {
            logger.warn("Error while updating the currency" + currencyDto.getId());
            throw e;
        }

        return null;
    }


    @Override
    public boolean delete(Currency currencyDto) {
        try {
            repository.delete(currencyDto);
        } catch (HibernateException e) {
            logger.warn("Error while getting the currency" + currencyDto.getId());
            throw e;
        }
        return checkIfCurrencyExist(currencyDto);
    }

    public Currency retrieveCurrencyById(Long Id) {
        return repository.findCurrencyById(Id);
    }

    private boolean checkIfCurrencyExist(Currency currency) {
        return repository.existsById(currency.getId());
    }


}
