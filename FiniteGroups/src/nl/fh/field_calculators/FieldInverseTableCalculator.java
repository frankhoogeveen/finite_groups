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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import nl.fh.calculator.Calculator;
import nl.fh.calculator.EvaluationException;
import nl.fh.field.Field;
import nl.fh.field.FieldElement;

/**
 * Calculates the multiplicative inverses of the field elements
 * 
 * @author frank
 */
public class FieldInverseTableCalculator implements Calculator<Field> {

    public FieldInverseTableCalculator() {
    }

    @Override
    public Map<FieldElement, FieldElement> evaluate(Field field) throws EvaluationException {
        Set<FieldElement> list = (Set<FieldElement>) field.getProperty(FieldProperty.ELEMENTS);
        
        Map<FieldElement,FieldElement> result = new HashMap<FieldElement,FieldElement>();
        for(FieldElement f1 : list){
            if(!f1.isZero()){
                result.put(f1, f1.inverse());
            }
        }
        
        return result;
    }
}
