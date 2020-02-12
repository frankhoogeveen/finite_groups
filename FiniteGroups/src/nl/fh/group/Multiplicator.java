/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group;

/**
 *
 * @author frank
 * @param <E>   type of elements that can be multiplied by this
 */
public interface Multiplicator<E extends Element> {
    
    /**
     * 
     * @param factor1
     * @param factor2 
     * 
     * @return factor1 * factor2 
     * @throws MultiplicatorException 
     */
    E getProduct(E factor1, E factor2) throws MultiplicatorException;

    /**
     * 
     * @return  the unit element of this multiplicator
     */
    public Element getUnit();
}