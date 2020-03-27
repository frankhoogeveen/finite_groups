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
package nl.fh.group_calculators;

import nl.fh.calculator.Calculator;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Group;
import nl.fh.homomorphism.GroupHomomorphism;
import nl.fh.homomorphism_calculator.HomomorphismProperty;

/**
 *
 * @author frank
 */
public class OuterAutomorphismGroupCalculator implements Calculator<Group> {

    public OuterAutomorphismGroupCalculator() {
    }

    @Override
    public Group evaluate(Group group) throws EvaluationException {
        GroupHomomorphism morp = (GroupHomomorphism) group.getProperty(GroupProperty.InnerAutomorphismEmbedding);
        return (Group) morp.getProperty(HomomorphismProperty.FactorGroup);
    }
}
