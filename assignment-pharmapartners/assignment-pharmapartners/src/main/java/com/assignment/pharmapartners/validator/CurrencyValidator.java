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
    public void validationCurrency(Currency retrievedCurrency) {
        try {
            if (!isNullOrEmpty(retrievedCurrency.getName()) && !isNullOrEmpty(retrievedCurrency.getTICKER()) && !isNullOrEmpty(retrievedCurrency.getMARKET_CAP())) {
                isNullOrEmpty(retrievedCurrency.getNUMBER_OF_COINS());

            }
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
        return !isNullOrEmpty(newCreatedCurrency.getName()) || !isNullOrEmpty(newCreatedCurrency.getTICKER()) || !isNullOrEmpty(newCreatedCurrency.getMARKET_CAP()) || !isNullOrEmpty(newCreatedCurrency.getNUMBER_OF_COINS());
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
