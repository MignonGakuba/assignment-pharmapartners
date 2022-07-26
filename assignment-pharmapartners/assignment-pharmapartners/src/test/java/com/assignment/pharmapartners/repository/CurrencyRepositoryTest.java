package com.assignment.pharmapartners.repository;

import com.assignment.pharmapartners.models.Currency;
import com.assignment.pharmapartners.server.CurrencyController;
import com.assignment.pharmapartners.service.CurrencyService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@WebMvcTest(CurrencyController.class)
@AutoConfigureMockMvc
public class CurrencyRepositoryTest {


    @MockBean
    private CurrencyRepository currencyRepository;

    @MockBean
    private CurrencyService currencyService;


    @Test
    public void injectedComponentsAreNotNull() {
        Assert.assertNotNull(currencyRepository);

    }

    @Test
    public void whenSaved_thenFindsByeId() {
        Currency currency = new Currency.CurrencyBuilder(1L, "Bitcoin", "BTC")
                .NUMBER_OF_COINS("16.770.000")
                .MARKET_CAP("189.580.000.000")
                .build();

        currencyRepository.save(currency);
        Currency findCurrency = currencyRepository.findCurrencyById(currency.getId());
        Assert.assertNotNull(findCurrency);
    }

    @Test
    public void whenSaved_thenFindCurrencyById() {

        Currency  currency = new Currency.CurrencyBuilder(6L, "BTC", "Bitcoin")
                .NUMBER_OF_COINS("16.770.000")
                .MARKET_CAP("189.344.000.000")
                .build();

        System.out.println(currency.getId());

        currencyRepository.save(currency);

        Currency findCurrency = currencyRepository.findCurrencyById(currency.getId());
        System.out.println(findCurrency);
        Assertions.assertNotNull(findCurrency);
    }

}
