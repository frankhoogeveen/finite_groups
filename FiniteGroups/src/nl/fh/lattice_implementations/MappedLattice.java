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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import nl.fh.lattice.Lattice;
import nl.fh.lattice.LatticeComparison;

/**
 * Wraps one Lattice<T> into this Lattice<S> with the use of a Map<S.T> 
 * 
 * 
 * @author frank
 */
public class MappedLattice<S,T> implements Lattice<S> {

    private final Lattice<T> innerLattice;
    private final Map<S, T> map;
    private final HashMap<T, S> inverseMap;

    /**
     * 
     * @param lattice
     * @param map an 1-1 map
     */
    public MappedLattice(Lattice<T> lattice, Map<S,T> map){
        this.innerLattice = lattice;
        this.map = map;
       
        Set<T> values = new HashSet<T>(map.values());
        if(values.size() != map.keySet().size()){
            throw new IllegalArgumentException("map for MappedLattice is not 1-1");
        }
        
        this.inverseMap = new HashMap<T,S>();
        for(S s : map.keySet()){
            inverseMap.put(map.get(s), s);
        }
    }

    @Override
    public boolean belowEqual(S s1, S s2) {
        return innerLattice.belowEqual(map.get(s1), map.get(s2));
    }

    @Override
    public boolean below(S s1, S s2) {
        return innerLattice.below(map.get(s1), map.get(s2));
    }

    @Override
    public boolean aboveEqual(S s1, S s2) {
        return innerLattice.aboveEqual(map.get(s1), map.get(s2));
    }

    @Override
    public boolean above(S s1, S s2) {
        return innerLattice.above(map.get(s1), map.get(s2));
    }

    @Override
    public boolean covers(S s1, S s2) {
        return innerLattice.covers(map.get(s1), map.get(s2));
    }

    @Override
    public S meet(S s1, S s2) {
        return inverseMap.get(innerLattice.meet(map.get(s1), map.get(s2)));
    }

    @Override
    public S join(S s1, S s2) {
        return inverseMap.get(innerLattice.join(map.get(s1), map.get(s2)));
    }

    @Override
    public Set<S> coveredSet(S s) {
        Set<S> result = new HashSet<S>();
        for(T t : innerLattice.coveredSet(map.get(s))){
            result.add(inverseMap.get(t));
        }
        return result;
    }
    
    @Override
    public Set<S> coveringSet(S s) {
        Set<S> result = new HashSet<S>();
        for(T t : innerLattice.coveringSet(map.get(s))){
            result.add(inverseMap.get(t));
        }
        return result;
    }

    @Override
    public S top() {
        return inverseMap.get(innerLattice.top());
    }

    @Override
    public S bottom() {
        return inverseMap.get(innerLattice.bottom());
    }

    @Override
    public int size() {
        return innerLattice.size();
    }

    @Override
    public List<S> sort() {
        List<S> result = new ArrayList<S>();
        for(T t : innerLattice.sort()){
            result.add(inverseMap.get(t));
        }
        return result;
    }

    @Override
    public LatticeComparison compare(S s1, S s2) {
        return innerLattice.compare(map.get(s1), map.get(s2));
    }
}
