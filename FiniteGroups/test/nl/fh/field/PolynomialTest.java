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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class PolynomialTest {
    
    @Test
    public void PolynomialNormalizationTest(){
        Polynomial poly  = new Polynomial(new int[]{3,1,0,0}, 7);
        Polynomial poly2 = new Polynomial(new int[]{10,-6}, 7);

        assertTrue(poly.equals(poly2));
    }
 
    @Test
    public void PolynomialSumTest(){
        Polynomial poly  = new Polynomial(new int[]{3,1,0,1}, 7);
        Polynomial poly2 = new Polynomial(new int[]{1,-6}, 7);
        Polynomial poly12 = new Polynomial(new int[]{4,2,0,1}, 7);

        assertTrue(poly.plus(poly2).equals(poly12));
    }
    
    @Test
    public void PolynomialProductTest(){
        Polynomial poly  = new Polynomial(new int[]{3,1,0,1}, 7);
        Polynomial poly2 = new Polynomial(new int[]{1,-4}, 7);
        Polynomial poly12 = new Polynomial(new int[]{3,3,3,1,3}, 7);

        assertTrue(poly.times(poly2).equals(poly12));
    }
    
    @Test 
    public void ZeroPolynomialTest(){
        Polynomial zero = new Polynomial(new int[] {0}, 2);
        Polynomial one  = new Polynomial(new int[] {1}, 2);
        
        assertTrue(one.plus(one).equals(zero));
    }
    
    @Test
    public void ModPolynomialTest(){
        
        Polynomial poly  = new Polynomial(new int[]{3,1,6,1}, 7);
        Polynomial poly2 = new Polynomial(new int[]{1,1,1}, 7);
        Polynomial poly3 = new Polynomial(new int[]{1,2}, 7);
        
        Polynomial poly4 = poly.times(poly2).plus(poly3);
        
        assertEquals(poly3, poly4.mod(poly2));
    }
    
}
