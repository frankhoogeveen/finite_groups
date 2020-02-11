/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group_info;

import nl.fh.group_info_calculators.OrderUpdater;
import nl.fh.group_info_calculators.MultiplicationTableCalculator;
import nl.fh.group_info_calculators.GroupElementsCalculator;
import nl.fh.group_info_calculators.UnitCalculator;

/**
 *  It is the concern of this class to keep track of which algorithms we use to 
 *  update the group info tables and where/how in the group info table 
 *  the results are stored.
 * 
 * The actual calculations of the group properties is not the concern of this.
 * 
 * @author frank
 */

class GroupInfoTableUpdater{
    private static final int MAX_ITERATIONS = 10000;
    
    private static final GroupElementsCalculator groupElementsUpdater = new GroupElementsCalculator(MAX_ITERATIONS);
    private static final OrderUpdater orderUpdater = new OrderUpdater();
    private static final UnitCalculator unitUpdater = new UnitCalculator();
    private static final MultiplicationTableCalculator multiplicationTableUpdater = new MultiplicationTableCalculator(MAX_ITERATIONS);
    
    
    static void calculateGroupElements(GroupInfoTable info) throws GroupInfoConstructionException {
        info.groupElements = groupElementsUpdater.calculate(info);
    }

    static void calculateOrder(GroupInfoTable info) throws GroupInfoConstructionException {
        info.order = orderUpdater.calculate(info);
    }

    static void calculateUnit(GroupInfoTable info) throws GroupInfoConstructionException {
        info.unit =  unitUpdater.calculate(info);
    }

    static void calculateMultiplicationTable(GroupInfoTable info) throws GroupInfoConstructionException {
        info.multiplicationTable =  multiplicationTableUpdater.calculate(info);
    }

}