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
import nl.fh.group.GroupException;
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
public class EmbeddingTest {
    
    @Test
    public void D4CenterEmbeddingTest() throws GroupException, EvaluationException{
        
        GroupFactory fac = new GroupFactory();
        Group group = fac.getDihedralGroup(4);
        
        GroupHomomorphism centerEmbedding = (GroupHomomorphism) group.getProperty(GroupProperty.CenterEmbedding);
        Group center = (Group) centerEmbedding.getProperty(HomomorphismProperty.Domain);
        int order = (int) center.getProperty(GroupProperty.Order);
        
        assertEquals(2, order);
        
        boolean isEmbedding = (boolean) centerEmbedding.getProperty(HomomorphismProperty.IsEmbedding);
        assertTrue(isEmbedding);
        
        boolean isNormal = (boolean) centerEmbedding.getProperty(HomomorphismProperty.IsNormal);
        assertTrue(isNormal);
    }
}
