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
package nl.fh.subgroups_test;

import java.util.Map;
import nl.fh.calculator.Calculator;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.Multiplicator;
import nl.fh.group_calculators.CommutatorsMapCalculator;
import nl.fh.group_calculators.ConjugationMapCalculator;
import nl.fh.group_calculators.GroupProperty;
import nl.fh.group_definition_factory.GroupFactory;
import nl.fh.homomorphism.GroupHomomorphism;
import nl.fh.homomorphism_calculator.HomomorphismProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class DerivedGroupTest {
    
    /*
    * test the derived subgroup [G,G] for 
    * A5 perfect group, the derived subgroup is the entire group
    * S4 -> A4
    * Q -> C2
    * an abelean group    
    */
    
    @Test
    public void DerivedA5Test() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group a5 = fac.getAlternatingGroup(5);
       
        GroupHomomorphism der = (GroupHomomorphism) a5.getProperty(GroupProperty.CommutatorsEmbedding);
        Group derGroup = (Group) der.getProperty(HomomorphismProperty.Domain);
        int order = (int) derGroup.getProperty(GroupProperty.Order);
        
        assertTrue((boolean) der.getProperty(HomomorphismProperty.IsEmbedding));
        assertEquals(60, order);
    }
    
    @Test
    public void DerivedS4Test() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group s4 = fac.getSymmetricGroup(4);
       
        GroupHomomorphism der = (GroupHomomorphism) s4.getProperty(GroupProperty.CommutatorsEmbedding);
        Group derGroup = (Group) der.getProperty(HomomorphismProperty.Domain);
        int order = (int) derGroup.getProperty(GroupProperty.Order);
        
        assertTrue((boolean) der.getProperty(HomomorphismProperty.IsEmbedding));
        assertEquals(12, order);
    }
    
    @Test
    public void DerivedQTest() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group q = fac.getDicyclicGroup(2);
       
        GroupHomomorphism der = (GroupHomomorphism) q.getProperty(GroupProperty.CommutatorsEmbedding);
        Group derGroup = (Group) der.getProperty(HomomorphismProperty.Domain);
        int order = (int) derGroup.getProperty(GroupProperty.Order);
        
        assertTrue((boolean) der.getProperty(HomomorphismProperty.IsEmbedding));
        assertEquals(2, order);
    }
    
    @Test
    public void DerivedAbeleanTest() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group q = fac.getAbeleanGroup(new int[]{3,7});
       
        GroupHomomorphism der = (GroupHomomorphism) q.getProperty(GroupProperty.CommutatorsEmbedding);
        Group derGroup = (Group) der.getProperty(HomomorphismProperty.Domain);
        int order = (int) derGroup.getProperty(GroupProperty.Order);
        
        assertTrue((boolean) der.getProperty(HomomorphismProperty.IsEmbedding));
        assertEquals(1, order);
    }
    
    @Test
    public void CommutatorMapTest() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group group = fac.getDicyclicGroup(2);
       
        Multiplicator table = (Multiplicator) group.getProperty(GroupProperty.MultiplicationTable);
        Map<Element, Element> inv = (Map<Element,Element>) group.getProperty(GroupProperty.Inverses);
        
        Calculator calc = new CommutatorsMapCalculator();
        Map<Element, Map<Element, Element>> map = (Map<Element, Map<Element, Element>>) calc.evaluate(group);               
        
        for(Element g : group){
            for(Element h : group){
                Element gh = table.getProduct(g,h);
                Element hg = table.getProduct(h, g);
                Element hg_inv = inv.get(hg);
                Element comm = table.getProduct(gh, hg_inv);
                
                assertTrue(comm.equals(map.get(g).get(h)));
            }
        }
    }
    
    @Test
    public void ConjugationMapTest() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group group = fac.getDicyclicGroup(2);
       
        Multiplicator table = (Multiplicator) group.getProperty(GroupProperty.MultiplicationTable);
        Map<Element, Element> inv = (Map<Element,Element>) group.getProperty(GroupProperty.Inverses);
        
        Calculator calc = new ConjugationMapCalculator();
        Map<Element, Map<Element, Element>> map = (Map<Element, Map<Element, Element>>) calc.evaluate(group);               
        
        for(Element g : group){
            for(Element h : group){
                Element gh = table.getProduct(g, h);
                Element g_inv = inv.get(g);
                Element conj = table.getProduct(gh, g_inv);
                
                assertTrue(conj.equals(map.get(g).get(h)));
            }
        }
    }
}
