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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import nl.fh.calculator.Calculator;
import nl.fh.calculator.EvaluationException;
import nl.fh.field.Field;
import nl.fh.field.FieldElement;

/**
 *
 *  Returns a List of FieldElements with
 *  List.get(k) = generator^k for some primitive field element
 * 
 * @author frank
 */
class PrimitivePowersCalculator implements Calculator<Field> {
   
    @Override
    public List<FieldElement> evaluate(Field field) throws EvaluationException {    
        
        int order = (int) field.getProperty(FieldProperty.ORDER);
        FieldElement prim = (FieldElement) field.getProperty(FieldProperty.PRIMITIVE);

        List<FieldElement> result = new ArrayList<FieldElement>();
        
        for(int i = 0; i < order-1; i++){
            result.add(prim.power(i));
        }
        
        return result;
    }
}
