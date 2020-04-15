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

import java.util.List;
import java.util.Set;
import nl.fh.calculator.Calculator;
import nl.fh.calculator.EvaluationException;
import nl.fh.field.Field;
import nl.fh.field.FieldElement;
import nl.fh.field.Polynomial;

/**
 *
 * @author frank
 */
class PrimitiveCalculator implements Calculator<Field> {

    private FieldElement unit;
    private FieldElement zero;
    private FieldElement mod;

    @Override
    public FieldElement evaluate(Field field) throws EvaluationException {
        int order = (int) field.getProperty(FieldProperty.ORDER);
        this.unit = (FieldElement) field.getProperty(FieldProperty.ONE);
        this.zero = (FieldElement) field.getProperty(FieldProperty.ZERO);
//        this.mod  = (FieldElement) field.getProperty(FieldProperty.POLYNOMIAL);   //TODO review
        
        Set<FieldElement> set = (Set<FieldElement>) field.getProperty(FieldProperty.ELEMENTS);
        
        for(FieldElement f : set){
            if(multiplicativeOrder(f) == (order-1)){
                return f;
            }
        }
        
        throw new IllegalStateException("could not find multiplicative generator for field");
    }

    private int multiplicativeOrder(FieldElement f) {
         
         if(f.isZero()){
             return 0;
         }
         
         FieldElement current = unit;
         int count = 0;
         
         while(true){
             count += 1;
             current = current.times(f);
             if(current.equals(unit)){
                 return count;
             }
         }
    }
}
