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
import java.util.List;
import java.util.Map;
import nl.fh.calculator.Calculator;
import nl.fh.calculator.EvaluationException;
import nl.fh.field.Field;
import nl.fh.field.FieldElement;

/**
 *
 * @author frank
 */
 class FieldLogTableCalculator implements Calculator<Field> {

    @Override
    public Map<FieldElement, Integer> evaluate(Field field) throws EvaluationException {
        List<FieldElement> powers = (List<FieldElement>) field.getProperty(FieldProperty.PRIMITIVEPOWERS);
        
        Map<FieldElement, Integer> result = new HashMap<FieldElement, Integer>();
        for(int i = 0; i < powers.size(); i++){
            result.put(powers.get(i), i);
        }
        
        return result;
    }

    
}
