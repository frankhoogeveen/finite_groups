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
package nl.fh.field_calculators;

import nl.fh.calculator.Calculator;
import nl.fh.calculator.Property;
import nl.fh.field.Field;

/**
 *
 * @author frank
 */
public enum FieldProperty implements Property{

    NAME(null),
    ORDER(null),
    CHARACTERISTIC(null),
    POLYNOMIAL(null),
    ELEMENTS(new ElementsCalculator()),
    ZERO(new ZeroCalculator()),
    ONE(new OneCalculator()),
    PRODUCTTABLE(new FieldProductTableCalculator()),
    SUMTABLE(new FieldSumTableCalculator()),
    INVERSETABLE(new FieldInverseTableCalculator()),
    LOGTABLE(new FieldLogTableCalculator()),
    PRIMITIVE(new PrimitiveCalculator()),
    PRIMITIVEPOWERS(new PrimitivePowersCalculator());
    
    private final Calculator calculator;
    
    /**
     * 
     * @param calc the calculator
     * 
     * The caller should know what to do with the output of the calculator
     * , in particular to what class to cast it,
     * 
     */
    private FieldProperty(Calculator<Field> calc){
        this.calculator = calc;
    }

    @Override
    public Calculator getCalculator() {
        return this.calculator;
    }
    
}
