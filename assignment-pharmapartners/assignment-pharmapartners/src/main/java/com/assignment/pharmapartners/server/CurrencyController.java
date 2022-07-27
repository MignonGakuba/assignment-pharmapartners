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
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static jdk.nashorn.internal.objects.NativeMath.log;


/**
 * com.assignment.pharmapartners.server @RequestMapping(value = "/api/currencies")
 * This controller is responsible for making restful web services
 * Allows the class to handle the request made by the client.
 */
@RequestMapping(value = "/api/currencies")
@RestController
public class CurrencyController {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyController.class);

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    @Lazy
    private CurrencyValidator validator;

    /**
     * Create get methode to get all currencies
     * GET/api/currencies Get all list of records
     *
     * @return ResponseEntity <JsonResult>
     */

    @GetMapping(value = "/get-all-currencies")
    public ResponseEntity<JsonResult> getAllCurrencies(@RequestParam(required = false, value = "page", defaultValue = "0") Integer page,
                                                       @RequestParam(required = false, value = "sort", defaultValue = "name") String sort,
                                                       @RequestParam(required = false, value = "size", defaultValue = "20") Integer size) {

        Pageable firstPageWithThreeElements = PageRequest.of(page, size, Sort.by(sort).descending());
        List<Currency> requestedCurrency = currencyService.requestedCurrencies(firstPageWithThreeElements);
        logger.info("Contains:"+String.valueOf(requestedCurrency));

        logger.info("Get all currencies from the memory database");
        JsonResult result = new JsonResult();
        try {
            List<Currency> currencies = currencyService.read();
            if (currencies != null) {
                result.setTimestamp(LocalDateTime.now());
                result.setResult(true);
                result.setItem(currencies);
                result.setMessage("Currencies is successfully retrieved");
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                result.setResult(false);
                result.setMessage("Failed to get the currencies.");
                result.setErrorCode(ErrorCode.FAILED_RETRIEVING_ALL_CURRENCIES);
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            result.setResult(false);
            result.setErrorCode(ErrorCode.GENERIC_OR_UNKNOWN);
            result.setErrorMessage(e.toString());
            logger.warn(e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Create POST methode on create a new record
     * Post/api/currencies/[create-currency]	Get a specific record on id
     *
     * @return ResponseEntity <JsonResult>
     */

    @PostMapping(value = "/create-currency", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> createCurrency(@RequestBody String json) {
        logger.info("create a new currency record.");
        JsonResult result = new JsonResult();
        try {
            Currency requestedCurrency = (Currency) JsonLogic.getObject(Currency.class, json);
            if (validator.validCreateCurrency(requestedCurrency)) {
                Currency createdCurrency = currencyService.create(requestedCurrency);
                result.setTimestamp(LocalDateTime.now());
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
            log(getClass(), "The requested record doesn't pass the validation");
            result.setTimestamp(LocalDateTime.now());
            result.setResult(false);
            result.setMessage("Failed creating this currency. The request record doesn't pass the validation");
            result.setErrorCode(ErrorCode.FAILED_CREATING_CURRENCY);
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            result.setTimestamp(LocalDateTime.now());
            result.setResult(false);
            result.setErrorCode(ErrorCode.GENERIC_OR_UNKNOWN);
            result.setErrorMessage(e.toString());
            logger.warn(e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Create methode on currency on id
     *
     * @param id GET/api/currencies/[currency]/{id}	Get a specific record on id
     * @return ResponseEntity <JsonResult>
     */

    @GetMapping(value = "/currency/{id}")
    public ResponseEntity<JsonResult> getCurrencyById(@PathVariable(value = "id") long id) {
        logger.info("Get currency by param {id}");
        JsonResult result = new JsonResult();
        try {
            Currency requestedCurrency = currencyService.retrieveCurrencyById(id);
            if (requestedCurrency != null) {
                result.setTimestamp(LocalDateTime.now());
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
            result.setErrorCode(ErrorCode.GENERIC_OR_UNKNOWN);
            result.setErrorMessage(e.toString());
            logger.warn(e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Update methode on the whole object
     * //PUT/api/currencies/[currency Update a specific record
     *
     * @return ResponseEntity <JsonResult>
     */

    @PutMapping(value = "/update-currency", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> updateCurrency(@RequestBody String json) {

        logger.info("Updating a currency record with new values");
        JsonResult result = new JsonResult();
        try {
            Currency requestedCurrency = (Currency) JsonLogic.getObject(Currency.class, json);
            validator.validationCurrency(requestedCurrency);
            Currency updatedCurrency = currencyService.update(requestedCurrency);
            if (updatedCurrency != null) {
                result.setResult(true);
                result.setTimestamp(LocalDateTime.now());
                result.setItem(updatedCurrency);
                result.setMessage("Currency is successfully updated");
                return new ResponseEntity<>(result, HttpStatus.OK);

            } else {
                result.setResult(false);
                result.setMessage("Failed updating this currency.");
                result.setErrorCode(ErrorCode.FAILED_UPDATE_CURRENCY);
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            result.setResult(false);
            result.setErrorCode(ErrorCode.GENERIC_OR_UNKNOWN);
            result.setErrorMessage(e.toString());
            logger.warn(e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Delete methode on the whole object
     * DELETE/api/currencies/[identifier Delete a specific record
     *
     * @return ResponseEntity <JsonResult>
     */

    @DeleteMapping(value = "/delete-currency/{id}")
    public ResponseEntity<JsonResult> deleteCurrency(@PathVariable(value = "id") long id) {
        logger.info("Delete a currency record");
        JsonResult result = new JsonResult();
        result.setTimestamp(LocalDateTime.now());

        try {
            Currency requestedCurrency = currencyService.retrieveCurrencyById(id);
            validator.validationCurrency(requestedCurrency);
            boolean IsDeleted = currencyService.delete(requestedCurrency);
            result.setTimestamp(LocalDateTime.now());
            if (IsDeleted) {
                result.setResult(true);
                result.setMessage("Currency is successfully deleted");
                return new ResponseEntity<>(result, HttpStatus.OK);

            } else {
                result.setResult(false);
                result.setMessage("Failed deleting this currency.");
                result.setErrorCode(ErrorCode.FAILED_DELETE_CURRENCY);
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            result.setTimestamp(LocalDateTime.now());
            result.setResult(false);
            result.setErrorCode(ErrorCode.GENERIC_OR_UNKNOWN);
            result.setErrorMessage(e.toString());
            log(this.getClass(), e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
