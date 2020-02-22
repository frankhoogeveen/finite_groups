/*
 * Copyright (C) 2020 Frank Hoogeveen
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package nl.fh.info_table;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class to support lazy evaluation
 * 
 * @author frank
 */
public class InfoTable {
    private static final Logger LOGGER = Logger.getLogger(InfoTable.class.getName());
    
    private final Map<Property,Value> values;
    private final Map<Property,Calculator> calculators;

    
    public InfoTable() {
        
        this.values = new HashMap<Property,Value>();
        this.calculators = new HashMap<Property,Calculator>();
        
        LOGGER.setLevel(Level.WARNING);
    }
    
    /**
     * 
     * @param propertyType defines what property will be retrieved
     * @return the value of that property
     * @throws InfoTableException used when calculators do not exist or crash
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
            
            String mess = "no calculator loaded: " + propertyType.toString();
            LOGGER.warning(mess);
            throw new InfoTableException(mess);
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
