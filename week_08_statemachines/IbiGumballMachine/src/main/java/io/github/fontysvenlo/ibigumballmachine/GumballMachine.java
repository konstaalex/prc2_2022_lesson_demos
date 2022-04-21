/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.fontysvenlo.ibigumballmachine;

/**
 *
 * @author ibrahimkouzak
 */
public class GumballMachine {

    State soldOutState;
    State noQuarterState;
    State hasQuarterState;
    State soldState;
    State winnerState;

    State state = soldOutState;

    private int numberGumballs = 0;

    private GumballMachine(int numberGumballs) {
        this.numberGumballs = numberGumballs;
    }

    private final GumballMachine init() {
        this.soldOutState = new SoldOutState(this);
        this.noQuarterState = new NoQuarterState(this);
        this.hasQuarterState = new HasQuarterState(this);
        this.soldState = new SoldState(this);
        this.winnerState = new WinnerState(this);
        if (numberGumballs > 0) {
            state = noQuarterState;
        } else {
            state = soldOutState;
        }
        return this;
    }

    public static GumballMachine create(int numberOfBalls) {
        return new GumballMachine(numberOfBalls).init();
    }

    public int getNumberGumballs() {
        return numberGumballs;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public State getNoQuarterState() {
        return noQuarterState;
    }

    public State getHasQuarterState() {
        return hasQuarterState;
    }

    public State getSoldState() {
        return soldState;
    }

    public State getWinnerState() {
        return winnerState;
    }

    public State getState() {
        return state;
    }

    public void insertQuarter() {
        this.state.insertQuarter();
    }

    public void ejectQuarter() {
        this.state.ejectQuarter();
    }

    public void turnCrank() {
        this.state.turnCrank();
        this.state.dispense();
    }

    @Override
    public String toString() {
        return "GumballMachine{" + "state=" + state + ", count=" + numberGumballs + '}';
    }

    void releaseBall() {
        System.out.println("A gumball comes rolling out the slot!!!");
        if (this.numberGumballs != 0) {
            this.numberGumballs = numberGumballs - 1;
        }
    }

}
