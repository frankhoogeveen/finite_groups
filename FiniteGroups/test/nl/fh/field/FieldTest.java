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
package nl.fh.field;

import nl.fh.calculator.EvaluationException;
import nl.fh.factory.FieldFactory;
import nl.fh.field_calculators.FieldProperty;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class FieldTest {
    
    @Test
    public void CreateFieldTest() throws EvaluationException{
        FieldFactory fac = new FieldFactory();
        
        Field f2 = fac.getField(2);
        assertEquals(2, (int) f2.getProperty(FieldProperty.ORDER));
        
        Field f27 = fac.getField(27);
        assertEquals(27, (int) f27.getProperty(FieldProperty.ORDER));
        assertEquals(3, (int) f27.getProperty(FieldProperty.CHARACTERISTIC));
        assertEquals("F_27", (String) f27.getProperty(FieldProperty.NAME));
    }
    

   
   @Test
   public void FieldUnitsTest() throws EvaluationException{
        int ORDER = 9;
        FieldFactory fac = new FieldFactory();
        Field field = fac.getField(ORDER);
        
        FieldElement zero = (FieldElement) field.getProperty(FieldProperty.ZERO);
        FieldElement one = (FieldElement) field.getProperty(FieldProperty.ONE);
        
        assertEquals(zero, zero.plus(zero));
        assertEquals(one, zero.plus(one));
        assertEquals(one, one.plus(zero));
        
        assertEquals(zero, zero.times(zero));
        assertEquals(zero, zero.times(one));
        assertEquals(zero, one.times(zero)); 
        
        assertEquals(one, one.inverse());    
        
        assertEquals(one, one.divided(one));

   }
   
}
