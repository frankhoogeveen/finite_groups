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

import nl.fh.group_calculators.GroupTable;
import nl.fh.group_calculators.GroupProperty;
import nl.fh.group_calculators.GroupPowerTable;
import java.util.Map;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.*;
import nl.fh.group_definition_factory.GroupFactory;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class PowerTableCalculatorTest {
    @Test
    public void D4Test() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group d4= fac.getDihedralGroup(4);
        
        GroupTable table = (GroupTable) d4.getProperty(GroupProperty.MultiplicationTable);
        GroupPowerTable powerTable = (GroupPowerTable)(Map<Element, Map<Integer, Element>> )d4.getProperty(GroupProperty.PowerTable);
        Element unit = (Element) d4.getProperty(GroupProperty.UnitElement);
        
        for(Element g : d4){
            Element[] powers = new Element[8];
            powers[0] = unit;
            for(int i = 1; i < 8; i++){
                powers[i] = table.getProduct(g, powers[i-1]);
            }
            
            for(int i = 0; i < 8; i++){
                assertEquals(powers[i], powerTable.get(g).get(i));
            }
        }
    }
    
    @Test
    public void D5Test() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group d5 = fac.getDihedralGroup(5);
        
        GroupTable table = (GroupTable) d5.getProperty(GroupProperty.MultiplicationTable);
        GroupPowerTable powerTable = (GroupPowerTable)(Map<Element, Map<Integer, Element>> )d5.getProperty(GroupProperty.PowerTable);
        Element unit = (Element) d5.getProperty(GroupProperty.UnitElement);
        
        for(Element g : d5){
            assertEquals(unit, powerTable.power(g, 0));
            assertEquals(g, powerTable.power(g, 1));
            assertEquals(table.getProduct(g, g), powerTable.power(g,2));
            assertEquals(unit, table.getProduct(g, powerTable.power(g, -1)));
            assertEquals(g, powerTable.power(g, -19));
        }
    }
    
}
