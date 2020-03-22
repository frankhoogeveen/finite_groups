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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fh.calculator.Calculator;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.GroupException;
import nl.fh.group_def_automorphism.Automorphism;
import nl.fh.group_def_automorphism.AutomorphismMultiplicator;
import nl.fh.homomorphism.HomomorphismException;

/**
 *
 * @author frank
 */
public class AutomorphismCalculator implements Calculator<Group> {

    @Override
    public Group evaluate(Group group) throws EvaluationException {
        String groupName = (String) group.getProperty(GroupProperty.Name);
        String name = "Aut(" + groupName + ")";

        AutomorphismMultiplicator mult = new AutomorphismMultiplicator();
        
        Set<Element> aut =  getAllAutomorphisms(group);
        
        try {
            return new Group(name, aut, mult);
        } catch (GroupException ex) {
            String mess = "could not determine automorphism group";
            Logger.getLogger(AutomorphismCalculator.class.getName()).log(Level.SEVERE, mess, ex);
            throw new EvaluationException(mess);
        }
    }

    private Set<Element> getAllAutomorphisms(Group group) throws EvaluationException {
       
        // get a set of generators of minimal size 
        Set<Set<Element>> smg = (Set<Set<Element>>)group.getProperty(GroupProperty.StronglyMinimalGeneratingSets);
        Set<Element> generators = smg.iterator().next();
        
        Map<Element, Set<Element>> candidatesMappedGenerators = findCandidates(generators, group);
        Set<Map<Element,Element>>  candidateMappings = createAllCombinations(candidatesMappedGenerators);
        
        Set<Element> aut = new HashSet<Element>();
        for(Map<Element, Element> map : candidateMappings){
            try {
                Automorphism a = new Automorphism(group, map);
                aut.add(a);
            } catch (HomomorphismException ex) {
                // apparently the mapped generators could not be extended to an automorphism
                // Do Nothing
            }
        }
        return aut;
    }

    /**
     * 
     * @param generators
     * @param group
     * @return  a maps that identifies for each generator what potential images 
     * under an automorphism are
     */
    private Map<Element, Set<Element>> findCandidates(Set<Element> generators, Group group) throws EvaluationException {
        Map<Element, Set<Element>> result = new HashMap<Element, Set<Element>>();
        for(Element g : generators){
            Set<Element> candidates = findCandidatesFor(g, group);            
            result.put(g, candidates);
        }
        return result;
    }

    /**
     * 
     * @param g
     * @param group
     * @return elements that are potential images of g under an automorphism
     */
    private Set<Element> findCandidatesFor(Element g, Group group) throws EvaluationException {
        Map<Element, Integer> orders = (Map<Element, Integer>) group.getProperty(GroupProperty.ElementOrders);
        Map<Element, Set<Element>> conj = (Map<Element, Set<Element>>) group.getProperty(GroupProperty.ConjugationClassesMap);
        
        Set<Element> result = new HashSet<Element>();
        for(Element h : group){
            boolean isCandidate = true;
            isCandidate &= (orders.get(g).equals(orders.get(h)));
            isCandidate &= (conj.get(g).size()==conj.get(h).size());
            if(isCandidate){
                result.add(h);
            }
        }
        return result;
    }

    /**
     * 
     * @param candidatesMappedGenerators
     * @return the set of all candidate mappings 
     */
    private Set<Map<Element, Element>> createAllCombinations(Map<Element, Set<Element>> map) {
        // create a pool in which all intermediary results live
        Set<Map<Element, Set<Element>>> pool = new HashSet<Map<Element, Set<Element>>>();
        pool.add(map);
        
        // expand each element 
        for(Element g : map.keySet()){
           Set<Map<Element, Set<Element>>> nextPool = new HashSet<Map<Element, Set<Element>>>(pool);
           for(Map<Element, Set<Element>> poolMap : pool){
               nextPool.remove(poolMap);
               for(Element target : map.get(g)){
                   Map<Element, Set<Element>> newMap = new HashMap<Element, Set<Element>>(poolMap);
                   Set<Element> targetSet = new HashSet<Element>();
                   targetSet.add(target);
                   newMap.put(g, targetSet);
                   nextPool.add(newMap);
               }
            }
           pool = nextPool;
        }
        
        //get rid of the singleton sets
        Set<Map<Element, Element>> result = new HashSet<Map<Element, Element>>();
        for(Map<Element, Set<Element>> singletonSetMap : pool){
            Map<Element, Element> singletonMap = new HashMap<Element, Element>();
            for(Element g : singletonSetMap.keySet()){
                Set<Element> singleton = singletonSetMap.get(g);
                if(singleton.size() != 1){
                    throw new IllegalStateException("Calculating automorphisms. This should not happen");
                }
                Element image = singleton.iterator().next();
                singletonMap.put(g, image);
            }
            result.add(singletonMap);
        }
        
        return result;
    }
}
