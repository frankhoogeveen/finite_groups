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
import nl.fh.calculator.Calculator;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
import nl.fh.homomorphism.GroupHomomorphism;

/**
 * Determines whether a GroupHomomorphism is a MonoMorphism, i.e. whether 
 * the mapping is injective. 
 * 
 * 
 * @author frank
 */
class IsMonoCalculator implements Calculator<GroupHomomorphism> {

    @Override
    public Boolean evaluate(GroupHomomorphism morph) throws EvaluationException {
          Map<Element, Element> map = (Map<Element, Element>) morph.getProperty(HomomorphismProperty.Map);
          int before = map.keySet().size();
          int after = (new HashSet(map.values())).size();
          return (before == after);
    }    
}
