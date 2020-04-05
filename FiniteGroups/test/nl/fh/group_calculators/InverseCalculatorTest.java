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
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group_def_substitutions.StringElement;
import nl.fh.group_def_substitutions.StringMultiplicator;
import nl.fh.group_def_substitutions.StringSubstitution;
import nl.fh.group_calculators.GroupProperty;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.GroupException;
import nl.fh.group.Multiplicator;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class InverseCalculatorTest {
    @Test
    public void Y21Test() throws EvaluationException, GroupException{
        Set<Element> generators = new HashSet<Element>();
        generators.add(new StringElement("x"));
        generators.add(new StringElement("y"));
        
        StringMultiplicator multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution("xxx", ""));
        multiplication.addSubstitution(new StringSubstitution("yyyyyyy", ""));
        multiplication.addSubstitution(new StringSubstitution("yx", "xyy"));
        
        String name = "Y21";
        
        Group group = new Group(name, generators, multiplication);
        
        
        
        Multiplicator table = ((Multiplicator)group.getProperty(GroupProperty.MultiplicationTable));
        Map<Element, Element> inv = ((Map<Element, Element>)group.getProperty(GroupProperty.Inverses));
        Element unit = ((Element)group.getProperty(GroupProperty.UnitElement));
        int order = ((int)group.getProperty(GroupProperty.Order));
                
        assertEquals(21, order);
        
        for(Element g : group){
            assertEquals(unit, table.getProduct(g, inv.get(g)));
            assertEquals(unit, table.getProduct(inv.get(g), g));
        }
    }
}
