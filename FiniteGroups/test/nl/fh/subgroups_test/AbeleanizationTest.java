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

import nl.fh.calculator.EvaluationException;
import nl.fh.group.Group;
import nl.fh.group_calculators.GroupProperty;
import nl.fh.group_definition_factory.GroupFactory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class AbeleanizationTest {
    @Test
    public void Q4AbelianizationTest() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group q4 = fac.getDicyclicGroup(4);
        
        Group ab = (Group) q4.getProperty(GroupProperty.Abelianization);
        
        assertTrue((boolean)ab.getProperty(GroupProperty.IsAbelean));
    }
    
    @Test
    public void S4AbelianizationTest() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group s4 = fac.getSymmetricGroup(4);
        
        Group ab = (Group) s4.getProperty(GroupProperty.Abelianization);
        int order = (int) ab.getProperty(GroupProperty.Order);
        
        assertTrue((boolean)ab.getProperty(GroupProperty.IsAbelean));
        assertEquals(2, order);
        
    }
    
    @Test
    public void A5AbelianizationTest() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group a5 = fac.getAlternatingGroup(5);
        
        Group ab = (Group) a5.getProperty(GroupProperty.Abelianization);
        int order = (int) ab.getProperty(GroupProperty.Order);
        
        assertTrue((boolean)ab.getProperty(GroupProperty.IsAbelean));
        assertEquals(1, order);
    }
    
    @Test
        public void AbeleanAbelianizationTest() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group c33 = fac.getAbeleanGroup(new int[]{3,11});
        
        Group ab = (Group) c33.getProperty(GroupProperty.Abelianization);
        int order = (int) ab.getProperty(GroupProperty.Order);
        
        assertTrue((boolean)ab.getProperty(GroupProperty.IsAbelean));
        assertEquals(33, order);
    }
}
