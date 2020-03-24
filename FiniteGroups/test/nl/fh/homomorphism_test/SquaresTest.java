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

import java.util.Set;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
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
public class SquaresTest {
    
    
    
    @Test
    public void QsquaredTest() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group q = fac.getDicyclicGroup(2);
        
        Set<Element> squares = (Set<Element>) q.getProperty(GroupProperty.SquaresSet);
        assertEquals(2, squares.size());
        
        GroupHomomorphism sq = (GroupHomomorphism) q.getProperty(GroupProperty.SquaresEmbedding);
        
        assertTrue((boolean) sq.getProperty(HomomorphismProperty.IsEmbedding));
        assertTrue((boolean) sq.getProperty(HomomorphismProperty.IsNormal));
    }
    
    @Test
    public void A4quaredTest() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group a4 = fac.getAlternatingGroup(4);
        
        Set<Element> squares = (Set<Element>) a4.getProperty(GroupProperty.SquaresSet);
        assertEquals(9, squares.size());
        
        GroupHomomorphism sq = (GroupHomomorphism) a4.getProperty(GroupProperty.SquaresEmbedding);
        
        assertTrue((boolean) sq.getProperty(HomomorphismProperty.IsEmbedding));
        assertTrue((boolean) sq.getProperty(HomomorphismProperty.IsNormal));
        
        Group sqGroup = (Group)sq.getProperty(HomomorphismProperty.Domain);
        int order = (int) sqGroup.getProperty(GroupProperty.Order);
        
        assertEquals(12, order);
     }
    
}
