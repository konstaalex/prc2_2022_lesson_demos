/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package io.github.fontysvenlo.ibigumballmachine;

/**
 *
 * @author ibrahimkouzak
 */
public interface State {

    default void insertQuarter() {
        System.out.println("This transaction is not supported in this state");
    }

    default void turnCrank() {
        System.out.println("This transaction is not supported in this state");
    }

    default void ejectQuarter() {
        System.out.println("This transaction is not supported in this state");
    }

    default void dispense() {
        System.out.println("This transaction is not supported in this state");
    }

}
