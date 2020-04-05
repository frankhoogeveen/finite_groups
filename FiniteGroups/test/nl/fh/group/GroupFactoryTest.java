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

import nl.fh.group.Group;
import nl.fh.group_definition_factory.GroupFactory;
import nl.fh.group_calculators.GroupProperty;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.GroupException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class GroupFactoryTest {
    
    public GroupFactoryTest(){
        
    }
    
    @Test
    public void CyclicTest() throws EvaluationException, GroupException{
        GroupFactory fac = new GroupFactory();
        Group c3 = fac.getCyclicGroup(3);
        assertEquals(3, (int)c3.getProperty(GroupProperty.Order));
    }
    
    public void AbeleanTest() throws EvaluationException, GroupException{
        GroupFactory fac = new GroupFactory();
        Group c30 = fac.getAbeleanGroup(new int[]{2,3,5});
        
        assertEquals(2*3*5, (int)c30.getProperty(GroupProperty.Order));
    }
    
    public void G16FactoryTest() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group g16_1 = fac.getGroup16_1();
        Group g16_2 = fac.getGroup16_2();
        
        assertEquals(16, (int)g16_1.getProperty(GroupProperty.Order));
        assertEquals(16, (int)g16_2.getProperty(GroupProperty.Order));        
    }
    
}
