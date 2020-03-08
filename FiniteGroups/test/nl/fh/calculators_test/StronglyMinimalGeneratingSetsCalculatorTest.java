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
package nl.fh.calculators_test;

import java.util.Set;
import nl.fh.group.Group;
import nl.fh.group_def_product.GroupProduct;
import nl.fh.group_definition_factory.GroupFactory;
import nl.fh.group.GroupProperty;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
import nl.fh.group.GroupException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class StronglyMinimalGeneratingSetsCalculatorTest {
    private boolean verbose = false;
    
    /**
     *
     * @throws EvaluationException
     */
    @Test
    public void S3Test() throws EvaluationException, GroupException{
        GroupFactory fac = new GroupFactory();
        Group s3 = fac.getSymmetricGroup(3);
        
        Set<Set<Element>> gen = (Set<Set<Element>>)(s3.getProperty(GroupProperty.StronglyMinimalGeneratingSets));
        Element unit = (Element)(s3.getProperty(GroupProperty.UnitElement));

        // s4 has six elements, thus 6 over 2 = 15 pairs
        // the unit combined with anything else does not generate the entire group (-5)
        // the two elements of order three combined do not generate the group (-1)
        // leaves 15-5-1 = 9 pairs of generating elements
        assertEquals(9, gen.size());
        for(Set<Element> set : gen){
            assertEquals(2, set.size());
            
            // the unit element should not be in a set of generators
            assertTrue(!set.contains(unit));
        }
    }
    
    
    
    @Test
    public void S4Test() throws EvaluationException, GroupException{
        GroupFactory fac = new GroupFactory();
        Group s4 = fac.getSymmetricGroup(4);
        
        Set<Set<Element>> gen = (Set<Set<Element>>)(s4.getProperty(GroupProperty.StronglyMinimalGeneratingSets));
        Element unit = (Element)(s4.getProperty(GroupProperty.UnitElement));

        assertEquals(108, gen.size());
        for(Set<Element> set : gen){
            assertEquals(2, set.size());
            assertTrue(!set.contains(unit));
        }
    }
    
    @Test
    public void C6Test() throws EvaluationException, GroupException{
        GroupFactory fac = new GroupFactory();
        Group c6 = fac.getCyclicGroup(6);
        
        /*
        <x>, <x^2, x^3>, <x^4, x^3> <x^5> are the four minimal sets of generators
        but <x> and <x^5> are the two strongly minimal sets
        */
        
        Set<Set<Element>> gen = (Set<Set<Element>>)(c6.getProperty(GroupProperty.StronglyMinimalGeneratingSets));
        Element unit = (Element)(c6.getProperty(GroupProperty.UnitElement));      
        
        assertEquals(2, gen.size());
        for(Set<Element> set : gen){
            assertEquals(1, set.size());
            assertTrue(!set.contains(unit));
        }
      
    }
    
    @Test
    public void D3D4Test() throws EvaluationException, GroupException{
        GroupFactory fac = new GroupFactory();

        Group d3 = fac.getDihedralGroup(3);
        Group d4 = fac.getDihedralGroup(4);
        
        
        Set<Set<Element>> content3 = ((Set<Set<Element>>)(d3.getProperty(GroupProperty.StronglyMinimalGeneratingSets)));
        Set<Set<Element>> content4 = ((Set<Set<Element>>)(d4.getProperty(GroupProperty.StronglyMinimalGeneratingSets)));
        
        assertEquals(9, content3.size());
        assertEquals(12, content4.size());
    }
    
    
    @Test
    public void MoreThanTwoGeneratorsTest() throws EvaluationException, GroupException{
        GroupFactory fac = new GroupFactory();
        
        Group[] factors = new Group[2];
        factors[0] = fac.getDihedralGroup(3);
        factors[1] = fac.getDihedralGroup(4);
        
        Group product = GroupProduct.of(factors);
        
        Set<Set<Element>> gen = (Set<Set<Element>>)(product.getProperty(GroupProperty.StronglyMinimalGeneratingSets));
        Element unit = (Element)(product.getProperty(GroupProperty.UnitElement));      
        
        assertEquals(5376, gen.size());
        for(Set<Element> set : gen){
            assertEquals(3, set.size());
            assertTrue(!set.contains(unit));
        }
    }
}
