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
package nl.fh.numbers_test;

import nl.fh.number.Prime;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class PrimesTest {
    
    @Test
    public void SieveTest(){
        assertTrue(Prime.isPrime(7));
        assertTrue(Prime.isPrime(2));
        assertTrue(Prime.isPrime(23));
        
        assertFalse(Prime.isPrime(4));
        assertFalse(Prime.isPrime(7*7));
        assertFalse(Prime.isPrime(1));
        assertFalse(Prime.isPrime(0));
        assertFalse(Prime.isPrime(-7));
    }
    @Test
    public void PrimeAfterTest(){
        assertEquals(2, Prime.after(0));
        assertEquals(2, Prime.after(1));
        assertEquals(3, Prime.after(2));
        assertEquals(19, Prime.after(17));       
    }
}
