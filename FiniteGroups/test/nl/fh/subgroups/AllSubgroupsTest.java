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
package nl.fh.subgroups;

import java.util.Set;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group_calculators.GroupProperty;
import nl.fh.factory.GroupFactory;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class AllSubgroupsTest {
    
    @Test
    public void AllSubgroupsD4Test() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group d4 = fac.getDihedralGroup(4);
        
        Set<Set<Element>> subGroups = (Set<Set<Element>>) d4.getProperty(GroupProperty.SubgroupSets);
        
        assertEquals(10, subGroups.size());
    }
    
    @Test
    public void AllSubgroupsS4Test() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group s4 = fac.getSymmetricGroup(4);
        
        Set<Set<Element>> subGroups = (Set<Set<Element>>) s4.getProperty(GroupProperty.SubgroupSets);
        
        assertEquals(30, subGroups.size());
    }
}
