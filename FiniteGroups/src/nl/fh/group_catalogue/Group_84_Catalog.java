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
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.GroupException;
import nl.fh.group_def_product.GroupProduct;
import nl.fh.group_def_substitutions.StringElement;
import nl.fh.group_def_substitutions.StringMultiplicator;
import nl.fh.group_def_substitutions.StringSubstitution;
import nl.fh.group_definition_factory.GroupFactory;
import nl.fh.group_formatter.GroupFormatter;

/**
 *
 * @author frank
 */
public class Group_84_Catalog extends GroupCatalog {
    private static final Logger LOGGER = Logger.getLogger(Group_84_Catalog.class.getSimpleName());
    private final GroupFactory factory;
    
    public Group_84_Catalog() throws EvaluationException, GroupException{
        super();
        factory = new GroupFactory();
        
        /* G84_1  A4 x C7 */
        Group c7 = factory.getCyclicGroup(7);
        Group a4 = factory.getAlternatingGroup(4);
        List<Group> list = new ArrayList<Group>();
        list.add(a4);
        list.add(c7);
        Group g1 = GroupProduct.of(list);
        
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
        
        Group g2 = new Group(name, generators, multiplication);
        
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
        
        Group y21 = new Group(name, generators, multiplication);
        Group c4 = factory.getCyclicGroup(4);
        
        list = new ArrayList<Group>();
        list.add(y21);
        list.add(c4);
        Group g3 = GroupProduct.of(list);
        
        super.add(g3);
        
        /* G84_4 */
        Group c2= factory.getCyclicGroup(2);
        
        list = new ArrayList<Group>();
        list.add(y21);
        list.add(c2);
        list.add(c2);
        Group g4 = GroupProduct.of(list);
        
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
        
        Group g5 = new Group(name, generators, multiplication);
        
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
        
        Group g6 = new Group(name, generators, multiplication);
        
        super.add(g6);
        
        /* G84_7  C42 x C2 */
        Group c42 = factory.getCyclicGroup(42);
        list = new ArrayList<Group>();
        list.add(c42);
        list.add(c2);
        Group g7 = GroupProduct.of(list);

        super.add(g7);
        
        /* G84_8 */
        Group d7 = factory.getDihedralGroup(7);
        Group d3 = factory.getDihedralGroup(3);
        
        list = new ArrayList<Group>();
        list.add(d7);
        list.add(d3);
        Group g8 = GroupProduct.of(list);
    
        super.add(g8);
        
        /* G84_9 */
        Group c6  = factory.getCyclicGroup(6);
        
        list = new ArrayList<Group>();
        list.add(d7);
        list.add(c6);
        Group g9 = GroupProduct.of(list);
        
        super.add(g9);
        
        /* G84_10 */
        Group d21  = factory.getDihedralGroup(21);
        
        list = new ArrayList<Group>();
        list.add(d21);
        list.add(c2);
        Group g10 = GroupProduct.of(list);
                
        super.add(g10);
        
        /* G84_11 */
        Group c14 = factory.getCyclicGroup(14);
        
        list = new ArrayList<Group>();
        list.add(d3);
        list.add(c14);
        Group g11 = GroupProduct.of(list);
                    
        super.add(g11);
        
        /* G84_12 */
        Group g12 = factory.getCyclicGroup(84);
                    
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
        
        Group g13 = new Group(name, generators, multiplication);
        
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
        
        Group g14 = new Group(name, generators, multiplication);
        
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
        
        Group g15 = new Group(name, generators, multiplication);
        
        super.add(g15);  

        
    }
    
    /**
     * main method that prints the entire catalog
     * @param args 
     */
    public static void main(String[] args) throws EvaluationException, GroupException{
  
        GroupCatalog cat = new Group_84_Catalog();
        GroupFormatter format = new GroupFormatter();
        System.out.println(format.createReport(cat));
    }
}
