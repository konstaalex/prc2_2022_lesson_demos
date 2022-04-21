/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.fontysvenlo.ibigumballmachine;

/**
 *
 * @author ibrahimkouzak
 */
public class WinnerState implements State {

    GumballMachine gumballMachine;

    public WinnerState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void dispense() {
        System.out.println("You are a winner, you get two gumballs for your quarter!!!");
        this.gumballMachine.releaseBall();
        if (this.gumballMachine.getNumberGumballs() == 0) {
            this.gumballMachine.setState(gumballMachine.soldOutState);
        } else {
            this.gumballMachine.releaseBall();
            if (this.gumballMachine.getNumberGumballs() > 0) {
                this.gumballMachine.setState(gumballMachine.hasQuarterState);
            } else {
                System.out.println("Oops, we are out!!!");
                this.gumballMachine.setState(gumballMachine.soldOutState);
            }
        }
    }
}
