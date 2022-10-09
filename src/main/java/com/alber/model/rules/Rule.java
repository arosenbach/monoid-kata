package com.alber.model.rules;

import com.alber.model.Cab;

import java.util.List;
import java.util.Objects;

@FunctionalInterface
public interface Rule {

    static Rule identity() {
        return cabs -> cabs;
    }

    default Rule combine(Rule after) {
        Objects.requireNonNull(after);
        return cabs -> after.apply(this.apply(cabs));
    }

    List<Cab> apply(List<Cab> cabs);
}