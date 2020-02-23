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

import nl.fh.info_table_values.BooleanValue;
import nl.fh.info_table_values.SubsetValue;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.GroupDefinition;
import nl.fh.group.Multiplicator;
import nl.fh.group_def_cyclic.CyclicElement;
import nl.fh.group_def_cyclic.CyclicMultiplicator;
import nl.fh.group_def_product.GroupProduct;
import nl.fh.group_def_substitutions.StringElement;
import nl.fh.group_def_substitutions.StringMultiplicator;
import nl.fh.group_def_substitutions.StringSubstitution;
import nl.fh.group_info_calculators.GroupProperty;
import nl.fh.info_table.InfoTable;
import nl.fh.info_table.InfoTableException;
import nl.fh.info_table_values.IntValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class CenterCalculatorTest {
    
    @Test 
    public void C3squaredTest(){

        // create an instance of C3
        Set<Element> generators = new HashSet<Element>();
        generators.add(CyclicElement.generatorOfOrder(3));
        
        Multiplicator multiplication = new CyclicMultiplicator(3);
        
        GroupDefinition def = new GroupDefinition("C3", generators, multiplication);
        
        //define the product
        List<GroupDefinition> defs = new ArrayList<GroupDefinition>();
        defs.add(def);
        defs.add(def);
        GroupDefinition product = GroupProduct.of(defs);

        try {
            Group g = new Group(product);
            InfoTable info =  g.getInfo();
            assertEquals(3*3, ((IntValue)info.getValue(GroupProperty.Order)).content());
            
            SubsetValue center = ((SubsetValue)info.getValue(GroupProperty.Center));
            BooleanValue abelean = ((BooleanValue)info.getValue(GroupProperty.IsAbelean));
            
            assertEquals(9, center.count());
            assertTrue(abelean.content());
            
        } catch (InfoTableException ex) {
            assertTrue(false);
        }
    }
    
    @Test
    public void Y21Test() throws InfoTableException{
        Set<Element> generators = new HashSet<Element>();
        generators.add(new StringElement("x"));
        generators.add(new StringElement("y"));
        
        StringMultiplicator multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution("xxx", ""));
        multiplication.addSubstitution(new StringSubstitution("yyyyyyy", ""));
        multiplication.addSubstitution(new StringSubstitution("yx", "xyy"));
        
        String name = "Y21";
        
        GroupDefinition definition = new GroupDefinition(name, generators, multiplication);
        
        try {
            Group g = new Group(definition);
            InfoTable info =  g.getInfo();
            assertEquals(21, ((IntValue)info.getValue(GroupProperty.Order)).content());
            
            SubsetValue center = ((SubsetValue)info.getValue(GroupProperty.Center));
            BooleanValue abelean = ((BooleanValue)info.getValue(GroupProperty.IsAbelean));
            
            assertEquals(1, center.count());
            assertFalse(abelean.content());
            
        } catch (InfoTableException ex) {
            assertTrue(false);
        }
    }
    
        @Test
    public void D4Test() throws InfoTableException{
        Set<Element> generators = new HashSet<Element>();
        generators.add(new StringElement("a"));
        generators.add(new StringElement("b"));
        
        StringMultiplicator multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution("aa", ""));
        multiplication.addSubstitution(new StringSubstitution("bbbb", ""));
        multiplication.addSubstitution(new StringSubstitution("ba", "abbb"));
        String name = "D4";
        
        GroupDefinition definition = new GroupDefinition(name, generators, multiplication);
        
        try {
            Group g = new Group(definition);
            InfoTable info =  g.getInfo();
            assertEquals(8, ((IntValue)info.getValue(GroupProperty.Order)).content());
            
            SubsetValue center = ((SubsetValue)info.getValue(GroupProperty.Center));
            assertEquals(2, center.count());
            
        } catch (InfoTableException ex) {
            assertTrue(false);
        }
    }
    
    
}
