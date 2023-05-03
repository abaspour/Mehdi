package com.abbaspour.model;

import java.math.BigInteger;
/**
 * @author Mehdi Abbaspour
 * @version 1.0
 * created: May 1, 2023
 */
public class BitMap {

    private Boolean machine_on;
    private Boolean grinding_beans;
    private Boolean empty_grounds_fault;
    private Boolean water_empty_fault;
    private Integer number_of_cups_today; // 5 - 12
    private Boolean descale_required;
    private Boolean have_another_one_carl;

    public Boolean getMachine_on() {
        return machine_on;
    }

    public void setMachine_on(Boolean machine_on) {
        this.machine_on = machine_on;
    }

    public Boolean getGrinding_beans() {
        return grinding_beans;
    }

    public void setGrinding_beans(Boolean grinding_beans) {
        this.grinding_beans = grinding_beans;
    }

    public Boolean getEmpty_grounds_fault() {
        return empty_grounds_fault;
    }

    public void setEmpty_grounds_fault(Boolean empty_grounds_fault) {
        this.empty_grounds_fault = empty_grounds_fault;
    }

    public Boolean getWater_empty_fault() {
        return water_empty_fault;
    }

    public void setWater_empty_fault(Boolean water_empty_fault) {
        this.water_empty_fault = water_empty_fault;
    }

    public Integer getNumber_of_cups_today() {
        return number_of_cups_today;
    }

    public void setNumber_of_cups_today(Integer number_of_cups_today) {
        this.number_of_cups_today = number_of_cups_today;
    }

    public Boolean getDescale_required() {
        return descale_required;
    }

    public void setDescale_required(Boolean descale_required) {
        this.descale_required = descale_required;
    }

    public Boolean getHave_another_one_carl() {
        return have_another_one_carl;
    }

    public void setHave_another_one_carl(Boolean have_another_one_carl) {
        this.have_another_one_carl = have_another_one_carl;
    }

}
