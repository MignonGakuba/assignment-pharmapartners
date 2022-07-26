package com.assignment.pharmapartners.repository;

import com.assignment.pharmapartners.models.Currency;
import com.assignment.pharmapartners.server.CurrencyController;
import com.assignment.pharmapartners.service.CurrencyService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
    public void whenSaved_thenFindsById() {
        Currency currency = new Currency.CurrencyBuilder(1L, "Bitcoin", "BTC")
                .NUMBER_OF_COINS("16.770.000")
                .MARKET_CAP("189.580.000.000")
                .build();

        currency.setId(1L);

        currencyService.create(currency);

        Currency findCurrency = currencyRepository.findCurrencyById(currency.getId());
        Assert.assertNotNull(findCurrency);
    }

    @Test
    public void whenSaved_thenFindCurrencyById() {

        Currency record = new Currency.CurrencyBuilder(6L, "DogeCoin", "DGC")
                .NUMBER_OF_COINS("162.770.000")
                .MARKET_CAP("189.5820.000.000")
                .build();

        record.setId(6L);

        currencyRepository.save(record);

        Currency findCurrency = currencyRepository.findCurrencyById(record.getId());
        Assertions.assertNotNull(findCurrency);
    }

}
