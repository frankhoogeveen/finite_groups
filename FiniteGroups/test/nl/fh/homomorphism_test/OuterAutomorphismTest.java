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
package nl.fh.homomorphism_test;

import java.util.HashMap;
import java.util.Map;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.Multiplicator;
import nl.fh.group_calculators.GroupProperty;
import nl.fh.group_def_automorphism.Automorphism;
import nl.fh.group_def_automorphism.AutomorphismMultiplicator;
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
public class OuterAutomorphismTest {
    @Test
    public void ConjugationMapTest() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group group = fac.getDihedralGroup(4);
        
        Map<Element, Map<Element,Element>> conjMap = (Map<Element, Map<Element,Element>>) group.getProperty(GroupProperty.ConjugationMap);
        Multiplicator mult = (Multiplicator) group.getProperty(GroupProperty.MultiplicationTable);
        
        // check if the conj map is a homomorphism
        for(Element g1 : group){
            for(Element g2 : group){
                Element g12 = mult.getProduct(g1, g2);
                
                Map<Element, Element> compose = composeMaps(conjMap.get(g1), conjMap.get(g2));
                assertEquals(compose, conjMap.get(g12));
            }
        }
    }
                    
    private Map<Element, Element> composeMaps(Map<Element, Element> map1, Map<Element, Element> map2) {
        Map<Element, Element> result = new HashMap<Element, Element>();
        for(Element g : map1.keySet()){
            result.put(g, map1.get(map2.get(g)));
        }
        return result;
    }
    
    @Test
    public void InnerAutomorphismMapCalculatorTest() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group group = fac.getDihedralGroup(4);
        
        Map<Element, Element> map = (Map<Element, Element>) group.getProperty(GroupProperty.InnerAutomorphismMap);
        
        for(Element g : group){
            assertTrue(map.get(g) instanceof Automorphism);
        }
        
        Multiplicator multAuto = new AutomorphismMultiplicator();
        Multiplicator multGroup = (Multiplicator) group.getProperty(GroupProperty.MultiplicationTable);
        
        for(Element g1 : group){
            for(Element g2 : group){
                Element mapAfterMult = map.get(multGroup.getProduct(g1, g2));
                Element multAfterMap = multAuto.getProduct(map.get(g1), map.get(g2));
                assertEquals(mapAfterMult, multAfterMap);
            }
        }
    }
    
    @Test
    public void InnerAutomorphismEmbeddingCalculatorTest() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group group = fac.getDihedralGroup(4);
        
        GroupHomomorphism morph = (GroupHomomorphism) group.getProperty(GroupProperty.InnerAutomorphismEmbedding);
        
        assertTrue((boolean) morph.getProperty(HomomorphismProperty.IsNormal));
    }
            
    @Test
    public void OuterIsomorphismCalculatorTest() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group group = fac.getDihedralGroup(4);
        
        Group out = (Group) group.getProperty(GroupProperty.OuterAutomorphismGroup);
        int order = (int) out.getProperty(GroupProperty.Order);
        
        assertEquals(2, order);
    }
    
    @Test
    public void S3OuterIsomorphismCalculatorTest() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group group = fac.getSymmetricGroup(3);
        
        Group out = (Group) group.getProperty(GroupProperty.OuterAutomorphismGroup);
        int order = (int) out.getProperty(GroupProperty.Order);
        
        assertEquals(1, order);
    }
    
    @Test
    public void S4OuterIsomorphismCalculatorTest() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group group = fac.getSymmetricGroup(4);
        
        Group out = (Group) group.getProperty(GroupProperty.OuterAutomorphismGroup);
        int order = (int) out.getProperty(GroupProperty.Order);
        
        assertEquals(1, order);
    }
}

