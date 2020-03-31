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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import nl.fh.lattice.Lattice;
import nl.fh.lattice.LatticeComparator;
import nl.fh.lattice.LatticeComparison;

/**
 * Lattice implementation that pre-calculates all results and stores them in
 * tables when the constructor is called.
 * 
 * @author frank
 */
public class LatticeTables<T> implements Lattice<T> {
    Map<T, Map<T, LatticeComparison>> comparisonMap;
    Map<T, Set<T>> coveringMap;
    Map<T, Set<T>> coveredMap;
    Map<T,Map<T,T>> meetMap;
    Map<T,Map<T,T>> joinMap;    
    List<T> list;
    
    
    public LatticeTables(Set<T> set, LatticeComparator comparator){
        
        Lattice<T> concrete = new ConcreteLattice<T>(set, comparator);
        
        //pre populate the tables
        this.list = concrete.sort();
        
        initializeComparisonMap(concrete);
        initializeCoveringMaps(concrete);
        initializeMeetMap(concrete);
        initializeJoinMap(concrete);
    }
    
    @Override
    public LatticeComparison compare(T t1, T t2) {
        return this.comparisonMap.get(t1).get(t2);
    }

    @Override
    public boolean belowEqual(T t1, T t2) {
        return LatticeComparison.belowEqual(this.comparisonMap.get(t1).get(t2));
    }

    @Override
    public boolean below(T t1, T t2) {
        return LatticeComparison.below(this.comparisonMap.get(t1).get(t2));
    }

    @Override
    public boolean aboveEqual(T t1, T t2) {
        return LatticeComparison.aboveEqual(this.comparisonMap.get(t1).get(t2));
    }

    @Override
    public boolean above(T t1, T t2) {
        return LatticeComparison.above(this.comparisonMap.get(t1).get(t2));
    }

    @Override
    public boolean covers(T t1, T t2) {
        return this.coveringMap.get(t1).contains(t2);
    }

    @Override
    public T meet(T t1, T t2) {
        return this.meetMap.get(t1).get(t2);
    }

    @Override
    public T join(T t1, T t2) {
        return this.joinMap.get(t1).get(t2);
    }

    @Override
    public Set<T> coveredSet(T t) {
        return this.coveredMap.get(t);
    }

    @Override
    public Set<T> coveringSet(T t) {
        return this.coveringMap.get(t);
    }

    @Override
    public T top() {
        return this.list.get(0);
    }

    @Override
    public T bottom() {
        return this.list.get(list.size()-1);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public List<T> sort() {
        return this.list;
    }

    private void initializeComparisonMap(Lattice<T> concrete) {
        this.comparisonMap = new HashMap<T,Map<T,LatticeComparison>>();
        for(T t1 : this.list){
            this.comparisonMap.put(t1, new HashMap<T, LatticeComparison>());
            for(T t2 : this.list){
                this.comparisonMap.get(t1).put(t2, concrete.compare(t1, t2));
            }
        }
    }

    private void initializeCoveringMaps(Lattice<T> concrete) {
        this.coveringMap = new HashMap<T,Set<T>>();
        for(T t1 : this.list){
            this.coveringMap.put(t1, concrete.coveringSet(t1));
        }   
        
        this.coveredMap = new HashMap<T,Set<T>>();
        for(T t1 : this.list){
            this.coveredMap.put(t1, concrete.coveredSet(t1));
        }   
    }

    private void initializeMeetMap(Lattice<T> concrete) {
        this.meetMap = new HashMap<T,Map<T,T>>();
        for(T t1 : this.list){
            this.meetMap.put(t1, new HashMap<T, T>());
            for(T t2 : this.list){
                this.meetMap.get(t1).put(t2, concrete.meet(t1, t2));
            }
        }
    }

    private void initializeJoinMap(Lattice<T> concrete) {
        this.joinMap = new HashMap<T,Map<T,T>>();
        for(T t1 : this.list){
            this.joinMap.put(t1, new HashMap<T, T>());
            for(T t2 : this.list){
                this.joinMap.get(t1).put(t2, concrete.join(t1, t2));
            }
        }
    }
}
