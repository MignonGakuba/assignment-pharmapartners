package com.assignment.pharmapartners.interfaces;

import com.assignment.pharmapartners.models.Currency;

import java.util.List;

public interface ICurrencyService extends IGenericService<Currency> {

    @Override
    List<Currency> read();

    @Override
    Currency update(Currency currencyDto);

    @Override
    Currency create(Currency currencyDto);

    @Override
    boolean delete(Currency currencyDto);
}
