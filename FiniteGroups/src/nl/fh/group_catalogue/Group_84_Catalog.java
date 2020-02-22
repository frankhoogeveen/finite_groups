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
package nl.fh.group_catalogue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import nl.fh.group.Element;
import nl.fh.group.GroupDefinition;
import nl.fh.group_def_product.GroupProduct;
import nl.fh.group_def_substitutions.StringElement;
import nl.fh.group_def_substitutions.StringMultiplicator;
import nl.fh.group_def_substitutions.StringSubstitution;
import nl.fh.group_definition_factory.GroupDefinitionFactory;
import nl.fh.group_formatter.GroupFormatter;

/**
 *
 * @author frank
 */
public class Group_84_Catalog extends GroupCatalog {
    private static final Logger LOGGER = Logger.getLogger(Group_84_Catalog.class.getSimpleName());
    private final GroupDefinitionFactory factory;
    
    public Group_84_Catalog(){
        super();
        factory = new GroupDefinitionFactory();
        
        /* G84_1  A4 x C7 */
        GroupDefinition c7 = factory.getCyclicGroup(7);
        GroupDefinition a4 = factory.getAlternatingGroup(4);
        List<GroupDefinition> list = new ArrayList<GroupDefinition>();
        list.add(a4);
        list.add(c7);
        GroupDefinition g1 = GroupProduct.of(list);
        
        super.add(g1);
        
        /* G84_2 */
        Set<Element> generators = new HashSet<Element>();
        generators.add(new StringElement("x"));
        generators.add(new StringElement("y"));
        generators.add(new StringElement("z"));
        
        StringMultiplicator multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution("xxxx", ""));
        multiplication.addSubstitution(new StringSubstitution("yyy", ""));
        multiplication.addSubstitution(new StringSubstitution("zzzzzzz", ""));
        multiplication.addSubstitution(new StringSubstitution("yx", "xy"));
        multiplication.addSubstitution(new StringSubstitution("zx", "xzzzzzz"));
        multiplication.addSubstitution(new StringSubstitution("zy", "yzz"));
        
        String name = "G84_2";
        
        GroupDefinition g2 = new GroupDefinition(name, generators, multiplication);
        
        super.add(g2);
        
        /* G84_3 */
        generators = new HashSet<Element>();
        generators.add(new StringElement("x"));
        generators.add(new StringElement("y"));
        
        multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution("xxx", ""));
        multiplication.addSubstitution(new StringSubstitution("yyyyyyy", ""));
        multiplication.addSubstitution(new StringSubstitution("yx", "xyy"));
        
        name = "Y21";
        
        GroupDefinition y21 = new GroupDefinition(name, generators, multiplication);
        GroupDefinition c4 = factory.getCyclicGroup(4);
        
        list = new ArrayList<GroupDefinition>();
        list.add(y21);
        list.add(c4);
        GroupDefinition g3 = GroupProduct.of(list);
        
        super.add(g3);
        
        /* G84_4 */
        GroupDefinition c2= factory.getCyclicGroup(2);
        
        list = new ArrayList<GroupDefinition>();
        list.add(y21);
        list.add(c2);
        list.add(c2);
        GroupDefinition g4 = GroupProduct.of(list);
        
        super.add(g4);
        
        /* G84_5 */
        generators = new HashSet<Element>();
        generators.add(new StringElement("a"));
        generators.add(new StringElement("b"));
        generators.add(new StringElement("x"));
        generators.add(new StringElement("y"));

        
        multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution("aa", ""));
        multiplication.addSubstitution(new StringSubstitution("bb", ""));
        multiplication.addSubstitution(new StringSubstitution("xxx", ""));
        multiplication.addSubstitution(new StringSubstitution("yyyyyyy", ""));
        multiplication.addSubstitution(new StringSubstitution("ba", "ab"));
        multiplication.addSubstitution(new StringSubstitution("xa", "ax"));
        multiplication.addSubstitution(new StringSubstitution("xb", "bx"));
        multiplication.addSubstitution(new StringSubstitution("ya", "ayyyyyy"));
        multiplication.addSubstitution(new StringSubstitution("yb", "by"));
        multiplication.addSubstitution(new StringSubstitution("yx", "xyy"));
        
        name = "G84_5";
        
        GroupDefinition g5 = new GroupDefinition(name, generators, multiplication);
        
        super.add(g5);
        
        /* G84_6 */
        
        generators = new HashSet<Element>();
        generators.add(new StringElement("a"));
        generators.add(new StringElement("b"));
        generators.add(new StringElement("c"));
        generators.add(new StringElement("z"));

        
        multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution("aa", ""));
        multiplication.addSubstitution(new StringSubstitution("bb", ""));
        multiplication.addSubstitution(new StringSubstitution("ccc", ""));
        multiplication.addSubstitution(new StringSubstitution("zzzzzzz", ""));
        multiplication.addSubstitution(new StringSubstitution("ba", "ab"));
        multiplication.addSubstitution(new StringSubstitution("ca", "bc"));
        multiplication.addSubstitution(new StringSubstitution("cb", "abc"));
        multiplication.addSubstitution(new StringSubstitution("za", "az"));
        multiplication.addSubstitution(new StringSubstitution("zb", "bz"));
        multiplication.addSubstitution(new StringSubstitution("zc", "czz"));
        
        name = "G84_6";
        
        GroupDefinition g6 = new GroupDefinition(name, generators, multiplication);
        
        super.add(g6);
        
        /* G84_7  C42 x C2 */
        GroupDefinition c42 = factory.getCyclicGroup(42);
        list = new ArrayList<GroupDefinition>();
        list.add(c42);
        list.add(c2);
        GroupDefinition g7 = GroupProduct.of(list);

        super.add(g7);
        
        /* G84_8 */
        GroupDefinition d7 = factory.getDihedralGroup(7);
        GroupDefinition d3 = factory.getDihedralGroup(3);
        
        list = new ArrayList<GroupDefinition>();
        list.add(d7);
        list.add(d3);
        GroupDefinition g8 = GroupProduct.of(list);
    
        super.add(g8);
        
        /* G84_9 */
        GroupDefinition c6  = factory.getCyclicGroup(6);
        
        list = new ArrayList<GroupDefinition>();
        list.add(d7);
        list.add(c6);
        GroupDefinition g9 = GroupProduct.of(list);
        
        super.add(g9);
        
        /* G84_10 */
        GroupDefinition d21  = factory.getDihedralGroup(21);
        
        list = new ArrayList<GroupDefinition>();
        list.add(d21);
        list.add(c2);
        GroupDefinition g10 = GroupProduct.of(list);
                
        super.add(g10);
        
        /* G84_11 */
        GroupDefinition c14 = factory.getCyclicGroup(14);
        
        list = new ArrayList<GroupDefinition>();
        list.add(d3);
        list.add(c14);
        GroupDefinition g11 = GroupProduct.of(list);
                    
        super.add(g11);
        
        /* G84_12 */
        GroupDefinition g12 = factory.getCyclicGroup(84);
                    
        super.add(g12);
        
        /* G84_13 */
        generators = new HashSet<Element>();
        generators.add(new StringElement("x"));
        generators.add(new StringElement("y"));
        generators.add(new StringElement("z"));

        multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution("xxxx", ""));
        multiplication.addSubstitution(new StringSubstitution("yyy", ""));
        multiplication.addSubstitution(new StringSubstitution("zzzzzzz", ""));
        multiplication.addSubstitution(new StringSubstitution("yx", "xy"));
        multiplication.addSubstitution(new StringSubstitution("zx", "xzzzzzz"));
        multiplication.addSubstitution(new StringSubstitution("zy", "yz"));
        
        name = "G84_13";
        
        GroupDefinition g13 = new GroupDefinition(name, generators, multiplication);
        
        super.add(g13);
        
        /* G84_14 */
        generators = new HashSet<Element>();
        generators.add(new StringElement("x"));
        generators.add(new StringElement("y"));
        generators.add(new StringElement("z"));

        multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution("xxxx", ""));
        multiplication.addSubstitution(new StringSubstitution("yyy", ""));
        multiplication.addSubstitution(new StringSubstitution("zzzzzzz", ""));
        multiplication.addSubstitution(new StringSubstitution("yx", "xyy"));
        multiplication.addSubstitution(new StringSubstitution("zx", "xz"));
        multiplication.addSubstitution(new StringSubstitution("zy", "yz"));
        
        name = "G84_14";
        
        GroupDefinition g14 = new GroupDefinition(name, generators, multiplication);
        
        super.add(g14); 
        
        /* G84_15 */
        generators = new HashSet<Element>();
        generators.add(new StringElement("x"));
        generators.add(new StringElement("y"));
        generators.add(new StringElement("z"));

        multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution("xxxx", ""));
        multiplication.addSubstitution(new StringSubstitution("yyy", ""));
        multiplication.addSubstitution(new StringSubstitution("zzzzzzz", ""));
        multiplication.addSubstitution(new StringSubstitution("yx", "xyy"));
        multiplication.addSubstitution(new StringSubstitution("zx", "xzzzzzz"));
        multiplication.addSubstitution(new StringSubstitution("zy", "yz"));
        
        name = "G84_15";
        
        GroupDefinition g15 = new GroupDefinition(name, generators, multiplication);
        
        super.add(g15);  

        
    }
    
    /**
     * main method that prints the entire catalog
     * @param args 
     */
    public static void main(String[] args){
  
        GroupCatalog cat = new Group_84_Catalog();
        GroupFormatter format = new GroupFormatter();
        System.out.println(format.createReport(cat));
    }
}
