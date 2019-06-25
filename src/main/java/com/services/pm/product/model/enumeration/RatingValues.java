package com.services.pm.product.model.enumeration;

import lombok.Getter;

@Getter
public enum RatingValues {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    private int value;

    RatingValues(int value) {
        this.value = value;
    }

}
