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
package nl.fh.number;

import java.util.Map;
import nl.fh.number.IntNumber;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class PrimeFactorsTest {
    
    @Test
    public void LcmTest(){
        assertEquals(2*2*3*3*5,IntNumber.lcm(2*2*3, 2*3*3*5));
        assertEquals(2*2*3,IntNumber.lcm(2*2*3, 1));
    }
    
    @Test
    public void GcdTest(){
        assertEquals(2*3,IntNumber.gcd(2*2*3, 2*3*3*5));
        assertEquals(1,IntNumber.gcd(2*2*3, 1));
    }
    
    @Test
    public void FactorizeTest(){
        int n = 2 * 2 * 3 * 191;
        Map<Integer, Integer> factorization = IntNumber.factorize(n);
        
        assertEquals(3, factorization.keySet().size());
        assertEquals((int) 2, (int)factorization.get(2));
        assertEquals((int)1, (int)factorization.get(3));
        assertEquals((int)1, (int)factorization.get(191));
    }
}
