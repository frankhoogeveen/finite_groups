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
import nl.fh.calculator.EvaluationException;
import nl.fh.field.Field;
import nl.fh.field.FieldElement;
import nl.fh.field.Polynomial;

/**
 * Calculator evaluates the multiplicative unit (1) of a field
 * 
 * 
 * @author frank
 */
public class OneCalculator implements Calculator<Field> {

    public OneCalculator() {
    }

    @Override
    public FieldElement evaluate(Field field) throws EvaluationException {
        return new FieldElement(new Polynomial(new int[]{1}), field);
    }
    
}
