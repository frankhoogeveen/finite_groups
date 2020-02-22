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
import nl.fh.group.GroupDefinition;
import nl.fh.group.Multiplicator;
import nl.fh.group_catalogue.AllGroupCatalog;
import nl.fh.group_def_cyclic.CyclicElement;
import nl.fh.group_def_cyclic.CyclicMultiplicator;
import nl.fh.group_def_permutation.PermutationElement;
import nl.fh.group_def_permutation.PermutationMultiplicator;
import nl.fh.group_def_product.GroupProduct;
import nl.fh.group_def_substitutions.StringElement;
import nl.fh.group_def_substitutions.StringMultiplicator;
import nl.fh.group_def_substitutions.StringSubstitution;

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
    
    /**
     * 
     * @param n
     * @return the definition of the symmetric group of order n!
     */
    public GroupDefinition getSymmetricGroup(int n){
        if(n < 1){
            throw new IllegalArgumentException(" cannot define S_n for n < 1");
        }
        
        int[] cycle = new int[n];
        int[] flip = new int[n];
        for(int i = 0; i < n; i++){
            cycle[i] = i+1;
            flip[i] = i;
        }
        cycle[n-1] = 0;
        flip[0] = 1;
        flip[1] = 0;
        
        Set<Element> generators = new HashSet<Element>();
        generators.add(new PermutationElement(flip));
        generators.add(new PermutationElement(cycle));
        
        Multiplicator multiplication = new PermutationMultiplicator(n);
        
        String name = "S"+Integer.toString(n);
        
        GroupDefinition definition = new GroupDefinition(name, generators, multiplication);
        
        return definition;
    }

    /**
     * 
     * @param n
     * @return the Alternating group A_n 
     */
    
    public GroupDefinition getAlternatingGroup(int n) {
        
        if( n != 4){
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
        Set<Element> generators = new HashSet<Element>();
        generators.add(new StringElement("a"));
        generators.add(new StringElement("b"));
        generators.add(new StringElement("c"));        
        
        StringMultiplicator multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution("aa", ""));
        multiplication.addSubstitution(new StringSubstitution("bb", ""));
        multiplication.addSubstitution(new StringSubstitution("ccc", ""));
        multiplication.addSubstitution(new StringSubstitution("ba", "ab"));
        multiplication.addSubstitution(new StringSubstitution("ca", "bc"));
        multiplication.addSubstitution(new StringSubstitution("cb", "abc"));
        
        String name = "A4";
        
        GroupDefinition definition = new GroupDefinition(name, generators, multiplication);
        
        return definition;
    }
    
}
