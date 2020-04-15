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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class PolynomialTest {
    
    @Test
    public void PolynomialNormalizationTest(){
        
        Polynomial poly  = new Polynomial(new int[]{3,1,0,0});
        Polynomial poly2 = new Polynomial(new int[]{10,-6});

        assertTrue(poly.equals(poly2.reduceModulo(7)));
    }
 
    @Test
    public void PolynomialSumTest(){
        Polynomial poly  = new Polynomial(new int[]{3,1,0,1});
        Polynomial poly2 = new Polynomial(new int[]{1,-6});
        Polynomial poly12 = new Polynomial(new int[]{4,2,0,1});

        Polynomial sum = poly.plus(poly2);
        sum = sum.reduceModulo(7);
       
        assertTrue(sum.equals(poly12));
        
        Polynomial diff = poly12.minus(poly2);
        diff = diff.reduceModulo(7);
        
        assertTrue(diff.equals(poly));
    }
    
    @Test
    public void PolynomialProductTest(){
        Polynomial poly  = new Polynomial(new int[]{3,1,0,1});
        Polynomial poly2 = new Polynomial(new int[]{1,-4});
        Polynomial poly12 = new Polynomial(new int[]{3,3,3,1,3});

        Polynomial prod = poly.times(poly2);
        prod = prod.reduceModulo(7);
        
        assertTrue(prod.equals(poly12));
    }
    
    @Test 
    public void ZeroPolynomialTest(){
        Polynomial zero = new Polynomial(new int[] {0});
        Polynomial one  = new Polynomial(new int[] {1});
        
        assertTrue(one.plus(one).reduceModulo(2).equals(zero));
        assertTrue(zero.isZero());
    }
    
    @Test
    public void ModPolynomialTest(){
        
        Polynomial poly  = new Polynomial(new int[]{3,1,6,1});
        Polynomial poly2 = new Polynomial(new int[]{1,1,1});
        Polynomial poly3 = new Polynomial(new int[]{1,2});
        
        Polynomial poly4 = poly.times(poly2).plus(poly3);
        
        assertEquals(poly3, poly4.reduceModulo(poly2,7));
    }
    
    @Test
    public void ModPolynomialFieldTest(){
        FieldFactory fac = new FieldFactory();
        Field field = fac.getField(9);
        
        Polynomial poly  = new Polynomial(new int[]{2,4});
        Polynomial poly2 = new Polynomial(new int[]{2,1});
        
        assertEquals(poly2, poly.reduceModulo(field));
    }
    
    @Test
    public void QuotientModuloTest(){
        
        Polynomial poly  = new Polynomial(new int[]{3,1,6,5});
        Polynomial poly2 = new Polynomial(new int[]{1,1,1});
        Polynomial poly3 = new Polynomial(new int[]{1,2});
        
        Polynomial poly4 = poly.times(poly2).plus(poly3).reduceModulo(7);
        
        assertEquals(poly, poly4.quotientModulo(poly2,7));
    }
    
    
    @Test
    public void IrreduciblePolynomialsTest(){
        
        Polynomial poly = new Polynomial(new int[]{1,1,1});
        assertTrue(poly.isIrreducible(2));
        
        poly = new Polynomial(new int[]{1,0,1});
        assertFalse(poly.isIrreducible(2));
        
        poly = new Polynomial(new int[]{1,0,1});
        assertTrue(poly.isIrreducible(3));
        
        poly = new Polynomial(new int[]{1,2,0,1});
        assertTrue(poly.isIrreducible(3));          
        
        // polynomials of low degree(zero or one) are by definition irreducible
        poly = new Polynomial(new int[]{1,1});
        assertTrue(poly.isIrreducible(3));
        
        poly = new Polynomial(new int[]{1});
        assertTrue(poly.isIrreducible(3));        
        
    }
    

    
}
