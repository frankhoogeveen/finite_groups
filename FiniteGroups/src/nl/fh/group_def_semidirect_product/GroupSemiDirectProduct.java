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

import java.util.HashSet;
import java.util.Set;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.GroupException;
import nl.fh.group.Multiplicator;
import nl.fh.group_calculators.GroupProperty;
import nl.fh.homomorphism.GroupHomomorphism;

/**
 * 
 * 
 * 
 * @author frank
 */
public class GroupSemiDirectProduct{

    private static String RIGHT_NORMAL_SEMIDIRECT_PRODUCT = "\u22ca";
    
    /**
     * Returns the semi direct product of N and H under phi
     * 
     * (n1, h1) (n2, h2) -> (n1.phi(h1)(n2), h1.h2)
     * 
     * 
     * @param N   the normal factor
     * @param H   the quotient
     * @param HinAutN the embedding of the quotient in the automorphisms of N
     * @return
     */
    public static Group of(Group H, Group N, GroupHomomorphism HinAutN) throws EvaluationException, GroupException{

        Set<Element> elements = createElements(H, N);
        String name = composeName(H, N);
        Multiplicator mult = new SemiDirectProductMultiplicator(N, H, HinAutN);
        
        return new Group(name, elements, mult);
    }

    private static String composeName(Group H, Group N) throws EvaluationException {
        String nameN = (String) N.getProperty(GroupProperty.Name);
        String nameH = (String) H.getProperty(GroupProperty.Name);
        String name = nameH + RIGHT_NORMAL_SEMIDIRECT_PRODUCT + nameN;
        return name;
    }

    private static Set<Element> createElements(Group H, Group N) throws EvaluationException {
        Set<Element> nSet = (Set<Element>) N.getProperty(GroupProperty.Elements);
        Set<Element> hSet = (Set<Element>) H.getProperty(GroupProperty.Elements);
        Set<Element> elements = new HashSet<Element>();
        for(Element n : nSet){
            for(Element h : hSet){
                elements.add(new SemiDirectProductElement(h,n));
            }
        }
        return elements;
    }
    
}
