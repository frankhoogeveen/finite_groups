/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.info_table;

/**
 *
 * @author frank
 */
public interface Calculator {
    
     /**
     * 
     * @return a enum describing the property calculated by this
     */
    public Property getProperty();
    
    /**
     * 
     * @param info
     * @return the output of this calculator, using info as input
     * @throws InfoTableException when the calculation does not succeed
     */
    public Value evaluate(InfoTable info) throws InfoTableException;
}
