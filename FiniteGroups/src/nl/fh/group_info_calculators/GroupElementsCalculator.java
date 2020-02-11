/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group_info_calculators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import nl.fh.group.Element;
import nl.fh.group.GroupDefinition;
import nl.fh.group_info.GroupInfoConstructionException;
import nl.fh.group.Multiplicator;
import nl.fh.group_info.GroupInfoTable;

/**
 *
 * @author frank
 */
public class GroupElementsCalculator{
    private final int maxIter;
    
    /**
     * 
     * @param maxIter the maximum number of iterations made to 
     * identify all elements
     */
    public GroupElementsCalculator(int maxIter) {
        this.maxIter = maxIter;
    }

    /**
     * calculate the set of all elements from the group info table
     * 
     * @param info
     * @return a list of elements
     * @throws GroupInfoConstructionException
     */    
    public List<Element> calculate(GroupInfoTable info) throws GroupInfoConstructionException {
        GroupDefinition def = info.getDefinition();
        Multiplicator multiplicator = def.getMultiplicator();     
        Set<Element> currentElements = new HashSet<Element>(def.getGenerators());
        
        int startSize;
        int iterations = 0;
        
        do{
          if(iterations++ > this.maxIter){
                stopIterations();
          }
        
          startSize = currentElements.size();
          
          Set<Element> products = makeAllProducts(currentElements, multiplicator);
          currentElements.addAll(products);
          
        } while(currentElements.size() > startSize);
        
        return new ArrayList<Element>(currentElements);
    }

    private Set<Element> makeAllProducts(Set<Element> currentElements, Multiplicator multiplicator) throws GroupInfoConstructionException {
        Set<Element> products = new HashSet<Element>();
        
        for(Element g1 : currentElements){
            for(Element g2 : currentElements){
                products.add(multiplicator.getProduct(g1, g2));
            }
        }
        
        return products;
    }

    private void stopIterations() throws GroupInfoConstructionException {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getCanonicalName());
        sb.append(" hit ");
        sb.append(Integer.toString(this.maxIter));
        sb.append(" iterations when looking for elements");
        
        throw new GroupInfoConstructionException(sb.toString());
    }
}
