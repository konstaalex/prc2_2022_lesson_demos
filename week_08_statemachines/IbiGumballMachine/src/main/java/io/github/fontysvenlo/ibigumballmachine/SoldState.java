/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.fontysvenlo.ibigumballmachine;

/**
 *
 * @author ibrahimkouzak
 */
public class SoldState implements State{

    GumballMachine gumballMachine;

    public SoldState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void dispense() {
        this.gumballMachine.releaseBall();
        if (gumballMachine.getNumberGumballs() > 0) {
            this.gumballMachine.setState(gumballMachine.noQuarterState);
        } else {
            System.out.println("Oops, out of gumballs!");
            this.gumballMachine.setState(gumballMachine.soldOutState);
        }
    }

}
