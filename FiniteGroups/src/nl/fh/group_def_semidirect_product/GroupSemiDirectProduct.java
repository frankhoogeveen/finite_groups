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
package nl.fh.group_def_semidirect_product;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.GroupException;
import nl.fh.group.Multiplicator;
import nl.fh.group_calculators.GroupProperty;
import nl.fh.homomorphism.GroupHomomorphism;
import nl.fh.homomorphism_calculator.HomomorphismProperty;

/**
 * 
 * 
 * 
 * @author frank
 */
public class GroupSemiDirectProduct{

    private static String LEFT_NORMAL_SEMIDIRECT_PRODUCT = "\u22c9";
    
    /**
     * Returns the semi direct product of N and H under phi
     * 
     * (n1, h1) (n2, h2) -> (n1.phi(h1)(n2), h1.h2)
     * 
     * 
     * @param NinG  the embedding of the normal factor
     * @param HinG  the embedding of the quotient
     * @param HinAutN the embedding of the quotient in the automorphisms of N
     * @return
     */
    public static Group of(GroupHomomorphism NinG, GroupHomomorphism HinG, GroupHomomorphism HinAutN) throws EvaluationException, GroupException{
        
        Group G = (Group) NinG.getProperty(HomomorphismProperty.Codomain);
        Group G1 = (Group) HinG.getProperty(HomomorphismProperty.Codomain);
        
        if(!G.equals(G1)){
            throw new IllegalArgumentException("the group need to be the same for both factors of a semidirect product");
        }
        
        Group N = (Group) NinG.getProperty(HomomorphismProperty.Domain);
        Group H = (Group) HinG.getProperty(HomomorphismProperty.Domain);

        Set<Element> elements = createElements(N, H);
        String name = composeName(N, H);
        Multiplicator mult = new SemiDirectProductMultiplicator(G, HinAutN);
        
        return new Group(name, elements, mult);
    }

    private static String composeName(Group N, Group H) throws EvaluationException {
        String nameN = (String) N.getProperty(GroupProperty.Name);
        String nameH = (String) H.getProperty(GroupProperty.Name);
        String name = nameN + LEFT_NORMAL_SEMIDIRECT_PRODUCT + nameH;
        return name;
    }

    private static Set<Element> createElements(Group N, Group H) throws EvaluationException {
        Set<Element> nSet = (Set<Element>) N.getProperty(GroupProperty.Elements);
        Set<Element> hSet = (Set<Element>) H.getProperty(GroupProperty.Elements);
        Set<Element> elements = new HashSet<Element>();
        for(Element n : nSet){
            for(Element h : hSet){
                elements.add(new SemiDirectProductElement(n,h));
            }
        }
        return elements;
    }
    
}
