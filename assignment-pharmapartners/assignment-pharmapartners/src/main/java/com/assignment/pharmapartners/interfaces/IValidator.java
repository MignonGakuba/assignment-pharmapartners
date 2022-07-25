package com.assignment.pharmapartners.interfaces;

import com.assignment.pharmapartners.models.Currency;

public interface IValidator {


    void validationCurrency(Currency user);
    boolean validCreateCurrency(Currency user);
    Currency sanitize(Currency user);
}
