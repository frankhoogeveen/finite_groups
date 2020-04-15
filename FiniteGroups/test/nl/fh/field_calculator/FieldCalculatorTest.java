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
package nl.fh.field_calculator;

import java.util.List;
import java.util.Map;
import java.util.Set;
import nl.fh.checker.FieldChecker;
import nl.fh.calculator.EvaluationException;
import nl.fh.factory.FieldFactory;
import nl.fh.field.Field;
import nl.fh.field.FieldElement;
import nl.fh.field_calculators.FieldProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class FieldCalculatorTest {
    
   public FieldCalculatorTest(){
       
   }
   
   @Test
   public void FieldElementsTest() throws EvaluationException{
        int ORDER = 25;
        FieldFactory fac = new FieldFactory();
        Field field = fac.getField(ORDER);
        
        Set<FieldElement> elements = (Set<FieldElement>) field.getProperty(FieldProperty.ELEMENTS);
        FieldElement one = (FieldElement) field.getProperty(FieldProperty.ONE);
        FieldElement zero = (FieldElement) field.getProperty(FieldProperty.ZERO);
        
        assertEquals(ORDER, elements.size()); 
        assertTrue(elements.contains(one));
        assertTrue(elements.contains(zero));
   }
   
   @Test
   public void FieldInverseTest() throws EvaluationException{
        int ORDER = 8;
        FieldFactory fac = new FieldFactory();
        Field field = fac.getField(ORDER);
        
        Set<FieldElement> elements = (Set<FieldElement>) field.getProperty(FieldProperty.ELEMENTS);
        FieldElement one = (FieldElement) field.getProperty(FieldProperty.ONE);
        FieldElement zero = (FieldElement) field.getProperty(FieldProperty.ZERO);        
        Map<FieldElement, FieldElement> inv = (Map<FieldElement, FieldElement>) field.getProperty(FieldProperty.INVERSETABLE);
        
        assertFalse(inv.keySet().contains(zero));
        
        for(FieldElement f : elements){
            if(!f.isZero()){
                assertEquals(one, f.times(inv.get(f)));
                assertEquals(one, inv.get(f).times(f));
            }
        }
   }
   
   @Test
   public void DiscreteLogarithmTest() throws EvaluationException{
        int ORDER = 49;
        FieldFactory fac = new FieldFactory();
        Field field = fac.getField(ORDER);
        
        Set<FieldElement> set = (Set<FieldElement>) field.getProperty(FieldProperty.ELEMENTS);
        List<FieldElement> powers = (List<FieldElement>) field.getProperty(FieldProperty.PRIMITIVEPOWERS);
        Map<FieldElement, Integer> log = (Map<FieldElement, Integer>) field.getProperty(FieldProperty.LOGTABLE);
        int order = (int) field.getProperty(FieldProperty.ORDER);
        
        for(FieldElement f : set){
            if(!f.isZero()){
                int k = log.get(f);
                
                FieldElement f3 = f.times(f).times(f);
                int k3 = (3 * k) % (order - 1);
                
                assertEquals(f3, powers.get(k3));
            }
        }
   }
   
    @Test
    public void FieldCheckerTest() throws EvaluationException{
        FieldChecker check = new FieldChecker(true);
        
        int ORDER = 27;
        FieldFactory fac = new FieldFactory();
        Field field = fac.getField(ORDER);
        
        assertTrue(check.isField(field));
    }
}
