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
package nl.fh.group_info_table;

import nl.fh.group_info_calculators.ConjugationClassCalculator;
import nl.fh.group_info_calculators.IsAbeleanCalculator;
import nl.fh.group_info_calculators.CenterCalculator;
import nl.fh.group_info_calculators.InverseCalculator;
import nl.fh.group_info_calculators.ElementOrderCalculator;
import java.util.List;
import nl.fh.group.Element;
import nl.fh.group.Multiplicator;
import nl.fh.group_info_calculators.*;
import nl.fh.info_table.InfoTable;

/**
 *
 * @author frank
 */
public class GroupInfoTable extends InfoTable {
    

    /**
     * This is an info table pre-loaded with a set of calculators that are 
     * relevant for groups.
     * 
     * @param elements
     * @param multiplicator 
     */
    public GroupInfoTable(List<Element> elements, Multiplicator multiplicator) {
        super.add(new MultiplicationTableCalculator(elements, multiplicator));
        super.add(new OrderCalculator());
        super.add(new UnitCalculator());
        super.add(new ElementOrderCalculator());
        super.add(new InverseCalculator());
        super.add(new CenterCalculator());
        super.add(new IsAbeleanCalculator());
        super.add(new ConjugationClassCalculator());
        super.add(new StronglyMinimalGeneratorsCalculator());
        super.add(new IsNormalCalculator());
    }
}
