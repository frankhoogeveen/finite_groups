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
package nl.fh.group_calculators;

import nl.fh.calculator.EvaluationException;
import nl.fh.group.Group;
import nl.fh.group_definition_factory.GroupFactory;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class FrattiniTest {
    
    @Test
    public void A4FrattiniTest() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group a4 = fac.getAlternatingGroup(4);
        
        Group frattini = (Group) a4.getProperty(GroupProperty.FrattiniGroup);
        
        int order = (int) frattini.getProperty(GroupProperty.Order);
        assertEquals(1, order);
    }
    
    @Test
    public void Q2FrattiniTest() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group group = fac.getDicyclicGroup(2);
        
        Group frattini = (Group) group.getProperty(GroupProperty.FrattiniGroup);
        
        int order = (int) frattini.getProperty(GroupProperty.Order);
        assertEquals(2, order);
    }
    
}
