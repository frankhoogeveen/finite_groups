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
package nl.fh.group_definition_factory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.GroupException;
import nl.fh.group.Multiplicator;
import nl.fh.group_catalogue.SmallGroupCatalog;
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
public class GroupFactory {
    private static final Logger LOGGER = Logger.getLogger(SmallGroupCatalog.class.getSimpleName());
    
    
     /**
     * 
     * @param n the order
     * @return the result of the cyclic group of order n 
     */
    public Group getCyclicGroup(int n) {
        if(n < 1){
            String mess = "cannot not define cyclic group of order " + Integer.toString(n);
            LOGGER.log(Level.SEVERE, mess);
            throw new IllegalArgumentException(mess);
        }
        
        Set<Element> generators = new HashSet<Element>();
        generators.add(CyclicElement.generatorOfOrder(n));
        
        Multiplicator multiplication = new CyclicMultiplicator(n);
        
        String name = "C" + Integer.toString(n);
        
                
        Group group = null;
        try {
            group = new Group(name, generators, multiplication);
        } catch (GroupException ex) {
            Logger.getLogger(GroupFactory.class.getName()).log(Level.SEVERE, "could not create cyclic group", ex);
            System.exit(-1);
        }
        return group;
        
    }

    /**
    * @param orders the orders of the cyclic factors
    * @return the result of the product of several cyclic groups
    */
    public Group getAbeleanGroup(int[] orders)  {
        List<Group> defs = new ArrayList<Group>();
        
        for(int i = 0; i < orders.length; i++){
            defs.add(getCyclicGroup(orders[i]));
        }

       Group result = null;
        try {
            result =  GroupProduct.of(defs);
        } catch (EvaluationException | GroupException ex) {
            Logger.getLogger(GroupFactory.class.getName()).log(Level.SEVERE, "could not create abelean group", ex);
            System.exit(-1);
        } 
        return result;
    }
    
    /**
     * 
     * @param n
     * @return the result of the symmetric group of order n!
     */
    public Group getSymmetricGroup(int n) {
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
        
        Group result = null;
        try {
            result =  new Group(name, generators, multiplication);
        } catch (GroupException ex) {
            Logger.getLogger(GroupFactory.class.getName()).log(Level.SEVERE, "could not create symmetric group", ex);
            System.exit(-1);
        } 
        return result;
    }

    /**
     * 
     * @param n
     * @return the Alternating group A_n 
     */
    
    public Group getAlternatingGroup(int n){
        
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
        
        Group result = null;
        try {
            result =  new Group(name, generators, multiplication);
        } catch (GroupException ex) {
            Logger.getLogger(GroupFactory.class.getName()).log(Level.SEVERE, "could not create alternating group", ex);
            System.exit(-1);
        } 
        return result;
    }

    public Group getDihedralGroup(int n){
        if(n < 1){
            String mess = "cannot not define dihedral group of order 2*" + Integer.toString(n);
            LOGGER.log(Level.SEVERE, mess);
            throw new IllegalArgumentException(mess);
        }
        
        Set<Element> generators = new HashSet<Element>();
        generators.add(new StringElement("a"));
        generators.add(new StringElement("b"));
        
        StringMultiplicator multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution(repeat("a",n), ""));
        multiplication.addSubstitution(new StringSubstitution("bb", ""));
         multiplication.addSubstitution(new StringSubstitution("ba", repeat("a", n-1) + "b"));      
        String name = "D" + Integer.toString(n);
        Group result = null;
        try {
            result =  new Group(name, generators, multiplication);
        } catch (GroupException ex) {
            Logger.getLogger(GroupFactory.class.getName()).log(Level.SEVERE, "could not create dihedral group", ex);
            System.exit(-1);
        } 
        return result;
    }

    private String repeat(String a, int n) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            sb.append(a);
        }
        return sb.toString();
    }

    public Group getDicyclicGroup(int n) {
        if(n < 1){
            String mess = "cannot not define dicyclic group of order 4*" + Integer.toString(n);
            LOGGER.log(Level.SEVERE, mess);
            throw new IllegalArgumentException(mess);
        }
        
        Set<Element> generators = new HashSet<Element>();
        generators.add(new StringElement("a"));
        generators.add(new StringElement("b"));
        
        StringMultiplicator multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution(repeat("a",2*n), ""));
        multiplication.addSubstitution(new StringSubstitution("bb", repeat("a",n)));
         multiplication.addSubstitution(new StringSubstitution("ba", repeat("a", 2*n-1) + "b"));      
        String name = "Q" + Integer.toString(n);
        Group result = null;
        try {
            result =  new Group(name, generators, multiplication);
        } catch (GroupException ex) {
            Logger.getLogger(GroupFactory.class.getName()).log(Level.SEVERE, "could not create dicyclic group", ex);
            System.exit(-1);
        } 
        return result;
    }
    
}
