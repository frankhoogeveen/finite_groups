/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group_def_product;

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
