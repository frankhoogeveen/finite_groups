/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group_catalogue;

import java.util.logging.Logger;
import nl.fh.group_definition_factory.GroupDefinitionFactory;

/**
 *
 * @author frank
 */
public class AllGroupCatalog extends GroupCatalog {
    private static final Logger LOGGER = Logger.getLogger(AllGroupCatalog.class.getSimpleName());
    private final GroupDefinitionFactory factory;
    
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
        this.factory = new GroupDefinitionFactory();
        
        /* order one */
        super.add(factory.getCyclicGroup(1));

        /* order two */
        super.add(factory.getCyclicGroup(2));
        
        /*order three */
        super.add(factory.getCyclicGroup(3));
        
        /*order four*/
        super.add(factory.getCyclicGroup(4));
        super.add(factory.getAbeleanGroup(new int[]{2,2}));
        
        /* order five */
        super.add(factory.getCyclicGroup(5));
        
        /* order six */
        super.add(factory.getCyclicGroup(6));
        //TODO add groups of this order
         
    }
}
