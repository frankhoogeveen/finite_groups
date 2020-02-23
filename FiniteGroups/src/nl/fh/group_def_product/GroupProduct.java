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
import nl.fh.group.GroupDefinition;
import nl.fh.group.Multiplicator;

/**
 *
 * @author frank
 */
public class GroupProduct {


    
    public static GroupDefinition of(List<GroupDefinition> defs) {
        String name = productName(defs);
        
        Set<Element> generators = createGenerators(defs);
        Multiplicator<ProductElement> multiplication = new ProductMultiplicator(defs);
     
        GroupDefinition product = new GroupDefinition(name, generators, multiplication );
        return product;
    }

    public static GroupDefinition of(GroupDefinition[] defs){
        return GroupProduct.of(Arrays.asList(defs));
    }
    
    private static String productName(List<GroupDefinition> defs) {
        if(defs.isEmpty()){
            return "C1";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(defs.get(0).getName());
        for(int i = 1; i < defs.size(); i++){
            sb.append("x");
            sb.append(defs.get(i).getName());
        }
        
        return sb.toString();
    }
    
    
    private static Set<Element> createGenerators(List<GroupDefinition> defs) {
        Element[] unitElement = getUnitElementOfProduct(defs);
        
        Set<Element> result = new HashSet<Element>();
        for(int i =0; i < defs.size(); i++){
            for(Element gen : defs.get(i).getGenerators()){
                Element[] newGen = unitElement.clone();
                newGen[i] = gen;
                result.add(new ProductElement(newGen));
            }
        }
        
        return result;
    }

    private static Element[] getUnitElementOfProduct(List<GroupDefinition> defs) {
        Element[] result = new Element[defs.size()];
        for(int i =0; i< defs.size(); i++){
            result[i] = defs.get(i).getMultiplicator().getUnit();
        }
        return result;
    }
}
