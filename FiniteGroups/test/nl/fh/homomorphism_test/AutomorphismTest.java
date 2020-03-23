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

import nl.fh.calculator.EvaluationException;
import nl.fh.group.Group;
import nl.fh.group_calculators.GroupProperty;
import nl.fh.group_definition_factory.GroupFactory;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class AutomorphismTest {
    boolean verbose = true;
    
    
    @Test
    public void D2Test() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group group = fac.getDihedralGroup(2);
        
        Group aut = (Group) group.getProperty(GroupProperty.AutomorphismGroup);
        
        int aut_order = (int) aut.getProperty(GroupProperty.Order);
        assertEquals(6, aut_order);   
    }
    
    @Test
    public void Q2Test() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group group = fac.getDicyclicGroup(2);
        
        Group aut = (Group) group.getProperty(GroupProperty.AutomorphismGroup);
        
        int aut_order = (int) aut.getProperty(GroupProperty.Order);
        assertEquals(24, aut_order); 
    }
    
        
    @Test
    public void AbeleanTest() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group group = fac.getAbeleanGroup(new int[]{2,3,5});
        
        Group aut = (Group) group.getProperty(GroupProperty.AutomorphismGroup);
        
        int aut_order = (int) aut.getProperty(GroupProperty.Order);
        assertEquals(8, aut_order); 
    }
    
    @Test
    public void S3Test() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group group = fac.getSymmetricGroup(3);
        
        Group aut = (Group) group.getProperty(GroupProperty.AutomorphismGroup);
        
        int aut_order = (int) aut.getProperty(GroupProperty.Order);
        assertEquals(6, aut_order); 
    }
    
    @Test
    public void S4Test() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group group = fac.getSymmetricGroup(4);
        
        Group aut = (Group) group.getProperty(GroupProperty.AutomorphismGroup);
        
        int aut_order = (int) aut.getProperty(GroupProperty.Order);
        assertEquals(24, aut_order); 
    }
}
