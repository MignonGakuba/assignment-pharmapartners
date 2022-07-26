package com.assignment.pharmapartners.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Represents a currency
 */
@Entity
public class Currency {

    //All final attributes
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String ticker;

    @Column(nullable = false)
    private String number_of_coins;

    @Column(nullable = false)
    private String market_cap;


    //Constructor
    public Currency() {

    }

    private Currency(CurrencyBuilder builder) {
        this.id = builder.Id;
        this.name = builder.Name;
        this.ticker = builder.TICKER;
        this.number_of_coins = builder.NUMBER_OF_COINS;
        this.market_cap = builder.MARKET_CAP;
    }

    public Long getId() {
        return id;
    }

    //All getter, and NO setter to provide immutability
    public void setId(Long Id) {
        this.id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String TICKER) {
        this.ticker = TICKER;
    }

    public String getNumber_of_coins() {
        return number_of_coins;
    }

    public void setNumber_of_coins(String NUMBER_OF_COINS) {
        this.number_of_coins = NUMBER_OF_COINS;
    }

    public String getMarket_cap() {
        return market_cap;
    }

    public void setMarket_cap(String MARKET_CAP) {
        this.market_cap = MARKET_CAP;
    }


    @Override
    public String toString() {
        return "Currency{" +
                "Id=" + id +
                ", Name='" + name + '\'' +
                ", TICKER='" + ticker + '\'' +
                ", NUMBER_OF_COINS='" + number_of_coins + '\'' +
                ", MARKET_CAP='" + market_cap + '\'' +
                '}';
    }

    /**
     * Implementing Builder Pattern on the Model Currency
     */

    public static class CurrencyBuilder {

        private final Long Id;
        private String Name;
        private String TICKER;
        private String NUMBER_OF_COINS;
        private String MARKET_CAP;

        public CurrencyBuilder(Long Id, String name, String TICKER) {
            this.Id = Id;
            this.Name = name;
            this.TICKER = TICKER;
        }

        public CurrencyBuilder NUMBER_OF_COINS(String NUMBER_OF_COINS) {
            this.NUMBER_OF_COINS = NUMBER_OF_COINS;
            return this;
        }

        public CurrencyBuilder MARKET_CAP(String MARKET_CAP) {
            this.MARKET_CAP = MARKET_CAP;
            return this;
        }

        /**
         * @return constructed Currency object
         */
        public Currency build() {
            return new Currency(this);
        }


    }

}

