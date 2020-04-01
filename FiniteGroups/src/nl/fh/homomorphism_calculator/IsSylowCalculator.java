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
package nl.fh.homomorphism_calculator;

import java.util.Map;
import nl.fh.calculator.Calculator;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Group;
import nl.fh.group_calculators.GroupProperty;
import nl.fh.homomorphism.GroupHomomorphism;
import nl.fh.number.IntNumber;

/**
 * Calculator returns true if the domain is a sylow subgroup of the codomain
 * 
 * @author frank
 */
public class IsSylowCalculator implements Calculator<GroupHomomorphism> {

    public IsSylowCalculator() {
    }

    @Override
    public Boolean evaluate(GroupHomomorphism morph) throws EvaluationException {
        boolean isEmbedding = (boolean) morph.getProperty(HomomorphismProperty.IsEmbedding);
        if(!isEmbedding){
            return false;
        }
        
        Group domain = (Group) morph.getProperty(HomomorphismProperty.Domain);
        Group codomain = (Group) morph.getProperty(HomomorphismProperty.Codomain);
        
        int nDomain = (int) domain.getProperty(GroupProperty.Order);
        int nCodomain  = (int) codomain.getProperty(GroupProperty.Order);
        
        if((nCodomain % nDomain) != 0){
            throw new IllegalStateException(this.getClass().getName());
        }
        
        Map<Integer, Integer> primeDecomposition = IntNumber.factorize(nDomain);
        if(primeDecomposition.keySet().size() > 1){
            return false;
        }
        
        int p = primeDecomposition.keySet().iterator().next();
        
        Map<Integer, Integer> primeDecompositionCo = IntNumber.factorize(nCodomain);        
        
        int expDomain = primeDecomposition.get(p);
        int expCodomain = primeDecompositionCo.get(p);
        
        return (expDomain == expCodomain);
        
    }
    
}
