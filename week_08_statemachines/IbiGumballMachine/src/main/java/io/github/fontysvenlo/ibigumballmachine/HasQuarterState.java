/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.fontysvenlo.ibigumballmachine;

import java.util.Random;

/**
 *
 * @author ibrahimkouzak
 */
public class HasQuarterState implements State {

    Random randomWinner = new Random();
    GumballMachine gumballMachine;

    public HasQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void turnCrank() {
        System.out.println("You turned!!!");
        int winner = randomWinner.nextInt(10);
        if (winner == 0 && (gumballMachine.getNumberGumballs() > 1)) {
            this.gumballMachine.setState(gumballMachine.winnerState);
        } else {
            this.gumballMachine.setState(gumballMachine.soldState);
        }
    }

    @Override
    public void ejectQuarter() {
        System.out.println("The coin is being ejected.");
        this.gumballMachine.setState(gumballMachine.noQuarterState);
    }

}
