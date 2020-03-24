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

import java.util.HashSet;
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
 *  Calculates the kernel as a set, of a homomorphism. I.e. returns the 
 *  set of elements that get mapped to the unit of the codomain
 * 
 * @author frank
 */
public class KernelSetCalculator implements Calculator<GroupHomomorphism> {

    @Override
    public Set<Element> evaluate(GroupHomomorphism morph) throws EvaluationException {
        Group codomain = (Group) morph.getProperty(HomomorphismProperty.Codomain);
        Element unit = (Element) codomain.getProperty(GroupProperty.UnitElement);
        
        Map<Element, Element> map = (Map<Element, Element>) morph.getProperty(HomomorphismProperty.Map);
        
        Set<Element> result = new HashSet<Element>();
        for(Element g : map.keySet()){
            if(map.get(g).equals(unit)){
                result.add(g);
            }
        }
        
        return result;
    }
    
}
