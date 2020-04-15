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
 *
 * @author frank
 */
public class FieldProductTableCalculator implements Calculator<Field> {

    public FieldProductTableCalculator() {
    }

    @Override
    public Map<FieldElement, Map<FieldElement, FieldElement>> evaluate(Field field) throws EvaluationException{
        Set<FieldElement> set = (Set<FieldElement>) field.getProperty(FieldProperty.ELEMENTS);
        
        Map<FieldElement, Map<FieldElement, FieldElement>> result = new HashMap<FieldElement, Map<FieldElement, FieldElement>>();
        for(FieldElement p1 : set){
            result.put(p1, new HashMap<FieldElement, FieldElement>());
            for(FieldElement p2 : set){
                result.get(p1).put(p2, p1.times(p2));
            }
        }
        
        return result;
    }
}
