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
package nl.fh.value_test;

import java.util.Arrays;
import nl.fh.info_table.Value;
import nl.fh.info_table_values.SubsetValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class SubsetValueTest {
    
    @Test
    public void BooleanArrayTest(){
        boolean[] a = new boolean[4];
        a[0] = false;
        a[1] = true;
        a[2] = true;
        a[3] = false;
         
        boolean[] b = a.clone();
        
        // the hashcode of an array does not only depend on its contents
        assertFalse(a.hashCode() == b.hashCode());
        
        // but Arrays.hashCode does!
        assertTrue(Arrays.hashCode(a) == Arrays.hashCode(b));
        
        // same is true for equals
        assertFalse(a.equals(b));
        assertTrue(Arrays.equals(a, b));
        
    }
    
        @Test
    public void SubsetValueTest(){
        boolean[] a = new boolean[4];
        a[0] = false;
        a[1] = true;
        a[2] = true;
        a[3] = false;
         
        boolean[] b = a.clone();
        
        Value v1 = new SubsetValue(a);
        Value v2 = new SubsetValue(a);
        Value v3 = new SubsetValue(b);
        
        // equality should only depend on the values inside, not on memory location
        assertFalse(v1 == v2);
        assertFalse(v1 == v3);
        assertFalse(v2 == v3);

        assertEquals(v1.hashCode(), v2.hashCode());
        assertEquals(v1.hashCode(), v3.hashCode());
        assertEquals(v2.hashCode(), v3.hashCode());
        
        assertTrue(v1.equals(v2));
        assertTrue(v1.equals(v3));
        assertTrue(v2.equals(v1));
        assertTrue(v2.equals(v3));
        assertTrue(v3.equals(v1));
        assertTrue(v3.equals(v2));
    }
}
