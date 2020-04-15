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
import nl.fh.group.Multiplicator;
import nl.fh.group_def_automorphism.Automorphism;
import nl.fh.group_def_automorphism.AutomorphismMultiplicator;
import nl.fh.group_def_cyclic.CyclicElement;
import nl.fh.group_def_semidirect_product.GroupSemiDirectProduct;
import nl.fh.factory.GroupFactory;
import nl.fh.homomorphism.GroupHomomorphism;
import nl.fh.homomorphism.HomomorphismException;

/**
 *
 * 
 * 
 * 
 * 
 * @author frank
 */
public class DihedralCalculator implements Calculator<Group> {

    /**
     * 
     * @param group has to be abelean
     * @return Dih(group) , the dihedral of group 
     * @throws EvaluationException
     * @throws HomomorphismException
     * @throws GroupException 
     * 
     * The result is the semi direct product of c3 with group, where c2 acts by 
     * mapping the unit element to the identity map on group and the element of
     * order 2 to the map that sends every element of group to its inverse.
     */
    @Override
    public Object evaluate(Group group) throws EvaluationException {
               
        boolean isAbelean = (boolean) group.getProperty(GroupProperty.IsAbelean);
        if(!isAbelean){
            throw new EvaluationException("cannot calculate Dih() of non abelean group");
        }
        
        try{
        // construct the cyclic group with two elements
        GroupFactory fac = new GroupFactory();
        Group c2 = fac.getCyclicGroup(2);  // H
        
        Element unit2 = CyclicElement.create(0, 2);
        Element gen2 = CyclicElement.create(1, 2);
        
        // construct the automorphisms
        Map<Element, Element> id  = identityMap(group);
        Map<Element, Element> inv = (Map<Element, Element>) group.getProperty(GroupProperty.Inverses);
        
        Automorphism phi0 = new Automorphism(group, id);
        Automorphism phi1 = new Automorphism(group, inv);
        
        Set<Element> phiSet = new HashSet<Element>();
        phiSet.add(phi0);
        phiSet.add(phi1);
        
        Multiplicator mult = new AutomorphismMultiplicator();
        Group aut = new Group("aut", phiSet, mult);
        
        // compose the map C2 -> Aut(G)
        Map<Element, Element> phi = new HashMap<Element, Element>();
        phi.put(unit2, phi0);
        phi.put(gen2, phi1);
        
        // make the homomorphism C2 -> Aut(G)
        GroupHomomorphism morph = new GroupHomomorphism(c2, aut, phi);
        
        // put together the semidirect product
        Group semi = GroupSemiDirectProduct.of(c2, group, morph);
        
        return semi;
        } catch (HomomorphismException|GroupException ex) {
            String mess = "could not calculate dihedral of group";
            Logger.getLogger(DihedralCalculator.class.getName()).log(Level.SEVERE, mess, ex);
            throw new EvaluationException(mess);
        } 
    }

    /**
     * 
     * @param group
     * @return  the identity map on the group
     */
    private static Map<Element, Element> identityMap(Group group) {
        Map<Element, Element> result = new HashMap<Element, Element>();
        for(Element g : group){
            result.put(g,g);
        }
        return result;
    }
}
