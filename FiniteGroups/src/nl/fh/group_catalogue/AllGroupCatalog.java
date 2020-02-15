/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group_catalogue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fh.group.Element;
import nl.fh.group.GroupDefinition;
import nl.fh.group.Multiplicator;
import nl.fh.group_def_cyclic.CyclicElement;
import nl.fh.group_def_cyclic.CyclicMultiplicator;
import nl.fh.group_def_product.GroupProduct;

/**
 *
 * @author frank
 */
public class AllGroupCatalog extends GroupCatalog {
    private static final Logger LOGGER = Logger.getLogger(AllGroupCatalog.class.getSimpleName());
    
    /**
     * main method that prints the entire catalog
     * @param args 
     */
    public static void main(String[] args){
  
        GroupCatalog cat = new AllGroupCatalog();
        System.out.println(cat);
    }
    
    
    public AllGroupCatalog(){
        super();

        /* order one */
        super.add(cyclicGroup(1));

        /* order two */
        super.add(cyclicGroup(2));
        
        /*order three */
        super.add(cyclicGroup(3));
        
        /*order four*/
        super.add(cyclicGroup(4));
        super.add(abeleanGroup(new int[]{2,2}));
        
        /* order fve */
        super.add(cyclicGroup(5));
        
        /* order six */
        super.add(cyclicGroup(6));
        //TODO add groups of this order
         
    }
    
     /**
     * 
     * @param n
     * @return the definition of the cyclic group of order n 
     */
    private GroupDefinition cyclicGroup(int n){
        if(n < 1){
            String mess = "could not define cyclic group of order " + Integer.toString(n);
            LOGGER.log(Level.SEVERE, mess);
            throw new IllegalArgumentException(mess);
        }
        
        Set<Element> generators = new HashSet<Element>();
        generators.add(CyclicElement.generatorOfOrder(n));
        
        Multiplicator multiplication = new CyclicMultiplicator(n);
        
        String name = "C" + Integer.toString(n);
        
        return new GroupDefinition(name, generators, multiplication);
        
    }

    /**
    * @return the product of several cyclic groups
    */
    private GroupDefinition abeleanGroup(int[] orders) {
        List<GroupDefinition> defs = new ArrayList<GroupDefinition>();
        
        for(int i = 0; i < orders.length; i++){
            defs.add(cyclicGroup(orders[i]));
        }
                
        return GroupProduct.of(defs);
    }
}
