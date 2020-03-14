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
package nl.fh.group_def_product;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.Multiplicator;
import nl.fh.group_calculators.GroupProperty;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.GroupException;
import nl.fh.group_definition_factory.GroupFactory;

/**
 * Creates the direct product of several groups
 * 
 * @author frank
 */
public class GroupProduct {
    private static final Group trivial = new GroupFactory().getCyclicGroup(1);
    
    public static Group of(List<Group> factors) throws EvaluationException, GroupException {
        if(factors.isEmpty()){
            return trivial;
        }
        
        String name = productName(factors);
        
        Set<Element> generators = createGenerators(factors);
        Multiplicator<ProductElement> multiplication = new ProductMultiplicator(factors);
     
        Group product = new Group(name, generators, multiplication);
        return product;
    }

    public static Group of(Group[] factors) throws EvaluationException, GroupException{
        return GroupProduct.of(Arrays.asList(factors));
    }
    
    private static String productName(List<Group> factors) throws EvaluationException {
        if(factors.isEmpty()){
            return "C1";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append((String)factors.get(0).getProperty(GroupProperty.Name));
        for(int i = 1; i < factors.size(); i++){
            sb.append("x");
            sb.append((String)factors.get(i).getProperty(GroupProperty.Name));
        }
        
        return sb.toString();
    }
    
    private static Set<Element> createGenerators(List<Group> factors) throws EvaluationException {
        Element[] unitElement = getUnitElementOfProduct(factors);
        
        Set<Element> result = new HashSet<Element>();
        
        for(int i =0; i < factors.size(); i++){
            for(Element g : (Set<Element>)factors.get(i).getProperty(GroupProperty.Elements)){
                    Element[] newGen = unitElement.clone();
                    newGen[i] = g;
                    result.add(new ProductElement(newGen));
            }
        }
        
        return result;
    }

    private static Element[] getUnitElementOfProduct(List<Group> factors) throws EvaluationException {
        Element[] result = new Element[factors.size()];
        for(int i =0; i< factors.size(); i++){
            result[i] = (Element) factors.get(i).getProperty(GroupProperty.UnitElement);
        }
        return result;
    }
}
