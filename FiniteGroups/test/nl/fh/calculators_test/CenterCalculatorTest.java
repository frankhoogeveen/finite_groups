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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.Multiplicator;
import nl.fh.group_def_cyclic.CyclicElement;
import nl.fh.group_def_cyclic.CyclicMultiplicator;
import nl.fh.group_def_product.GroupProduct;
import nl.fh.group_def_substitutions.StringElement;
import nl.fh.group_def_substitutions.StringMultiplicator;
import nl.fh.group_def_substitutions.StringSubstitution;
import nl.fh.group_calculators.GroupProperty;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.GroupException;
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
public class CenterCalculatorTest {
    
    @Test 
    public void C3squaredTest() throws GroupException, EvaluationException{

        // create an instance of C3
        Set<Element> generators = new HashSet<Element>();
        generators.add(CyclicElement.generatorOfOrder(3));
        
        Multiplicator multiplication = new CyclicMultiplicator(3);
        
        Group def = new Group("C3", generators, multiplication);
        
        //define the product of c3 with itself
        List<Group> defs = new ArrayList<Group>();
        defs.add(def);
        defs.add(def);
        Group product = GroupProduct.of(defs);

        assertEquals(3*3, (int)product.getProperty(GroupProperty.Order));

        Set<Element> center = ((Set<Element>)product.getProperty(GroupProperty.CenterSet));
        assertEquals(9, center.size());
        
        boolean abelean = ((boolean)product.getProperty(GroupProperty.IsAbelean));
        assertTrue(abelean);

    }
    
    @Test
    public void Y21Test() throws EvaluationException, GroupException{
        Set<Element> generators = new HashSet<Element>();
        generators.add(new StringElement("x"));
        generators.add(new StringElement("y"));
        
        StringMultiplicator multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution("xxx", ""));
        multiplication.addSubstitution(new StringSubstitution("yyyyyyy", ""));
        multiplication.addSubstitution(new StringSubstitution("yx", "xyy"));
        
        String name = "Y21";
        
        Group group = new Group(name, generators, multiplication);
        
        assertEquals(21, (int)group.getProperty(GroupProperty.Order));
        
        Set<Element> center = ((Set<Element>)group.getProperty(GroupProperty.CenterSet));
        assertEquals(1, center.size());
        
        boolean abelean = ((boolean)group.getProperty(GroupProperty.IsAbelean));
        assertTrue(!abelean);
    }
    
    @Test
    public void D4Test() throws EvaluationException, GroupException{
        Set<Element> generators = new HashSet<Element>();
        generators.add(new StringElement("a"));
        generators.add(new StringElement("b"));
        
        StringMultiplicator multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution("aa", ""));
        multiplication.addSubstitution(new StringSubstitution("bbbb", ""));
        multiplication.addSubstitution(new StringSubstitution("ba", "abbb"));
        String name = "D4";
        
        Group group = new Group(name, generators, multiplication);
        
        assertEquals(8, (int)group.getProperty(GroupProperty.Order));

        Set<Element> center = ((Set<Element>)group.getProperty(GroupProperty.CenterSet));
        assertEquals(2, center.size());
        
        boolean abelean = ((boolean)group.getProperty(GroupProperty.IsAbelean));
        assertTrue(!abelean);
    }
}
