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

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import nl.fh.calculator.Calculator;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
import nl.fh.group.Group;

/**
 * Calculate the set of conjugation classes as a Set<Set<Element>>
 * 
 * @author frank
 */
class ConjugationClassesSetCalculator implements Calculator<Group> {

    ConjugationClassesSetCalculator() {
    }

    @Override
    public Set<Set<Element>> evaluate(Group group) throws EvaluationException {
         Map<Element, Set<Element>> map = (Map<Element, Set<Element>>)group.getProperty(GroupProperty.ConjugationClassesMap);         
         return new HashSet(map.values());
    }
}
