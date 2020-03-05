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
package nl.fh.group;

import nl.fh.group_calculators.ConjugationClassesOrdersCalculator;
import nl.fh.group_calculators.ConjugationClassesSetCalculator;
import nl.fh.calculator.Calculator;
import nl.fh.calculator.Property;
import nl.fh.group_calculators.CenterCalculator;
import nl.fh.group_calculators.ConjugationClassesMapCalculator;
import nl.fh.group_calculators.ElementOrderCalculator;
import nl.fh.group_calculators.InverseCalculator;
import nl.fh.group_calculators.IsAbeleanCalculator;
import nl.fh.group_calculators.OrderCalculator;
import nl.fh.group_calculators.StronglyMinimalGeneratorsCalculator;
import nl.fh.group_calculators.UnitCalculator;

/**
 *
 * @author frank
 */
public enum GroupProperty implements Property {

    // properties that are set when defining the group
    Name(null),
    Elements(null),
    MultiplicationTable(null),     
    
    // properties that could be calculated at a later stage
    Order(new OrderCalculator()),
    UnitElement(new UnitCalculator()),
    ElementOrders(new ElementOrderCalculator()),
    Inverses(new InverseCalculator()),
    Center(new CenterCalculator()),
    IsAbelean(new IsAbeleanCalculator()),
    ConjugationClassesMap(new ConjugationClassesMapCalculator()),
    ConjugationClassesSet(new ConjugationClassesSetCalculator()),
    StronglyMinimalGeneratingSets(new StronglyMinimalGeneratorsCalculator()), 
    ConjugationsClassesOrders(new ConjugationClassesOrdersCalculator());

    private final Calculator calculator;
    
    /**
     * 
     * @param calc the calculator
     * 
     * The caller should know what to do with the output of the calculator
     * , in particular to what class to cast it,
     * 
     */
    private GroupProperty(Calculator<Group> calc){
        this.calculator = calc;
    }

    @Override
    public Calculator getCalculator() {
        return this.calculator;
    }
}
