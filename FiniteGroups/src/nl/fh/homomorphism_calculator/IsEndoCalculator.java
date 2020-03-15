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

import nl.fh.calculator.Calculator;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Group;
import nl.fh.homomorphism.GroupHomomorphism;

/**
 * Determines whether a homomorphism is an endomorphism, i.e. whether 
 * it maps a group to itself.
 * 
 * @author frank
 */
class IsEndoCalculator implements Calculator<GroupHomomorphism> {

    @Override
    public Boolean evaluate(GroupHomomorphism morph) throws EvaluationException {
         Group domain = (Group) morph.getProperty(HomomorphismProperty.Domain);
         Group codomain = (Group) morph.getProperty(HomomorphismProperty.Codomain);
         
         return domain.equals(codomain);
    }
    
}
