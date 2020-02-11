/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group_def_substitutions;

import java.util.HashMap;
import java.util.Map;
import nl.fh.group.Element;
import nl.fh.group.Multiplicator;

/**
 *
 * Multiplies strings by concatenating them and applying relations
 * 
 * @author frank
 */
public class StringMultiplicator implements Multiplicator<StringElement> {
    
    private final Map<String, String> substitutions = new HashMap<String, String>();
    private final int MAX_ITERATIONS = 10000;
    private static Element unit = new StringElement("");
    
    public void addSubstitution(StringSubstitution substitution) {
         this.substitutions.put(substitution.getFrom(), substitution.getTo());
    }
    
    @Override
    public StringElement getProduct(StringElement factor1, StringElement factor2) throws TooManyIterationsException {
        String result = factor1.characters + factor2.characters;
        String oldResult;
        int iterations = 0;
        
        do{
          oldResult = result;
          result = applyAllSubstitutions(oldResult);
          if(iterations++ > MAX_ITERATIONS){
              throw new TooManyIterationsException(factor1.toString() + "  " + factor2.toString());
          }
        } while(!result.equals(oldResult));
        
        return new StringElement(result);
    }

    private String applyAllSubstitutions(String s) {
        String result = s;
        for(String key : this.substitutions.keySet()){
            result = result.replace(key, this.substitutions.get(key));
        }
        return result;
    }

    @Override
    public Element getUnit() {
        return StringMultiplicator.unit;
    }
}
