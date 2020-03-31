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
import nl.fh.checker.GroupChecker;
import nl.fh.group.Group;
import nl.fh.group_calculators.GroupProperty;
import nl.fh.group_definition_factory.GroupFactory;
import nl.fh.homomorphism.GroupHomomorphism;
import nl.fh.homomorphism_calculator.HomomorphismProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * checks of taking the quotient of a group
 * center
 * squares
 * abelean
 * G/G -> C1
 * trivial (C1 as kernel and C1/C1)
 * derived
 * 
 * @author frank
 */
public class FactorGroupTest {
    
    @Test
    public void CenterFactorQTest() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group q = fac.getDicyclicGroup(2);
       
        GroupHomomorphism centerEmbedding = (GroupHomomorphism) q.getProperty(GroupProperty.CenterEmbedding);
        assertTrue((boolean) centerEmbedding.getProperty(HomomorphismProperty.IsEmbedding));
        assertTrue((boolean) centerEmbedding.getProperty(HomomorphismProperty.IsNormal));
        
        GroupHomomorphism quotient = (GroupHomomorphism) centerEmbedding.getProperty(HomomorphismProperty.FactorHomomorphism);
        Group factorGroup = (Group) quotient.getProperty(HomomorphismProperty.Codomain);
        
        // this should be the Viergroup
        int order = (int) factorGroup.getProperty(GroupProperty.Order);
        assertEquals(4, order);
        
        GroupChecker checker = new GroupChecker();
        assertTrue(checker.isGroup(factorGroup));
    }
    
    @Test
    public void SquaresS4Test() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group group = fac.getSymmetricGroup(4);
       
        GroupHomomorphism squaresEmbedding = (GroupHomomorphism) group.getProperty(GroupProperty.SquaresEmbedding);
        assertTrue((boolean) squaresEmbedding.getProperty(HomomorphismProperty.IsEmbedding));
        assertTrue((boolean) squaresEmbedding.getProperty(HomomorphismProperty.IsNormal));
        
        GroupHomomorphism quotient = (GroupHomomorphism) squaresEmbedding.getProperty(HomomorphismProperty.FactorHomomorphism);
        Group factorGroup = (Group) quotient.getProperty(HomomorphismProperty.Codomain);
        
        // this should be C2
        int order = (int) factorGroup.getProperty(GroupProperty.Order);
        assertEquals(2, order);
        
        GroupChecker checker = new GroupChecker();
        assertTrue(checker.isGroup(factorGroup));
    }
    
    @Test
    public void AbeleanTest() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group group = fac.getAbeleanGroup(new int[]{2,2,7});
       
        GroupHomomorphism squaresEmbedding = (GroupHomomorphism) group.getProperty(GroupProperty.SquaresEmbedding);
        assertTrue((boolean) squaresEmbedding.getProperty(HomomorphismProperty.IsEmbedding));
        assertTrue((boolean) squaresEmbedding.getProperty(HomomorphismProperty.IsNormal));
        
        GroupHomomorphism quotient = (GroupHomomorphism) squaresEmbedding.getProperty(HomomorphismProperty.FactorHomomorphism);
        Group factorGroup = (Group) quotient.getProperty(HomomorphismProperty.Codomain);
        
        // this should be C2xC2
        int order = (int) factorGroup.getProperty(GroupProperty.Order);
        assertEquals(4, order);
        
        GroupChecker checker = new GroupChecker();
        assertTrue(checker.isGroup(factorGroup));
    }
    
    @Test
    public void SelfEmbeddingTest() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group group = fac.getDihedralGroup(5);
       
        GroupHomomorphism selfEmbedding = (GroupHomomorphism) group.getProperty(GroupProperty.SelfEmbedding);
        assertTrue((boolean) selfEmbedding.getProperty(HomomorphismProperty.IsEmbedding));
        assertTrue((boolean) selfEmbedding.getProperty(HomomorphismProperty.IsNormal));
        
        GroupHomomorphism quotient = (GroupHomomorphism) selfEmbedding.getProperty(HomomorphismProperty.FactorHomomorphism);
        Group factorGroup = (Group) quotient.getProperty(HomomorphismProperty.Codomain);
        
        // this should be C1
        int order = (int) factorGroup.getProperty(GroupProperty.Order);
        assertEquals(1, order);
        
        GroupChecker checker = new GroupChecker();
        assertTrue(checker.isGroup(factorGroup));
    }
    
    @Test
    public void UnitEmbeddingTest() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group group = fac.getDihedralGroup(4);
       
        GroupHomomorphism unit = (GroupHomomorphism) group.getProperty(GroupProperty.UnitEmbedding);
        assertTrue((boolean) unit.getProperty(HomomorphismProperty.IsEmbedding));
        assertTrue((boolean) unit.getProperty(HomomorphismProperty.IsNormal));
        
        GroupHomomorphism quotient = (GroupHomomorphism) unit.getProperty(HomomorphismProperty.FactorHomomorphism);
        Group factorGroup = (Group) quotient.getProperty(HomomorphismProperty.Codomain);
        
        // this should be D4
        int order = (int) factorGroup.getProperty(GroupProperty.Order);
        assertEquals(8, order);
        
        GroupChecker checker = new GroupChecker();
        assertTrue(checker.isGroup(factorGroup));
    }
    
    @Test
    public void TrivialEmbeddingTest() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group group = fac.getCyclicGroup(1);
       
        GroupHomomorphism unit = (GroupHomomorphism) group.getProperty(GroupProperty.UnitEmbedding);
        assertTrue((boolean) unit.getProperty(HomomorphismProperty.IsEmbedding));
        assertTrue((boolean) unit.getProperty(HomomorphismProperty.IsNormal));
        
        GroupHomomorphism quotient = (GroupHomomorphism) unit.getProperty(HomomorphismProperty.FactorHomomorphism);
        Group factorGroup = (Group) quotient.getProperty(HomomorphismProperty.Codomain);
        
        // this should be C1
        int order = (int) factorGroup.getProperty(GroupProperty.Order);
        assertEquals(1, order);
        
        GroupChecker checker = new GroupChecker();
        assertTrue(checker.isGroup(factorGroup));
    }
    
    @Test 
    public void AutomorphismQuotientTest() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group group = fac.getDihedralGroup(4);
        
        Group aut = (Group) group.getProperty(GroupProperty.AutomorphismGroup);
        GroupHomomorphism inn = (GroupHomomorphism) group.getProperty(GroupProperty.InnerAutomorphismEmbedding);
        
        GroupHomomorphism quotient = (GroupHomomorphism) inn.getProperty(HomomorphismProperty.FactorHomomorphism);
        Group out = (Group) quotient.getProperty(HomomorphismProperty.Codomain);
        int order = (int) out.getProperty(GroupProperty.Order);
        
        assertEquals(2, order);
        
    }
}
