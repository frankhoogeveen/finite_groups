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
package nl.fh.group_def_substitutions;

import nl.fh.group_def_substitutions.StringSubstitution;
import nl.fh.group.Element;
import nl.fh.group_def_substitutions.StringElement;
import nl.fh.group_def_substitutions.StringMultiplicator;
import java.util.HashSet;
import java.util.Set;
import nl.fh.group.Group;
import nl.fh.group_calculators.GroupProperty;
import nl.fh.checker.GroupChecker;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.GroupException;
import nl.fh.group.GroupException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class GroupSubstitutionInconsistentTest {
    /**
     *  The test set up here looks like the construction of Y21, but is 
     *  subtly different.
     * 
     *  Instead of y.x-> x.y.y,, we take y.x-> x.y.y.y
     * 
     * From this relation we can prove
     * y = y.x.x.x = x y.y.y .x.x = ... = x^3 y^27, thus y = y^6 
     * 
     * From this one can conclude y = unit. 
     * 
     * Therefore the group definition is inconsistent. 
     * The expected behavior is to return false when checked.
     * 
     * @throws EvaluationException 
     */
    @Test
    public void PseudoY21Test() throws EvaluationException, GroupException{
        Set<Element> generators = new HashSet<Element>();
        generators.add(new StringElement("x"));
        generators.add(new StringElement("y"));
        
        StringMultiplicator multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution("xxx", ""));
        multiplication.addSubstitution(new StringSubstitution("yyyyyyy", ""));
        multiplication.addSubstitution(new StringSubstitution("yx", "xyyy"));
        
        String name = "Y21";
        
        Group pseudo = new Group(name, generators, multiplication);
        
        try {
            assertEquals(21, (int)pseudo.getProperty(GroupProperty.Order));
            
            GroupChecker check = new GroupChecker();
            
            assertFalse(check.isGroup(pseudo));
        } catch (EvaluationException ex) {
            assertTrue(false);
        }
    }
    
 }
