package com.assignment.pharmapartners.server;

import com.assignment.pharmapartners.helper.ErrorCode;
import com.assignment.pharmapartners.helper.JsonLogic;
import com.assignment.pharmapartners.helper.JsonResult;
import com.assignment.pharmapartners.models.Currency;
import com.assignment.pharmapartners.service.CurrencyService;
import com.assignment.pharmapartners.validator.CurrencyValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static jdk.nashorn.internal.objects.NativeMath.log;


@RequestMapping(value = "/api/currencies")
@RestController
public class CurrencyController {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyController.class);

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CurrencyValidator validator;

    //Create get methode to get all currencies
    //GET	/api/currencies	Haalt een lijst met records op
    @GetMapping(value = "/get-all")
    public ResponseEntity<JsonResult> getAllCurrencies( @RequestParam(required = false, value = "page", defaultValue = "0") Integer page,
                                                        @RequestParam(required = false, value = "TICKER", defaultValue ="BTC") String sort ) {

        logger.info("Get all currencies from the memory database");
        JsonResult result = new JsonResult();
        try {
            List<Currency> currencies = currencyService.read();
            if (currencies != null) {
                result.setResult(true);
                result.setItem(currencies);
                result.setMessage("Currencies is successfully retrieved");
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                result.setResult(false);
                result.setMessage("Failed to get a currencies.");
                result.setErrorCode(ErrorCode.FAILED_RETRIEVING_ALL_CURRENCIES);
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            result.setResult(false);
            result.setMessage("Failed to get a currencies.");
            result.setErrorCode(ErrorCode.GENERIC_OR_UNKNOWN);
            result.setErrorMessage(e.toString());
            logger.warn("UNKOWN ERROR");
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }

    ///TODO
    //POST	/api/currencies	Creëert een nieuw record
    @PostMapping(value = "/create-currency", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> createCurrency(@RequestBody String json) {

        log(getClass(), "Create new Currency....");
        JsonResult result = new JsonResult();
        try {
            Currency requestedCurrency = (Currency) JsonLogic.getObject(Currency.class,json);
            if (validator.validCreateCurrency(requestedCurrency)) {
                Currency createdCurrency = currencyService.create(requestedCurrency);
                if (createdCurrency != null) {
                    result.setResult(true);
                    result.setItem(createdCurrency);
                    result.setMessage("Currency is created successfully");
                    return new ResponseEntity<>(result, HttpStatus.OK);
                } else {
                    result.setResult(false);
                    result.setMessage("Failed creating this currency.");
                    result.setErrorCode(ErrorCode.FAILED_CREATING_CURRENCY);
                    return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
                }
            }
            log(getClass(), "Failed creating this currency......");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            result.setResult(false);
            result.setMessage("Failed to creat a currencies.");
            result.setErrorCode(ErrorCode.GENERIC_OR_UNKNOWN);
            result.setErrorMessage(e.toString());
            logger.warn("UNKOWN ERROR");
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }

    //Create get methode on curencey Id
    //GET	/api/currencies/[identifier]	Haalt een specifiek record op id
    @GetMapping(value = "/currency/{id}")
    public ResponseEntity<JsonResult> getCurrencyById(@PathVariable(value="id") long id) {
        log(getClass(), "Get Currency by identifier....");
        JsonResult result = new JsonResult();
        try {
            Currency requestedCurrency = currencyService.retrieveCurrencyById(id);
            if (requestedCurrency != null) {
                result.setResult(true);
                result.setItem(requestedCurrency);
                result.setMessage("Currency is successfully retrieved");
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                result.setResult(false);
                result.setMessage("Failed to get a currency by Id.");
                result.setErrorCode(ErrorCode.FAILED_RETRIEVE_CURRENCY_BY_Id);
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            result.setResult(false);
            result.setMessage("Failed to delete a currencies.");
            result.setErrorCode(ErrorCode.GENERIC_OR_UNKNOWN);
            result.setErrorMessage(e.toString());
            logger.warn("UNKOWN ERROR");
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }


    //Update methode on the Id
    //PUT	/api/currencies/[identifier]	Update een specifiek record
    @PutMapping(value = "/update-currency", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> updateCurrency(@RequestBody String json) {
        log(getClass(), "Updating the Currency....");
        JsonResult result = new JsonResult();
        try {
            Currency requestedCurrency = (Currency) JsonLogic.getObject(Currency.class, json);
            if (validator.validationCurrency(requestedCurrency)) {
                boolean IsDeleted = currencyService.delete(requestedCurrency);
                if (IsDeleted) {
                    result.setResult(true);
                    result.setMessage("Currency is successfully updated");
                    return new ResponseEntity<>(result, HttpStatus.OK);

                } else {
                    result.setResult(false);
                    result.setMessage("Failed updating this currency.");
                    result.setErrorCode(ErrorCode.FAILED_UPDATE_CURRENCY);
                    return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
                }
            }
            log(getClass(), "Failed updating this currency......");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            result.setResult(false);
            result.setMessage("Failed updating this currency.");
            result.setErrorCode(ErrorCode.GENERIC_OR_UNKNOWN);
            result.setErrorMessage(e.toString());
            log(this.getClass(), e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);

        }

    }
}
