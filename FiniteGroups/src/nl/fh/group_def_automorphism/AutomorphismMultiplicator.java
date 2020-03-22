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
package nl.fh.group_def_automorphism;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.Multiplicator;
import nl.fh.homomorphism.HomomorphismException;
import nl.fh.homomorphism_calculator.HomomorphismProperty;

/**
 *
 * @author frank
 */
public class AutomorphismMultiplicator implements Multiplicator<Automorphism> {

    @Override
    public Automorphism getProduct(Automorphism aut1, Automorphism aut2) throws EvaluationException {
        Group domain1 = (Group) aut1.getProperty(HomomorphismProperty.Domain);
        Group domain2 = (Group) aut2.getProperty(HomomorphismProperty.Domain);
        
        Map<Element, Element> map1 = (Map<Element, Element>)aut1.getProperty(HomomorphismProperty.Map);
        Map<Element, Element> map2 = (Map<Element, Element>)aut2.getProperty(HomomorphismProperty.Map);       
        
        //make sure the domains of the two automorphisms match
        if(!domain1.equals(domain2)){
            throw new EvaluationException("cannot multiply automorphisms with different domains");
        }
        
        
        // calculate the composite map
        Map<Element, Element> productMap = new HashMap<Element, Element>();
        for(Element g : domain1){
            productMap.put(g, map2.get(map1.get(g)));
        }
        
        try {
            return new Automorphism(domain1, productMap);
        } catch (HomomorphismException ex) {
            String mess = "could not multiply automorphisms";
            Logger.getLogger(AutomorphismMultiplicator.class.getName()).log(Level.SEVERE, mess, ex);
            throw new EvaluationException(mess);
        }
        
    }
    
}
