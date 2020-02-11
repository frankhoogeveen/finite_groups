/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group_def_cyclic;

import nl.fh.group.Element;
import nl.fh.group.Multiplicator;

/**
 *
 * @author frank
 */
public class CyclicMultiplicator implements Multiplicator<CyclicElement> {

    private final int order;
    private CyclicElement unit;

    public CyclicMultiplicator(int order) {
        this.order = order;
        
        this.unit = new CyclicElement();
        this.unit.order = order;
        this.unit.number = 0;
    }

    @Override
    public CyclicElement getProduct(CyclicElement factor1, CyclicElement factor2){
        if((factor1.order != this.order) || (factor2.order != this.order)){
            throw new IllegalArgumentException();
        }
        
        CyclicElement result = new CyclicElement();
        result.order = factor1.order;
        result.number = (factor1.number + factor2.number) % result.order;
        
        return result;
    }

    @Override
    public Element getUnit() {
        return this.unit;
    }

    
}
