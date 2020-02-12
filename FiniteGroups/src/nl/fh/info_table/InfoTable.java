/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.info_table;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to support lazy evaluation
 * 
 * @author frank
 */
public class InfoTable {
    private final Map<Property,Value> values;
    private final Map<Property,Calculator> calculators;

    public InfoTable() {
        this.values = new HashMap<Property,Value>();
        this.calculators = new HashMap<Property,Calculator>();
    }
    
    /**
     * 
     * @param propertyType defines what property will be retrieved
     * @return the value of that property
     * @throws InfoTableException 
     */
    public Value getValue(Property propertyType) throws InfoTableException{
        if(!values.containsKey(propertyType)){
            calculateAndStoreMissingValue(propertyType);
        }
        return values.get(propertyType);
    }

    private void calculateAndStoreMissingValue(Property propertyType) throws InfoTableException {
        Calculator calculator = calculators.get(propertyType);
        if(calculator == null){
            throw new InfoTableException("no calculator loaded: " + propertyType.toString());
        }
        values.put(propertyType, calculator.evaluate(this));
    }
    
    /**
     * 
     * @param calc Calculator added to this lazy evaluator, overrides previous entries
     */
    public void add(Calculator calc){
        this.calculators.put(calc.getProperty(), calc);
    }
}
