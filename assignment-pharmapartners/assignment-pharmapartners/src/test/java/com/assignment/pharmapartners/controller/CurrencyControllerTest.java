package com.assignment.pharmapartners.controller;

import com.assignment.pharmapartners.models.Currency;
import com.assignment.pharmapartners.repository.CurrencyRepository;
import com.assignment.pharmapartners.server.CurrencyController;
import com.assignment.pharmapartners.service.CurrencyService;
import com.assignment.pharmapartners.validator.CurrencyValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CurrencyController.class)
@AutoConfigureMockMvc
public class CurrencyControllerTest {

    Currency RECORD_1 = new Currency.CurrencyBuilder(1L, "Bitcoin", "BTC")
            .NUMBER_OF_COINS("16.770.000")
            .MARKET_CAP("189.580.000.000")
            .build();
    Currency RECORD_2 = new Currency.CurrencyBuilder(2L, "Ethereum", "ETH")
            .NUMBER_OF_COINS("96.710.000")
            .MARKET_CAP("69.280.000.000")
            .build();
    Currency RECORD_3 = new Currency.CurrencyBuilder(3L, "Ripple", "XRP")
            .NUMBER_OF_COINS("38.590.000.000")
            .MARKET_CAP("64.75.000..000")
            .build();
    Currency RECORD_4 = new Currency.CurrencyBuilder(3L, "'Bitcoin'", "BTC")
            .NUMBER_OF_COINS("16.770.000")
            .MARKET_CAP("189.580.000.000")
            .build();
    Currency RECORD_5 = new Currency.CurrencyBuilder(3L, "''BitcoinCash''", "BCH")
            .NUMBER_OF_COINS("16.670.000")
            .MARKET_CAP("189.580.000.000")
            .build();

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private CurrencyValidator currencyValidator;
    @MockBean
    private CurrencyRepository currencyRepository;
    @MockBean
    private CurrencyService currencyService;

    @Test
    public void getAllRecords_success() throws Exception {

        List<Currency> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3, RECORD_4, RECORD_5));

        Mockito.when(currencyService.read()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/currencies/get-all-currencies")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void getCurrencyById_success() throws Exception {
        Mockito.when(currencyService.retrieveCurrencyById(RECORD_1.getId())).thenReturn((RECORD_1));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/currencies/currency/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.item.name", is("Bitcoin")));
    }

    @Test
    public void createRecord_success() throws Exception {
        Currency record = new Currency.CurrencyBuilder(6L, "DogeCoin", "DGC")
                .NUMBER_OF_COINS("162.770.000")
                .MARKET_CAP("189.5820.000.000")
                .build();

        Mockito.when(currencyService.create(record)).thenReturn(record);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/currencies/create-currency")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(record));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.item.name", is("DogeCoin")));
    }


    @Test
    public void updateCurrencyRecord_success() throws Exception {

        Currency updatedRecord = new Currency.CurrencyBuilder(7L, "Ethereum", "ETH")
                .NUMBER_OF_COINS("96.000.710.000")
                .MARKET_CAP("69.280.000.000")
                .build();

        Mockito.when(currencyService.retrieveCurrencyById(updatedRecord.getId())).thenReturn(RECORD_1);
        Mockito.when(currencyService.create(updatedRecord)).thenReturn(updatedRecord);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/currencies/update-currency")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(updatedRecord));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.item.name", is("Ethereum")));
    }


    @Test
    public void updateCurrencyRecord_recordNotFound() throws Exception {
        Currency updatedRecord = new Currency.CurrencyBuilder(8L, "Ethereum", "ETH")
                .NUMBER_OF_COINS("96.000.710.000")
                .MARKET_CAP("69.280.000.000")
                .build();

        Mockito.when(currencyRepository.findById(updatedRecord.getId())).thenReturn(null);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/currencies/create-currency")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(updatedRecord));

        mockMvc.perform(mockRequest)
                .andExpect(status().isBadRequest())
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof ChangeSetPersister.NotFoundException))
                .andExpect(result ->
                        assertEquals("Patient with ID 5 does not exist.", result.getResolvedException().getMessage()));
    }


    @Test
    public void deleteCurrencyById_success() throws Exception {

        Currency deletedRecord = new Currency.CurrencyBuilder(8L, "Ethereum", "ETH")
                .NUMBER_OF_COINS("96.000.710.000")
                .MARKET_CAP("69.280.000.000")
                .build();

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/currencies/delete-currency")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(deletedRecord));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());

    }

    @Test
    public void deleteCurrencyById_notFound() throws Exception {
        Currency deleteRecord = new Currency.CurrencyBuilder(8L, "Ethereum", "ETH")
                .NUMBER_OF_COINS("96.000.710.000")
                .MARKET_CAP("69.280.000.000")
                .build();

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/currencies/create-currency")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(deleteRecord));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(status().isBadRequest());
    }

}
