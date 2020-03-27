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
package nl.fh.group_calculators;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fh.calculator.Calculator;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group_def_automorphism.Automorphism;
import nl.fh.homomorphism.HomomorphismException;

/**
 * calculates a map G-> Inn(G) that assigns to each g in G, the inner 
 * automorphism  x -> g.x.g^(-1)
 * 
 * @author frank
 */
public class InnerAutomorphismMapCalculator implements Calculator<Group> {
    
    @Override
    public Map<Element, Element> evaluate(Group group) throws EvaluationException {
        
        Map<Element, Map<Element,Element>> conjMap = (Map<Element, Map<Element,Element>>) group.getProperty(GroupProperty.ConjugationMap);
        
        Map<Element, Element> result  = new HashMap<Element, Element>();
        for(Element g : group){
            Automorphism  inn = null;
            try {
                inn = new Automorphism(group, conjMap.get(g));
            } catch (HomomorphismException ex) {
                String mess = "could not create inner automorphism";
                Logger.getLogger(InnerAutomorphismMapCalculator.class.getName()).log(Level.SEVERE, mess, ex);
                throw new EvaluationException(mess);
            }
            result.put(g, inn);
        }
        return result;
    }
}
