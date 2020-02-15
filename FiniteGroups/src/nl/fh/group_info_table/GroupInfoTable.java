/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    

    public GroupInfoTable(List<Element> elements, Multiplicator multiplicator) {
        super.add(new MultiplicationTableCalculator(elements, multiplicator));
        super.add(new OrderCalculator());
        super.add(new UnitCalculator());
        super.add(new ElementOrderCalculator());
        super.add(new InverseCalculator());
        super.add(new CenterCalculator());
        super.add(new IsAbeleanCalculator());
        super.add(new ConjugationClassCalculator());
    }
}
