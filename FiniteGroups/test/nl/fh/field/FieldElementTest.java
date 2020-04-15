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

import nl.fh.factory.FieldFactory;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class FieldElementTest {

    @Test
    public void testEquals() {
        FieldFactory fac = new FieldFactory();
        Field field = fac.getField(9);
        
        FieldElement f1 = new FieldElement(new Polynomial(new int[]{2,1,1}), field);
        FieldElement f2 = new FieldElement(new Polynomial(new int[]{1,1}), field);
        
        assertEquals(f1,f2);
    }
    
    @Test
    public void testPlus() {
        FieldFactory fac = new FieldFactory();
        Field field = fac.getField(9);
        
        FieldElement f00 = new FieldElement(new Polynomial(new int[]{0,0}), field);
        FieldElement f01 = new FieldElement(new Polynomial(new int[]{0,1}), field);
        FieldElement f22 = new FieldElement(new Polynomial(new int[]{2,2}), field);
        FieldElement f12 = new FieldElement(new Polynomial(new int[]{1,2}), field);
        FieldElement f21 = new FieldElement(new Polynomial(new int[]{2,1}), field);        
        
        assertEquals(f00, f00.plus(f00));
        assertEquals(f22, f22.plus(f00));
        assertEquals(f22, f00.plus(f22));
        assertEquals(f21, f12.plus(f12));
        assertEquals(f01, f12.plus(f22));
    }

    @Test
    public void testMinus() {

        FieldFactory fac = new FieldFactory();
        Field field = fac.getField(9);
        
        FieldElement f00 = new FieldElement(new Polynomial(new int[]{0,0}), field);
        FieldElement f22 = new FieldElement(new Polynomial(new int[]{2,2}), field);
        FieldElement f12 = new FieldElement(new Polynomial(new int[]{1,2}), field);
        FieldElement f11 = new FieldElement(new Polynomial(new int[]{1,1}), field);
        FieldElement f20 = new FieldElement(new Polynomial(new int[]{2,0}), field);           
        
        assertEquals(f00, f00.minus(f00));
        assertEquals(f22, f22.minus(f00));
        assertEquals(f11, f00.minus(f22));
        assertEquals(f00, f12.minus(f12));
        assertEquals(f20, f12.minus(f22));
    }

    @Test
    public void testNegative() {

        FieldFactory fac = new FieldFactory();
        Field field = fac.getField(9);
        
        FieldElement f00 = new FieldElement(new Polynomial(new int[]{0,0}), field);
        FieldElement f22 = new FieldElement(new Polynomial(new int[]{2,2}), field);
        FieldElement f12 = new FieldElement(new Polynomial(new int[]{1,2}), field);
        FieldElement f11 = new FieldElement(new Polynomial(new int[]{1,1}), field);
        FieldElement f21 = new FieldElement(new Polynomial(new int[]{2,1}), field);  
        
        assertEquals(f00, f00.negative());
        assertEquals(f22, f11.negative());
        assertEquals(f12, f21.negative());
    }

    @Test
    public void testTimes() {
        FieldFactory fac = new FieldFactory();
        Field field = fac.getField(9);
        
        FieldElement f00 = new FieldElement(new Polynomial(new int[]{0,0}), field);
        FieldElement f22 = new FieldElement(new Polynomial(new int[]{2,2}), field);
        FieldElement f12 = new FieldElement(new Polynomial(new int[]{1,2}), field);
        FieldElement f01 = new FieldElement(new Polynomial(new int[]{0,1}), field);
        FieldElement f20 = new FieldElement(new Polynomial(new int[]{2,0}), field);  
        FieldElement f10 = new FieldElement(new Polynomial(new int[]{1,0}), field);         
        
        assertEquals(f00, f00.times(f00));
        assertEquals(f00, f22.times(f00));
        assertEquals(f00, f00.times(f22));
        assertEquals(f01, f12.times(f12));
        assertEquals(f10, f12.times(f22));        
    }
    
    @Test
    public void testTimesScalar() {
        FieldFactory fac = new FieldFactory();
        Field field = fac.getField(9);
        
        FieldElement f00 = new FieldElement(new Polynomial(new int[]{0,0}), field);
        FieldElement f22 = new FieldElement(new Polynomial(new int[]{2,2}), field);
        FieldElement f12 = new FieldElement(new Polynomial(new int[]{1,2}), field);
        FieldElement f21 = new FieldElement(new Polynomial(new int[]{2,1}), field);         
        
        assertEquals(f00, f00.times(2));
        assertEquals(f00, f22.times(0));
        assertEquals(f00, f00.times(1));
        assertEquals(f21, f12.times(2));
        assertEquals(f12, f12.times(4));   
    }

    @Test
    public void testInverse() {
        FieldFactory fac = new FieldFactory();
        Field field = fac.getField(9);
        
//        FieldElement f00 = new FieldElement(new Polynomial(new int[]{0,0}), field);
        FieldElement f01 = new FieldElement(new Polynomial(new int[]{0,1}), field);
        FieldElement f02 = new FieldElement(new Polynomial(new int[]{0,2}), field);
        FieldElement f10 = new FieldElement(new Polynomial(new int[]{1,0}), field); 
        FieldElement f11 = new FieldElement(new Polynomial(new int[]{1,1}), field);
        FieldElement f12 = new FieldElement(new Polynomial(new int[]{1,2}), field);
        FieldElement f20 = new FieldElement(new Polynomial(new int[]{2,0}), field);
        FieldElement f21 = new FieldElement(new Polynomial(new int[]{2,1}), field);  
        FieldElement f22 = new FieldElement(new Polynomial(new int[]{2,2}), field);
  
        assertEquals(f10, f10.inverse());
        assertEquals(f20, f20.inverse());  
        assertEquals(f01, f02.inverse());
        assertEquals(f02, f01.inverse()); 
        assertEquals(f11, f21.inverse());
        assertEquals(f21, f11.inverse());  
        assertEquals(f12, f22.inverse());
        assertEquals(f22, f12.inverse());           
        
    }
}
