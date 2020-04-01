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
package nl.fh.lattice_test;

import java.util.HashMap;
import nl.fh.number.DivisionLatticeComparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import nl.fh.lattice.Lattice;
import nl.fh.lattice.LatticeComparator;
import nl.fh.lattice.LatticeComparison;
import nl.fh.lattice_implementations.ConcreteLattice;
import nl.fh.lattice_implementations.DualComparator;
import nl.fh.lattice_implementations.LatticeTables;
import nl.fh.lattice_implementations.MappedLattice;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class LatticeTest {
    
    @Test 
    public void IntegerComparatorTest(){
        LatticeComparator comp = new DivisionLatticeComparator();
        
        assertEquals(LatticeComparison.Equal, comp.compare(5, 5));
        assertEquals(LatticeComparison.Smaller, comp.compare(5, 15));
        assertEquals(LatticeComparison.Greater, comp.compare(60, 5));
        assertEquals(LatticeComparison.Unrelated, comp.compare(5, 7));
        
        LatticeComparator dual = new DualComparator(comp);
        assertEquals(LatticeComparison.Equal, dual.compare(5, 5));
        assertEquals(LatticeComparison.Smaller, dual.compare(30, 15));
        assertEquals(LatticeComparison.Greater, dual.compare(1, 5));
        assertEquals(LatticeComparison.Unrelated, dual.compare(5, 7));
    }
    
    @Test
    public void IntegerLatticeTest(){
        // pack all divisors of 60 in a set
        Set<Integer> set = new HashSet<Integer>();
        set.add(5);
        set.add(10);
        set.add(20);
        set.add(12);
        set.add(3);
        set.add(6);
        set.add(2);
        set.add(4);
        set.add(1);
        set.add(15);
        set.add(60);
        set.add(30);
        
        LatticeComparator<Integer> comp = new DivisionLatticeComparator();
        
        Lattice<Integer> lattice = new ConcreteLattice(set, comp);
        
        assertEquals(12, lattice.size());
        assertEquals(1, (int)lattice.bottom());
        assertEquals(60, (int)lattice.top());
        
        assertTrue(lattice.below(2, 12));
        assertTrue(lattice.belowEqual(6,6));
        assertTrue(lattice.above(15, 3));
        assertTrue(lattice.aboveEqual(6,6));
        
        assertTrue(lattice.covers(6, 12));
        assertFalse(lattice.covers(3,12));
        
        assertEquals(2, (int)lattice.meet(6, 10));
        assertEquals(1, (int)lattice.meet(3, 10));
        
        assertEquals(30, (int)lattice.join(10, 6));
        assertEquals(60, (int)lattice.join(5, 12));
        
        assertTrue(lattice.covers(4, 12));
        
        Set<Integer> expected = new HashSet<Integer>();
        expected.add(6);
        expected.add(4);
        assertEquals(expected, lattice.coveredSet(12));
        
        expected = new HashSet<Integer>();
        expected.add(12);
        expected.add(30);
        assertEquals(expected, lattice.coveringSet(6));
        
        List<Integer> list = lattice.sort();
        
        for(int i = 0; i< list.size(); i++){
            for(int j = i+1; j < list.size(); j++){
                int ni = list.get(i);
                int nj = list.get(j);
                
                LatticeComparison c = comp.compare(ni, nj);
                assertTrue(c.equals(LatticeComparison.Unrelated)||c.equals(LatticeComparison.Greater));
            }
        }
    }
    
        
    @Test
    public void IntegerTableLatticeTest(){
        // pack all divisors of 60 in a set
        Set<Integer> set = new HashSet<Integer>();
        set.add(5);
        set.add(10);
        set.add(20);
        set.add(12);
        set.add(3);
        set.add(6);
        set.add(2);
        set.add(4);
        set.add(1);
        set.add(15);
        set.add(60);
        set.add(30);
        
        LatticeComparator<Integer> comp = new DivisionLatticeComparator();
        
        Lattice<Integer> lattice = new LatticeTables(set, comp);
        
        assertEquals(12, lattice.size());
        assertEquals(1, (int) lattice.bottom());
        assertEquals(60, (int) lattice.top());
        
        assertTrue(lattice.below(2, 12));
        assertTrue(lattice.belowEqual(6,6));
        assertTrue(lattice.above(15, 3));
        assertTrue(lattice.aboveEqual(6,6));
        
        assertTrue(lattice.covers(6, 12));
        assertFalse(lattice.covers(3,12));
        
        assertEquals(2, (int)lattice.meet(6, 10));
        assertEquals(1, (int)lattice.meet(3, 10));
        
        assertEquals(30, (int)lattice.join(10, 6));
        assertEquals(60, (int)lattice.join(5, 12));
        
        assertTrue(lattice.covers(4, 12));
        
        Set<Integer> expected = new HashSet<Integer>();
        expected.add(6);
        expected.add(4);
        assertEquals(expected, lattice.coveredSet(12));
        
        expected = new HashSet<Integer>();
        expected.add(12);
        expected.add(30);
        assertEquals(expected, lattice.coveringSet(6));
        
        List<Integer> list = lattice.sort();
        
        for(int i = 0; i< list.size(); i++){
            for(int j = i+1; j < list.size(); j++){
                int ni = list.get(i);
                int nj = list.get(j);
                
                LatticeComparison c = comp.compare(ni, nj);
                assertTrue(c.equals(LatticeComparison.Unrelated)||c.equals(LatticeComparison.Greater));
            }
        }
    }

            
    @Test
    public void IntegerMappedLatticeTest(){
        // pack all divisors of 60 in a set
        Set<Integer> set = new HashSet<Integer>();
        set.add(5);
        set.add(10);
        set.add(20);
        set.add(12);
        set.add(3);
        set.add(6);
        set.add(2);
        set.add(4);
        set.add(1);
        set.add(15);
        set.add(60);
        set.add(30);
        
        LatticeComparator<Integer> comp = new DivisionLatticeComparator();
        Lattice<Integer> inner = new ConcreteLattice(set, comp);
        
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(Integer n : set){
            map.put(Integer.toString(n), n);
        }
        
        Lattice<String> lattice = new MappedLattice(inner, map);
        
        assertEquals(12, lattice.size());
        assertEquals("1", lattice.bottom());
        assertEquals("60", lattice.top());
        
        assertTrue(lattice.below("2", "12"));
        assertTrue(lattice.belowEqual("6","6"));
        assertTrue(lattice.above("15", "3"));
        assertTrue(lattice.aboveEqual("6","6"));
        
        assertTrue(lattice.covers("6", "12"));
        assertFalse(lattice.covers("3","12"));
        
        assertEquals("2", lattice.meet("6", "10"));
        assertEquals("1", lattice.meet("3", "10"));
        
        assertEquals("30", lattice.join("10", "6"));
        assertEquals("60", lattice.join("5", "12"));
        
        assertTrue(lattice.covers("4", "12"));
        
        Set<String> expected = new HashSet<String>();
        expected.add("6");
        expected.add("4");
        assertEquals(expected, lattice.coveredSet("12"));
        
        expected = new HashSet<String>();
        expected.add("12");
        expected.add("30");
        assertEquals(expected, lattice.coveringSet("6"));
        
        List<String> list = lattice.sort();
        
        for(int i = 0; i< list.size(); i++){
            for(int j = i+1; j < list.size(); j++){
                String ni = list.get(i);
                String nj = list.get(j);
                
                LatticeComparison c = lattice.compare(ni, nj);
                assertTrue(c.equals(LatticeComparison.Unrelated)||c.equals(LatticeComparison.Greater));
            }
        }
    }
}
