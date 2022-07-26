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
    private Long Id;

    @Column(nullable = false)
    private String Name;

    @Column(nullable = false)
    private String TICKER;

    @Column(nullable = false)
    private String NUMBER_OF_COINS;

    @Column(nullable = false)
    private String MARKET_CAP;


    //Constructor
    public Currency() {

    }

    private Currency(CurrencyBuilder builder) {
        this.Name = builder.Name;
        this.TICKER = builder.TICKER;
        this.NUMBER_OF_COINS = builder.NUMBER_OF_COINS;
        this.MARKET_CAP = builder.MARKET_CAP;
    }

    public Long getId() {
        return Id;
    }

    //All getter, and NO setter to provide immutability
    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTICKER() {
        return TICKER;
    }

    public void setTICKER(String TICKER) {
        this.TICKER = TICKER;
    }

    public String getNUMBER_OF_COINS() {
        return NUMBER_OF_COINS;
    }

    public void setNUMBER_OF_COINS(String NUMBER_OF_COINS) {
        this.NUMBER_OF_COINS = NUMBER_OF_COINS;
    }

    public String getMARKET_CAP() {
        return MARKET_CAP;
    }

    public void setMARKET_CAP(String MARKET_CAP) {
        this.MARKET_CAP = MARKET_CAP;
    }


    @Override
    public String toString() {
        return "Currency{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", TICKER='" + TICKER + '\'' +
                ", NUMBER_OF_COINS='" + NUMBER_OF_COINS + '\'' +
                ", MARKET_CAP='" + MARKET_CAP + '\'' +
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

        public Long getId() {
            return Id;
        }

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
            Currency currency = new Currency(this);
            return currency;
        }


    }

}

