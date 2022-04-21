/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.fontysvenlo.ibigumballmachine;

/**
 *
 * @author ibrahimkouzak
 */
public class SoldOutState implements State {

    GumballMachine gumballMachine;

    public SoldOutState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("Sorry, we are out of gumballs!");
    }

    @Override
    public void turnCrank() {
        System.out.println("You turned but we are soldout!!!");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("Not sure that you entered anything!!!");
    }

    @Override
    public void dispense() {
        System.out.println("We have no gumballs to be dispensed!!!");
    }
}
