package com.assignment.pharmapartners.models;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Currency {

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

    public void setId(Long Id) {
        this.Id = Id;
    }

    @Id
    public Long getId() {
        return Id;
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



}
