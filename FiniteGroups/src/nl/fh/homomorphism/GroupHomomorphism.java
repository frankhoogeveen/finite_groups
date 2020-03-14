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
package nl.fh.homomorphism;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fh.calculator.EvaluationException;
import nl.fh.calculator.PropertyCache;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.Multiplicator;
import nl.fh.group_calculators.GroupProperty;
import nl.fh.group_calculators.GroupTable;

/**
 *
 * @author frank
 */
public class GroupHomomorphism extends PropertyCache<MorphismProperty>{
    
    private Group domain;
    private Group codomain;

    /**
     * 
     * @param domain
     * @param codomain
     * @param coreMap this map is extended to a full map.
     */
    public GroupHomomorphism(Group domain, Group codomain, Map<Element, Element> coreMap) throws HomomorphismException {
        super();
        this.cache.put(MorphismProperty.Domain, domain);
        this.cache.put(MorphismProperty.Codomain, codomain);
        
        try {
            GroupTable table = (GroupTable) domain.getProperty(GroupProperty.MultiplicationTable);
            GroupTable cotable = (GroupTable) codomain.getProperty(GroupProperty.MultiplicationTable);
            this.cache.put(MorphismProperty.Map, createFullMap(domain, codomain, coreMap));
            
        } catch (EvaluationException ex) {
            String mess = "could not construct group homomorphism";
            Logger.getLogger(GroupHomomorphism.class.getName()).log(Level.SEVERE, mess, ex);
            throw new HomomorphismException(mess);
        }
    }

    /**
     * 
     * @param domain
     * @param codomain
     * @param coreMap
     * @return the coreMap extended to the entire group
     * @throws EvaluationException
     * @throws HomomorphismException if the core map does not define a homomorphism 
     */
    private Map<Element, Element> createFullMap(Group domain, Group codomain, Map<Element, Element> coreMap) throws EvaluationException, HomomorphismException {
        Multiplicator mult = (Multiplicator) domain.getProperty(GroupProperty.MultiplicationTable);
        Multiplicator comult = (Multiplicator) codomain.getProperty(GroupProperty.MultiplicationTable);
        Set<Element> domainElements = (Set<Element>) domain.getProperty(GroupProperty.Elements);
        
        Element unit = (Element) domain.getProperty(GroupProperty.UnitElement);
        Element counit = (Element) codomain.getProperty(GroupProperty.UnitElement);
        
        Map<Element, Element> result = new HashMap<Element, Element>();
        result.put(unit, counit);

        while(!result.keySet().containsAll(domainElements)){
            Map<Element, Element> result2 = new HashMap<Element, Element>(result);
            
            for(Element g : coreMap.keySet()){
                Element valG = coreMap.get(g);
                for(Element h : result.keySet()){
                    Element valH = result.get(h);
                    result2.put(mult.getProduct(g, h), comult.getProduct(valG, valH));
                }
            }
            
            result = result2;
        }
        
        check(result, mult, comult);
        return result;
    }

    /**
     * 
     * @param result
     * @param mult
     * @param comult
     * @throws EvaluationException
     * @throws HomomorphismException if the result is not a homomorphism 
     */
    private void check(Map<Element, Element> result, Multiplicator mult, Multiplicator comult) throws EvaluationException, HomomorphismException {
        for(Element g : result.keySet()){
            for(Element h : result.keySet()){
                Element x = result.get(mult.getProduct(g,h));
                Element y = comult.getProduct(result.get(g), result.get(h));
                if(!x.equals(y)){
                    throw new HomomorphismException("homomorphism could not be constructed");
                }
            }
        }
    }
}
