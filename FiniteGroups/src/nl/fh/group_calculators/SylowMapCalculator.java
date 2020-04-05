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
package nl.fh.group_calculators;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nl.fh.calculator.Calculator;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Group;
import nl.fh.homomorphism.GroupHomomorphism;
import nl.fh.homomorphism_calculator.HomomorphismProperty;
import nl.fh.lattice.Lattice;
import nl.fh.number.IntNumber;

/**
 *
 * @author frank
 */
public class SylowMapCalculator implements Calculator<Group> {

    public SylowMapCalculator() {
    }

    @Override
    public Map<Integer, Integer> evaluate(Group group) throws EvaluationException {
        int order = (int) group.getProperty(GroupProperty.Order);
        
        Map<Integer, Integer> factors = IntNumber.factorize(order);
        Map<Integer, Integer> result = new HashMap<Integer, Integer>();
        for(Integer p : factors.keySet()){
            result.put(p, 0);
        }
        
        Lattice<GroupHomomorphism> lattice = (Lattice<GroupHomomorphism>) group.getProperty(GroupProperty.SubgroupEmbeddingLattice);
        List<GroupHomomorphism> list = lattice.sort();
        for(GroupHomomorphism morph : list){
            boolean isSylow = (boolean) morph.getProperty(HomomorphismProperty.IsSylow);
            if(isSylow){
                int p = determinePrime(morph);
                
                int count = result.get(p);
                result.put(p, count+1);
            }
        }
        
        return result;
    }

    private int determinePrime(GroupHomomorphism morph) throws EvaluationException {
        Group domain = (Group) morph.getProperty(HomomorphismProperty.Domain);
        int order = (int) domain.getProperty(GroupProperty.Order);
        
        Map<Integer, Integer> map = IntNumber.factorize(order);
        
        if(map.keySet().size() != 1){
            throw new EvaluationException("this should not happen: incorrect sylow group");
        }
        
        return map.keySet().iterator().next();
        
    }
    
}
