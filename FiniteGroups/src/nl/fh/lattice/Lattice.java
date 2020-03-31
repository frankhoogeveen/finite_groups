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
package nl.fh.lattice;

import java.util.List;
import java.util.Set;

/**
 *
 * This interface defines a lattice
 * https://en.wikipedia.org/wiki/Lattice_(order)
 * 
 * @author frank
 */
public interface Lattice<T> extends LatticeComparator<T> {
    
    /**
     * 
     * @param t1
     * @param t2
     * @return true if t1 <= t2, false otherwise 
     */
    public boolean belowEqual(T t1, T t2);
    
    /**
     * 
     * @param t1
     * @param t2
     * @return true if t1 < t2, false otherwise 
     */
    public boolean below(T t1, T t2);
    
    /**
     * 
     * @param t1
     * @param t2
     * @return true is t1 >= t2, false otherwise 
     */
    public boolean aboveEqual(T t1, T t2);
    
    /**
     * 
     * @param t1
     * @param t2
     * @return true is t1 > t2, false otherwise 
     */
    public boolean above(T t1, T t2);
    /**
     * 
     * @param t1
     * @param t2
     * @return true if  t1 < t2 and there is no z such that t1 < z < t2
     */
    public boolean covers(T t1, T t2);
    
    
    /**
     * 
     * @param t1
     * @param t2
     * @return the largest common lower bound 
     */
    public T meet(T t1, T t2);
    
    /**
     * 
     * @param t1
     * @param t2
     * @return the smallest common upper bound 
     */
    public T join(T t1, T t2);
    
    /**
     * 
     * @param t
     * @return all z such that cover(z, t) is true 
     */
    public Set<T> coveredSet(T t);
    
    /**
     * 
     * @param t
     * @return all z such that cover(t, z) is true 
     */
    public Set<T> coveringSet(T t);
    
    /**
     * 
     * @return the upper bound of all elements 
     */
    public T top();
    
    /**
     * 
     * @return the lower bound of all elements 
     */
    public T bottom();
    
    /**
     * 
     * @return the number of elements
     */
    public int size();
    
    /**
     * 
     * @return topologically sorted list containing all elements of this lattice
     */
    public List<T> sort();
}
