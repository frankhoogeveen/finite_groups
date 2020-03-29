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
import nl.fh.homomorphism.GroupHomomorphism;
import nl.fh.homomorphism_calculator.HomomorphismProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class CenterTest {
    
 // three tests: abelean, trivial center and Q
    
    
    @Test
    public void Q2CenterTest() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group q = fac.getDicyclicGroup(2);
       
        GroupHomomorphism center = (GroupHomomorphism) q.getProperty(GroupProperty.CenterEmbedding);
        Group centerGroup = (Group) center.getProperty(HomomorphismProperty.Domain);
        int order = (int) centerGroup.getProperty(GroupProperty.Order);
        
        assertTrue((boolean) center.getProperty(HomomorphismProperty.IsEmbedding));
        assertEquals(2, order);
    }
    
    @Test
    public void AbeleanCenterTest() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group ab = fac.getAbeleanGroup(new int[]{2,3,4});
       
        GroupHomomorphism center = (GroupHomomorphism) ab.getProperty(GroupProperty.CenterEmbedding);
        Group centerGroup = (Group) center.getProperty(HomomorphismProperty.Domain);
        int order = (int) centerGroup.getProperty(GroupProperty.Order);
        
        assertTrue((boolean) center.getProperty(HomomorphismProperty.IsEmbedding));
        assertEquals(2*3*4, order);
    }
    
    @Test
    public void S4CenterTest() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group s4 = fac.getSymmetricGroup(4);
       
        GroupHomomorphism center = (GroupHomomorphism) s4.getProperty(GroupProperty.CenterEmbedding);
        Group centerGroup = (Group) center.getProperty(HomomorphismProperty.Domain);
        int order = (int) centerGroup.getProperty(GroupProperty.Order);
        
        assertTrue((boolean) center.getProperty(HomomorphismProperty.IsEmbedding));
        assertEquals(1, order);
    }
}
