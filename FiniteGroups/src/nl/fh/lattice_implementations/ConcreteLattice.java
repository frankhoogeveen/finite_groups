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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import nl.fh.lattice.Lattice;
import nl.fh.lattice.LatticeComparator;
import nl.fh.lattice.LatticeComparison;

/**
 * Implementation of the Lattice Interface. 
 * This implementation is not optimized for time, neither for space efficiency.
 * 
 * @author frank
 * @param <T>
 */
public class ConcreteLattice<T> implements Lattice<T> {

    private final Set<T> set;    
    private final LatticeComparator comparator;
    private final DualComparator dualComparator;

    
    /**
     * 
     * @param set elements of the lattice
     * @param comparator implements the partial ordering
     * 
     * If this is not a Lattice, i.e. if meets and/or joins do not uniquely exist,
     * a IllegalArgumentException is thrown.
     * 
     * In this implementation no results of previous invocations are stored.
     * 
     * For none of the methods, the check that the arguments are indeed
     * elements of the set is made. 
     */
    public ConcreteLattice(Set<T> set, LatticeComparator comparator){
        this.set = set;
        
        this.comparator = comparator;
        this.dualComparator = new DualComparator(comparator);
    }
    
    @Override
    public LatticeComparison compare(T t1, T t2) {
        return this.comparator.compare(t1, t2);
    }
    
    @Override
    public boolean belowEqual(T t1, T t2) {
        return LatticeComparison.belowEqual(this.comparator.compare(t1, t2));
    }

    @Override
    public boolean below(T t1, T t2) {
        return LatticeComparison.below(this.comparator.compare(t1, t2));
    }

    @Override
    public boolean aboveEqual(T t1, T t2) {
        return LatticeComparison.aboveEqual(this.comparator.compare(t1, t2));
    }

    @Override
    public boolean above(T t1, T t2) {
        return LatticeComparison.aboveEqual(this.comparator.compare(t1, t2));
    }

    @Override
    public boolean covers(T t1, T t2) {
        if(this.comparator.compare(t1, t2) != LatticeComparison.Smaller){
            return false;
        }
        
        // check that there is nothing inbetween
        for(T tz : this.set){
            boolean between = (this.comparator.compare(t1,tz) == LatticeComparison.Smaller);
            between &= (this.comparator.compare(tz,t2) == LatticeComparison.Smaller);
            if(between){
                return false;
            }
        }
        
        return true;
    }

    @Override
    public T meet(T t1, T t2) {
        Set<T> lower = findCommonLowerBounds(t1, t2);
        return findUniqueLargest(lower);
    }

    @Override
    public T join(T t1, T t2) {
        Set<T> upper = findCommonUpperBounds(t1, t2);
        return findUniqueSmallest(upper);
    }

    @Override
    public Set<T> coveredSet(T t) {
        Set<T> result = new HashSet<T>();
        for(T z : this.set){
            if(covers(z,t)){
                result.add(z);
            }
        }
        return result;
    }

    @Override
    public Set<T> coveringSet(T t) {
        Set<T> result = new HashSet<T>();
        for(T z : this.set){
            if(covers(t,z)){
                result.add(z);
            }
        }
        return result;
    }

    @Override
    public T top() {
        return findUniqueLargest(this.set);
    }

    @Override
    public T bottom() {
        return findUniqueSmallest(this.set);
    }

    @Override
    public int size() {
        return this.set.size();
    }

    @Override
    public List<T> sort() {
        List<T> result = new ArrayList<T>();
        simpleSort(result, this.comparator);
        return result;
    }
    
    /**
     * 
     * @param set
     * @return the unique largest of a set.
     * Throws an IllegalArgumentException if the largest is not unique
     */
    private T findUniqueLargest(Set<T> set){
        return findUniqueLargest(set, this.comparator);
    }
    
    /**
     * 
     * @param set
     * @return the unique smallest of a set.
     * Throws an IllegalArgumentException if the smallest is not unique
     */
    private T findUniqueSmallest(Set<T> set){
        return findUniqueLargest(set, this.dualComparator);
    }
    
    /**
     * find the largest element of a set and check that this is unique
     * based on a given comparator
     */
    private T findUniqueLargest(Set<T> set, LatticeComparator comparator){
        if(set.isEmpty()){
            throw new IllegalArgumentException("set cannot be empty");
        }
        
        // find a local maximum
        Iterator<T> iterator = set.iterator();
        T currentMax = iterator.next();
        
        while(iterator.hasNext()){
            T current = iterator.next();
            if(comparator.compare(current, currentMax) == LatticeComparison.Greater){
                currentMax = current;
            }
        }
        
        // check for uniqueness
        for(T element : set){
            if(!element.equals(currentMax)){
                if(comparator.compare(element, currentMax) != LatticeComparison.Smaller){
                     throw new IllegalArgumentException("Partial Order does not define lattice"); 
                }
            }
        }
        
        return currentMax;
    }

    private Set<T> findCommonLowerBounds(T t1, T t2) {
        return findCommonUpperBounds(t1, t2, this.dualComparator);
    }

    private Set<T> findCommonUpperBounds(T t1, T t2) {
        return findCommonUpperBounds(t1, t2, this.comparator);
    }

    private Set<T> findCommonUpperBounds(T t1, T t2, LatticeComparator comparator) {
        Set<T> result = new HashSet<T>();
        for(T tz : set){
            boolean cond1 = comparator.compare(tz, t1) == LatticeComparison.Equal;
            cond1 |= comparator.compare(tz, t1) == LatticeComparison.Greater;
            
            boolean cond2 = comparator.compare(tz, t2) == LatticeComparison.Equal;
            cond2 |= comparator.compare(tz, t2) == LatticeComparison.Greater;
            
            if(cond1 && cond2){
                result.add(tz);
            }
        }
        
        return result;
    }

    /**
     * 
     * This method implements a simple sort algorithm that is not very efficient. 
     * 
     * @param list   List to be sorted
     * @param comparator 
     */
    private void simpleSort(List<T> list, LatticeComparator comparator) {
        Set<T> todo = new HashSet<T>(this.set);
        
        while(!todo.isEmpty()){
            for(Iterator<T> iter = todo.iterator(); iter.hasNext();){
                T t = iter.next();
                boolean canBeAdded = true;
                    for(T t2 : todo){
                        boolean isAbove = (comparator.compare(t, t2) == LatticeComparison.Smaller);
                        boolean isNotInList = !list.contains(t2);
                        boolean reasonToSkip = isNotInList && isAbove;
                        canBeAdded &= !reasonToSkip;
                    }
                    if(canBeAdded){
                        list.add(t);
                        iter.remove();
                    }
            }
        }
    }
}
