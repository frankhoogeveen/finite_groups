/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import nl.fh.group_info_table.GroupInfoTableException;
import nl.fh.info_table.InfoTableException;

/**
 *
 * @author frank
 */
public class GroupElementsConstructor{

    static List<Element> construct(GroupDefinition def, int maxIter) throws InfoTableException, MultiplicatorException {
        Multiplicator multiplicator = def.getMultiplicator();     
        Set<Element> currentElements = new HashSet<Element>(def.getGenerators());
        
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
