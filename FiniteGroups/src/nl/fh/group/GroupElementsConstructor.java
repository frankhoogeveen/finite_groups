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
package nl.fh.group;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import nl.fh.group_info_table.GroupInfoTableException;
import nl.fh.info_table.InfoTableException;

/**
 * Given a group definition, this object calculates the set of all group elements
 *  
 * @author frank
 */
public class GroupElementsConstructor{

    static List<Element> construct(GroupDefinition def, int maxIter) throws InfoTableException, MultiplicatorException {
        Multiplicator multiplicator = def.getMultiplicator();     
        
        Set<Element> currentElements = new HashSet<Element>();
        currentElements.add(def.getMultiplicator().getUnit());
        currentElements.addAll(def.getGenerators());
        
        int startSize;
        int iterations = 0;
        
        do{
          if(iterations++ > maxIter){
                stopIterations(maxIter);
          }
        
          startSize = currentElements.size();
          
          Set<Element> products = makeAllProducts(currentElements, multiplicator);
          currentElements.addAll(products);
          
        } while(currentElements.size() > startSize);
        
        return new ArrayList<Element>(currentElements);
    }


    private static Set<Element> makeAllProducts(Set<Element> currentElements, Multiplicator multiplicator) throws InfoTableException, MultiplicatorException {
        Set<Element> products = new HashSet<Element>();
        
        for(Element g1 : currentElements){
            for(Element g2 : currentElements){
                products.add(multiplicator.getProduct(g1, g2));
            }
        }
        
        return products;
    }

    private static void stopIterations(int maxIter) throws GroupInfoTableException {
        StringBuilder sb = new StringBuilder();
        sb.append("The group element constructor hit ");
        sb.append(Integer.toString(maxIter));
        sb.append(" iterations when looking for elements");
        
        throw new GroupInfoTableException(sb.toString());
    }
}
