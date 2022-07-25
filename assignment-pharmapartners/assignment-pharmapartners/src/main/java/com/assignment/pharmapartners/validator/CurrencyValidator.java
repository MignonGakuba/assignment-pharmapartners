package com.assignment.pharmapartners.validator;

import com.assignment.pharmapartners.interfaces.IValidator;
import com.assignment.pharmapartners.models.Currency;
import org.springframework.stereotype.Component;

@Component
public class CurrencyValidator implements IValidator {


    @Override
    public void validationCurrency(Currency retrievedCurrency) {


    }

    @Override
    public boolean validCreateCurrency(Currency newCreatedCurrency) {
        return isNullOrEmpty(newCreatedCurrency.getName()) || isNullOrEmpty(newCreatedCurrency.getTICKER()) || isNullOrEmpty(newCreatedCurrency.getMARKET_CAP()) || isNullOrEmpty(newCreatedCurrency.getNUMBER_OF_COINS());
    }
    @Override
    public Currency sanitize(Currency retrievedCurrency) {
        return null;
    }

    private  boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
        // Or return s == null || s.isBlank(); in Java 11+
    }
}
