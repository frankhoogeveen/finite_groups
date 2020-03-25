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
package nl.fh.homomorphism_calculator;

import nl.fh.group_def_coset.CosetMultiplicator;
import nl.fh.group_def_coset.CosetElement;
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
import nl.fh.group.Multiplicator;
import nl.fh.group_calculators.GroupProperty;
import nl.fh.homomorphism.GroupHomomorphism;
import nl.fh.homomorphism.HomomorphismException;

/**
 * Given the homomorphism H->G, this calculator determines G->G/H
 * if H is normal in G
 * 
 * @author frank
 */
public class FactorHomomorphismCalculator implements Calculator<GroupHomomorphism> {

    @Override
    public GroupHomomorphism evaluate(GroupHomomorphism morph) throws EvaluationException {
        // check that the subgroup is indeed normal
        boolean isNormal = (boolean) morph.getProperty(HomomorphismProperty.IsNormal);
        if(!isNormal){
            throw new EvaluationException("cannot calculate factorgroup if subgroup is not normal");
        }
        
        // extract the information from the (co)domain 
        Group groupG = (Group) morph.getProperty(HomomorphismProperty.Codomain);
        Multiplicator multG = (Multiplicator) groupG.getProperty(GroupProperty.MultiplicationTable);
        Map<Element, Element> invG = (Map<Element, Element>) groupG.getProperty(GroupProperty.Inverses);

        Group groupH = (Group) morph.getProperty(HomomorphismProperty.Domain);        
        Set<Element> setH = (Set<Element>) groupH.getProperty(GroupProperty.Elements);
        
        // calculate what we need to define the quotient
        Map<Element, Set<Element>> cosetMap = findElementToCosetMap(groupG, multG, invG, setH);
        Map<Element, Element> factorMap = findMapToQuotient(cosetMap, morph);
        Set<Element> cosets = new HashSet<Element>(factorMap.values());
        String nameFactor = findNameQuotient(groupG, groupH);

        //actually define the quotient
        try {
            Group factorGroup = new Group(nameFactor, cosets, new CosetMultiplicator(factorMap));
            GroupHomomorphism quotient = new GroupHomomorphism(groupG, factorGroup, factorMap);
            return quotient;
        } catch (GroupException | HomomorphismException ex) {
            String mess = "could not calculate quotient of groups";
            Logger.getLogger(FactorHomomorphismCalculator.class.getName()).log(Level.SEVERE, mess, ex);
            throw new EvaluationException(mess);
        }
    }

    /**
     * 
     * @param groupG
     * @param multG
     * @param invG
     * @param setH
     * @return a map from G to the cosets of G mod H, where g is mapped to the coset containing g
     * @throws EvaluationException 
     */
    private Map<Element, Set<Element>> findElementToCosetMap(Group groupG, Multiplicator multG, Map<Element, Element> invG, Set<Element> setH) throws EvaluationException {
        Map<Element, Set<Element>> cosetMap = new HashMap<Element, Set<Element>> ();
        for(Element g1 : groupG){
            cosetMap.put(g1, new HashSet<Element>());
            for(Element g2 : groupG){
                Element g1g2inv = multG.getProduct(g1, invG.get(g2));
                if(setH.contains(g1g2inv)){
                    cosetMap.get(g1).add(g2);
                }
            }
        }
        return cosetMap;
    }

    /**
     * 
     * @param cosetMap
     * @param morph
     * @return the cosetMap where the values are turned from sets into group elements
     */
    private Map<Element, Element> findMapToQuotient(Map<Element, Set<Element>> cosetMap, GroupHomomorphism morph) {
        Map<Element, Element> factorMap = new HashMap<Element, Element>();
        for(Set<Element> set : cosetMap.values()){
            Element coset = new CosetElement(set, morph);
            for(Element g: set){
                factorMap.put(g, coset);
            }
        }
        return factorMap;
    }

    /**
     * 
     * @param groupG
     * @param groupH
     * @return the name of the quotient group
     * @throws EvaluationException 
     */
    private String findNameQuotient(Group groupG, Group groupH) throws EvaluationException {
        String nameG = (String) groupG.getProperty(GroupProperty.Name);
        String nameH = (String) groupH.getProperty(GroupProperty.Name);
        String nameFactor = nameG + "/" + nameH;
        return nameFactor;
    }
}
