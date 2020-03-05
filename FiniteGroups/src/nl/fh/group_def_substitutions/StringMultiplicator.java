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
package nl.fh.group_def_substitutions;

import nl.fh.group.GroupException;
import java.util.HashMap;
import java.util.Map;
import nl.fh.calculator.EvaluationException;
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
    public StringElement getProduct(StringElement factor1, StringElement factor2) throws EvaluationException {
        String result = factor1.characters + factor2.characters;
        String oldResult;
        int iterations = 0;
        
        do{
          oldResult = result;
          result = applyAllSubstitutions(oldResult);
          if(iterations++ > MAX_ITERATIONS){
              throw new EvaluationException(factor1.toString() + "  " + factor2.toString());
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
}
