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
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class GroupSubstitutionConstructorTest {
    @Test
    public void Cyclic3Test(){
        Set<Element> generators = new HashSet<Element>();
        generators.add(new StringElement("a"));
        
        StringMultiplicator multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution("aaa", ""));
        
        String name = "C3";

        try {
            Group g = new Group(name, generators, multiplication);
            assertEquals(3, (g.getProperty(GroupProperty.Order)));
            
            GroupChecker check = new GroupChecker();
            assertTrue(check.isGroup(g));
        } catch (EvaluationException | GroupException ex) {
            assertTrue(false);
        } 
    }
    
    @Test
    public void Cyclic7Test(){
        Set<Element> generators = new HashSet<Element>();
        generators.add(new StringElement("a"));
        
        StringMultiplicator multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution("aaaaaaa", ""));
        
        String name = "C7";
        
        try {
            Group g = new Group(name, generators, multiplication);
            assertEquals(7, (g.getProperty(GroupProperty.Order)));
            
            GroupChecker check = new GroupChecker();
            assertTrue(check.isGroup(g));
        } catch (Exception ex) {
            assertTrue(false);
        } 
    }
    
    @Test
    public void D3Test() throws EvaluationException{
        Set<Element> generators = new HashSet<Element>();
        generators.add(new StringElement("a"));
        generators.add(new StringElement("b"));
        
        StringMultiplicator multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution("aa", ""));
        multiplication.addSubstitution(new StringSubstitution("bbb", ""));
        multiplication.addSubstitution(new StringSubstitution("ba", "abb"));

        String name = "D3";
      
        try {
            Group g = new Group(name, generators, multiplication);
            assertEquals(6, (g.getProperty(GroupProperty.Order)));
            
            GroupChecker check = new GroupChecker();
            assertTrue(check.isGroup(g));
        } catch (Exception ex) {
            assertTrue(false);
        } 
    }
    
    @Test
    public void Y21Test() throws EvaluationException{
        Set<Element> generators = new HashSet<Element>();
        generators.add(new StringElement("x"));
        generators.add(new StringElement("y"));
        
        StringMultiplicator multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution("xxx", ""));
        multiplication.addSubstitution(new StringSubstitution("yyyyyyy", ""));
        multiplication.addSubstitution(new StringSubstitution("yx", "xyy"));
        
        String name = "Y21";
        
        try {
            Group g = new Group(name, generators, multiplication);
            assertEquals(21, (g.getProperty(GroupProperty.Order)));
            
            GroupChecker check = new GroupChecker();
            assertTrue(check.isGroup(g));
        } catch (Exception ex) {
            assertTrue(false);
        } 
    }
    
    

    /**
     * creates a group of order 84
     */
    @Test
    public void G84Test(){
        Set<Element> generators = new HashSet<Element>();
        generators.add(new StringElement("x"));
        generators.add(new StringElement("y"));
        generators.add(new StringElement("z"));
        
        StringMultiplicator multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution("xxx", ""));
        multiplication.addSubstitution(new StringSubstitution("yyyy", ""));
        multiplication.addSubstitution(new StringSubstitution("zzzzzzz", ""));
        multiplication.addSubstitution(new StringSubstitution("yx", "xy"));
        multiplication.addSubstitution(new StringSubstitution("zx", "xzz"));
        multiplication.addSubstitution(new StringSubstitution("zy", "yzzzzzz"));        
        
        String name = "G84_2";
        
        try {
            Group g = new Group(name, generators, multiplication);
            assertEquals(84, (g.getProperty(GroupProperty.Order)));
            
            GroupChecker check = new GroupChecker();
            assertTrue(check.isGroup(g));
        } catch (Exception ex) {
            assertTrue(false);
        } 
    }
}
