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
import java.util.Set;
import nl.fh.calculator.Calculator;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group_calculators.GroupProperty;
import nl.fh.homomorphism.GroupHomomorphism;

/**
 *
 * This calculator returns true, if the homomorphism is an embedding
 * 
 * @author frank
 */
public class IsEmbeddingCalculator implements Calculator<GroupHomomorphism> {

    public IsEmbeddingCalculator() {
    }

    @Override
    public Boolean evaluate(GroupHomomorphism morph) throws EvaluationException {
        Group domain = (Group) morph.getProperty(HomomorphismProperty.Domain);
        Group codomain = (Group) morph.getProperty(HomomorphismProperty.Codomain);
        
        Set<Element> domainSet = (Set<Element>) domain.getProperty(GroupProperty.Elements);
        Set<Element> codomainSet = (Set<Element>) codomain.getProperty(GroupProperty.Elements);
        
        if(!codomainSet.containsAll(domainSet)){
            return false;
        }
        
        Map<Element, Element> map = (Map<Element, Element>)morph.getProperty(HomomorphismProperty.Map);
        for(Element g : domainSet){
            if(!map.containsKey(g)){
                return false;
            }
            if(!map.get(g).equals(g)){
                return false;
            }
        }
        
        return true;
    }
    
}
