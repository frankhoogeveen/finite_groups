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
package nl.fh.group;

import java.util.Map;
import java.util.Set;
import nl.fh.calculator.EvaluationException;

/**
 * Object to check a group against the basic definitions
 * - closed
 * - existence of a neutral element
 * - existence of inverses for every element
 * - associativity
 *
 * @author frank
 */
public class GroupChecker {
    
    public boolean isGroup(Group group) throws EvaluationException{

        try{
            checkClosed(group);
            checkNeutralElement(group);
            checkInverses(group);
            checkAssociativity(group);
        } catch(EvaluationException e){
            return false;
        }
        return true;
    }

    private void checkClosed(Group group) throws EvaluationException {
        Map<Element, Map<Element,Element>> table = (Map<Element, Map<Element,Element>> )group.getProperty(GroupProperty.MultiplicationTable);
        Set<Element> set = (Set<Element>)group.getProperty(GroupProperty.Elements);
        
        for(Element g : set){
            for(Element h : set){
                if(!set.contains(table.get(g).get(h))){
                    throw new EvaluationException("Table not closed");
                }
            }
        }
    }

    private void checkNeutralElement(Group group) throws EvaluationException{
        Map<Element, Map<Element,Element>> table = (Map<Element, Map<Element,Element>> )group.getProperty(GroupProperty.MultiplicationTable);
        Set<Element> set = (Set<Element>)group.getProperty(GroupProperty.Elements);
        Element u = (Element)group.getProperty(GroupProperty.UnitElement);
        
        for(Element g : set){
            if((!table.get(g).get(u).equals(g)) || (!table.get(u).get(g).equals(g))){
                throw new EvaluationException("Incorrect unit");
            }
        }
    }

    private void checkInverses(Group group) throws EvaluationException {
        Set<Element> set = (Set<Element>)group.getProperty(GroupProperty.Elements);
        for(Element g : set){
            checkHasInverse(g, group);
        }
    }
    
    private void checkHasInverse(Element g, Group group) throws EvaluationException {
        Map<Element, Map<Element,Element>> table = (Map<Element, Map<Element,Element>> )group.getProperty(GroupProperty.MultiplicationTable);
        Set<Element> set = (Set<Element>)group.getProperty(GroupProperty.Elements);
        Element u = (Element)group.getProperty(GroupProperty.UnitElement);
        
        for(Element h : set){
            if(!table.get(g).get(h).equals(u) && table.get(h).get(g).equals(u)){
                return;
            }
        }
        throw new EvaluationException("Inverses do not exist for every element ");
    }

    private void checkAssociativity(Group group) throws EvaluationException {
        Map<Element, Map<Element,Element>> table = (Map<Element, Map<Element,Element>> )group.getProperty(GroupProperty.MultiplicationTable);
        Set<Element> set = (Set<Element>)group.getProperty(GroupProperty.Elements);
        
        for(Element g1 : set){
            for(Element g2 : set){
                for(Element g3 : set){
                    Element g12_3 = table.get(table.get(g1).get(g2)).get(g3);
                    Element g1_23 = table.get(g1).get(table.get(g2).get(g3));
                    if(!g12_3.equals(g1_23)){
                         throw new EvaluationException("Not associative");
                    }
                }
            }
        }
    }
}
