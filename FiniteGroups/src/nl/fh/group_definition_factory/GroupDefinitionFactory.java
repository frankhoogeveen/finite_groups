/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group_definition_factory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.GroupDefinition;
import nl.fh.group.Multiplicator;
import nl.fh.group_catalogue.AllGroupCatalog;
import nl.fh.group_def_cyclic.CyclicElement;
import nl.fh.group_def_cyclic.CyclicMultiplicator;
import nl.fh.group_def_product.GroupProduct;
import nl.fh.group_info_table.GroupInfoTableException;

/**
 * Factory object for groups
 * 
 * @author frank
 */
public class GroupDefinitionFactory {
    private static final Logger LOGGER = Logger.getLogger(AllGroupCatalog.class.getSimpleName());
    
    
     /**
     * 
     * @param n the order
     * @return the definition of the cyclic group of order n 
     */
    public GroupDefinition getCyclicGroup(int n) {
        if(n < 1){
            String mess = "cannot not define cyclic group of order " + Integer.toString(n);
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
    * @param orders the orders of the cyclic factors
    * @return the definition of the product of several cyclic groups
    */
    public GroupDefinition getAbeleanGroup(int[] orders) {
        List<GroupDefinition> defs = new ArrayList<GroupDefinition>();
        
        for(int i = 0; i < orders.length; i++){
            defs.add(getCyclicGroup(orders[i]));
        }

        return GroupProduct.of(defs);

    }
    
}
