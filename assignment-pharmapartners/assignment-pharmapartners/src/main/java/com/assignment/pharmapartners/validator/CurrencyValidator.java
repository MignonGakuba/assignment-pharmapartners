package com.assignment.pharmapartners.validator;

import com.assignment.pharmapartners.interfaces.IValidator;
import com.assignment.pharmapartners.models.Currency;
import org.springframework.stereotype.Component;


/**
 * Responsible  to check on the Entity Currency
 * Its validate on Currency object on the properties.
 */
@Component
public class CurrencyValidator implements IValidator {


    /**
     * if retrievedCurrency object does not break any assumption of system
     *
     * @return constructed Currency object without sentive information for the client-side
     */
    @Override
    public boolean validationCurrency(Currency retrievedCurrency) {
        try {
            return !isNullOrEmpty(retrievedCurrency.getName()) && !isNullOrEmpty(retrievedCurrency.getTicker()) && !isNullOrEmpty(retrievedCurrency.getMarket_cap()) && !isNullOrEmpty(retrievedCurrency.getNumber_of_coins());
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * if retrievedCurrency object does not break any assumption of system
     *
     * @return true| false returns if the createdCurrency object is validate
     */
    @Override
    public boolean validCreateCurrency(Currency newCreatedCurrency) {
        return !isNullOrEmpty(newCreatedCurrency.getName()) && !isNullOrEmpty(newCreatedCurrency.getTicker()) && !isNullOrEmpty(newCreatedCurrency.getMarket_cap()) && !isNullOrEmpty(newCreatedCurrency.getNumber_of_coins());
    }

    /**
     * Methode to protected sensitive information
     *
     * @return constructed Currency object without sentive information for the client-side
     */
    @Override
    public Currency sanitize(Currency retrievedCurrency) {
        return null;
    }

    private boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
        // Or return s == null || s.isBlank(); in Java 11+
    }
}
