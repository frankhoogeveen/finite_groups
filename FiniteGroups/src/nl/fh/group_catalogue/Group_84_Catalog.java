/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group_catalogue;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import nl.fh.group.GroupDefinition;
import nl.fh.group_def_product.GroupProduct;
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
