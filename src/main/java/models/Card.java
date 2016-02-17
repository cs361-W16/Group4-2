package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by michaelhilton on 1/25/16.
 */

public class Card implements Serializable {
    public final int value;
    public final Suit suit;
    public final String name;

    @JsonCreator
    public Card(@JsonProperty("value") int value, @JsonProperty("suit") Suit suit, @JsonProperty("name") String name) {
        this.value = value;
        this.suit = suit;
        this.name = name;
    }

    public String getName() {return name; }

    public Suit getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return this.value + this.suit.toString();
    }
}
