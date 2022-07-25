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

@Service
public class CurrencyService implements ICurrencyService {

    @Autowired
    private CurrencyRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(CurrencyService.class);

    @Override
    public List<Currency> read() {
        List<Currency> currencies = new ArrayList<>();
        try {
            currencies = repository.findAll(Sort.by(Sort.Direction.ASC, "TICKER"));
        }
        catch (Exception exception){
             logger.warn(exception.getMessage());
        }
        return currencies;
    }

    @Override
    public Currency update(Currency currencyDto) {
        try{
         Currency currencyToUpdate = repository.findCurrencyById(currencyDto.getId());
         currencyToUpdate.setName(currencyDto.getName());
         currencyToUpdate.setNUMBER_OF_COINS(currencyDto.getNUMBER_OF_COINS());
         currencyToUpdate.setMARKET_CAP(currencyDto.getMARKET_CAP());
         repository.save(currencyToUpdate);

        }
        catch (HibernateException e) {
            logger.warn("Error while updating the currency" + currencyDto.getId());
            throw e;
        }

        return null;
    }

    @Override
    public Currency create(Currency object) {
        try{
              repository.save(object);
        }
        catch (HibernateException e) {
            logger.warn("Error while getting the currency" + object.getId());
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


    public Currency retrieveCurrencyById(Long Id){
        return  repository.findCurrencyById(Id);
    }

    private boolean checkIfCurrencyExist(Currency currency) {
        return repository.existsById(currency.getId());
    }


}
