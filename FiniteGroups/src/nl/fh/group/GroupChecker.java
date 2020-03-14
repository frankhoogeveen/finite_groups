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
import nl.fh.group_formatter.GroupFormatter;
import nl.fh.homomorphism.GroupMorphism;

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
    private boolean verbose = true;
    
    public GroupChecker(){
        this.verbose = false;
    }
    
    /**
     * 
     * @param verbose If true, this checker will be verbose, when a test fails
     */
    public GroupChecker(boolean verbose){
        this.verbose = verbose;
    }
    
    /**
     * 
     * @param group the object to be tested
     * @return true if group is well defined:
     * - closed
     * - existence of neutral element
     * - existence of inverses for each element
     * - associativity
     * @throws EvaluationException if the group operation is not defined
     */
    public boolean isGroup(Group group) throws EvaluationException{

        try{
            checkClosed(group);
            checkNeutralElement(group);
            checkInverses(group);
            checkAssociativity(group);
        } catch(EvaluationException e){
            if(verbose){
                System.out.println("+++ Checking group failed +++");
                System.out.println(e.getMessage());
                System.out.println((new GroupFormatter()).createReport(group));
            }
            return false;
        }
        return true;
    }

    /**
     * 
     * @param morphism
     * @return true if the morphism is a homomorphism 
     */
    public boolean isHomomorphism(GroupMorphism morphism) throws EvaluationException{
        Group domain = morphism.getDomain();
        Multiplicator mDomain = (Multiplicator) domain.getProperty(GroupProperty.MultiplicationTable);
        
        Group codomain = morphism.getCodomain();
        Multiplicator mCodomain = (Multiplicator) codomain.getProperty(GroupProperty.MultiplicationTable);
        
        for(Element g1 : domain){
            for(Element g2 : domain){
                Element x = morphism.applyTo(mDomain.getProduct(g1, g2));
                Element y = mCodomain.getProduct(morphism.applyTo(g1), morphism.applyTo(g2));
                if(!x.equals(y)){
                    return false;
                }
            }
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
            if(table.get(g).get(h).equals(u) && table.get(h).get(g).equals(u)){
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
