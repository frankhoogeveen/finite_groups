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
 * Is a morphism H->G represents a normal subgroup, this calculator determines
 * the factor group G/H
 * 
 * @author frank
 */
public class FactorGroupCalculator implements Calculator<GroupHomomorphism> {

    @Override
    public Group evaluate(GroupHomomorphism morph) throws EvaluationException {
        GroupHomomorphism quotient = (GroupHomomorphism) morph.getProperty(HomomorphismProperty.FactorHomomorphism);
        return (Group) quotient.getProperty(HomomorphismProperty.Codomain);
    }
    
}
