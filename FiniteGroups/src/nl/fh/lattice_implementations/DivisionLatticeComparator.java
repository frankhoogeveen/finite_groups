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
package nl.fh.lattice_implementations;

import nl.fh.lattice.LatticeComparator;
import nl.fh.lattice.LatticeComparison;

/**
 *
 *  Comparator on the division lattice of the integers
 * 
 *  t1 < t2 iff t1 divides t2
 * 
 * @author frank
 */
public class DivisionLatticeComparator implements LatticeComparator<Integer> {

    @Override
    public LatticeComparison compare(Integer t1, Integer t2) {
        if((t1 < 1)||(t2 < 1)){
            throw new IllegalArgumentException();
        }
        
        if(t1.equals(t2)){
            return LatticeComparison.Equal;
        }
        
        if(t1 % t2 == 0){
            return LatticeComparison.Greater;
        }
        
         if(t2 % t1 == 0){
            return LatticeComparison.Smaller;
        }
         
        return LatticeComparison.Unrelated;
    }
    
}
