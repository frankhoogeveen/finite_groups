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
package nl.fh.subgroups;

import java.util.Set;
import nl.fh.calculator.Calculator;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group_calculators.EmbeddingCalculator;
import nl.fh.group_calculators.GroupFromSetCalculator;
import nl.fh.group_calculators.GroupProperty;
import nl.fh.group_calculators.SquaresSetCalculator;
import nl.fh.factory.GroupFactory;
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
    
    
    @Test
    public void A4quaredTestExplicit() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group a4 = fac.getAlternatingGroup(4);
        
        // check the set
        Calculator calc = new SquaresSetCalculator();
        Set<Element> squares2 = (Set<Element>) calc.evaluate(a4);
        assertEquals(9, squares2.size());
        
        //check the group
        calc = new GroupFromSetCalculator("Sq", GroupProperty.SquaresSet);
        Group squaresGroup = (Group) calc.evaluate(a4);
        int order = (int) a4.getProperty(GroupProperty.Order);
        assertEquals(12, order);
        
        //check the embedding
        calc = new EmbeddingCalculator(GroupProperty.SquaresGroup);
        GroupHomomorphism morph = (GroupHomomorphism) calc.evaluate(a4);
        
        Group domain = (Group) morph.getProperty(HomomorphismProperty.Domain);
        order = (int) domain.getProperty(GroupProperty.Order);
        assertEquals(12, order);
     }
}
