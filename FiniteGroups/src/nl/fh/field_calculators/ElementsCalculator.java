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

import java.util.HashSet;
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
public class ElementsCalculator implements Calculator<Field> {

    @Override
    public Set<FieldElement> evaluate(Field field) throws EvaluationException {
        int p = (int) field.getProperty(FieldProperty.CHARACTERISTIC);
        int order = (int) field.getProperty(FieldProperty.ORDER);
        Polynomial poly =  (Polynomial) field.getProperty(FieldProperty.POLYNOMIAL);
        int degree = poly.degree();
        
        Set<FieldElement> result = new HashSet<FieldElement>();
        int[] arr = new int[degree+1];
        for(int i = 0; i < order; i++){
            result.add(new FieldElement(new Polynomial(arr), field));
            increment(arr, p);
        }
        return result;
    }

    public void increment(int[] arr, int p){
        int current = 0;
        while(current < arr.length){
            arr[current] += 1;
            if(arr[current] < p){
                return;
            }
            arr[current] = 0;
            current +=1;
        }
    }
}
