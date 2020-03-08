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
package nl.fh.group_test;

import java.util.Set;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group_definition_factory.GroupFactory;
import nl.fh.group.GroupProperty;
import nl.fh.group.GroupChecker;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.GroupException;
import nl.fh.group.Multiplicator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class SubgroupTest {
    
    @Test
    public void S4Test() throws EvaluationException, GroupException{
        GroupFactory fac = new GroupFactory();
        Group s4 = fac.getSymmetricGroup(4);
        
        Set<Set<Element>> gen = (Set<Set<Element>>)(s4.getProperty(GroupProperty.StronglyMinimalGeneratingSets));
        
        GroupChecker checker = new GroupChecker();
        for(Set<Element> set : gen){
            Group subGroup = new Group("subgroup", set, (Multiplicator) s4.getProperty(GroupProperty.MultiplicationTable));
            assertEquals(24, (int)subGroup.getProperty(GroupProperty.Order));
            assertTrue(checker.isGroup(subGroup));
        }
    }
    
    @Test
    public void S5CenterTest() throws EvaluationException, GroupException{
        GroupFactory fac = new GroupFactory();
        Group s5 = fac.getSymmetricGroup(5);
        
        Set<Element> center = (Set<Element>) s5.getProperty(GroupProperty.Center);
        
        Group sub =new Group( "subgroup", center, (Multiplicator) s5.getProperty(GroupProperty.MultiplicationTable));

        GroupChecker checker = new GroupChecker();
        assertTrue(checker.isGroup(sub));

        assertTrue((boolean)(sub.getProperty(GroupProperty.IsAbelean)));
    }
    
    
}
