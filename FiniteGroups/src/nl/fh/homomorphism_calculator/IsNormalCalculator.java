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
 * @author frank
 */
public class IsNormalCalculator implements Calculator<GroupHomomorphism> {

    public IsNormalCalculator() {
    }

    @Override
    public Boolean evaluate(GroupHomomorphism morph) throws EvaluationException {
        boolean isEmbedding = (boolean) morph.getProperty(HomomorphismProperty.IsEmbedding);
        if(!isEmbedding){
            return false;
        }
        
        Group domain = (Group) morph.getProperty(HomomorphismProperty.Domain);
        Group codomain = (Group) morph.getProperty(HomomorphismProperty.Codomain);
        
        Map<Element, Map<Element, Element>> conjMap = (Map<Element, Map<Element, Element>>) codomain.getProperty(GroupProperty.ConjugationMap);
        
        Set<Element> domainSet = (Set<Element>) domain.getProperty(GroupProperty.Elements);
        Set<Element> codomainSet = (Set<Element>) codomain.getProperty(GroupProperty.Elements); 
        
        for(Element h : codomain){
            for(Element g : domain){
                // h.g.h^(-1) should be in the subset(=domain of the embedding)
                if(!domainSet.contains(conjMap.get(g).get(h))){
                    return false;
                }
            }
        }
        return true;
    }
    
}
