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

import java.util.Set;
import nl.fh.lattice.LatticeComparator;
import nl.fh.lattice.LatticeComparison;

/**
 * Comparator on the Lattice of Sets with the inclusion relation
 * 
 * 
 * @author frank
 */
public class SetComparator implements LatticeComparator<Set<Object>> {
    private static SetComparator instance = null;

    private SetComparator(){
        
    }
    
    public static SetComparator getInstance() {
        if(instance == null){
            instance = new SetComparator();
        }
        return instance;
    }

    @Override
    public LatticeComparison compare(Set<Object> t1, Set<Object> t2) {
        if(t1.equals(t2)){
            return LatticeComparison.Equal;
        }
        
        if(t1.containsAll(t2)){
            return LatticeComparison.Greater;
        }
        
        if(t2.containsAll(t1)){
            return LatticeComparison.Smaller;
        }
        
        return LatticeComparison.Unrelated;
    }
}
