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

import java.util.Collection;
import nl.fh.homomorphism_calculator.HomomorphismProperty;
import java.util.HashMap;
import java.util.HashSet;
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
import nl.fh.group_def_automorphism.Automorphism;
import nl.fh.group_def_automorphism.AutomorphismMultiplicator;

/**
 *
 * @author frank
 */
public class GroupHomomorphism extends PropertyCache<HomomorphismProperty>{

    /**
     * 
     * @param domain
     * @param codomain
     * @param coreMap this map is extended to a full map.
     */
    public GroupHomomorphism(Group domain, Group codomain, Map<Element, Element> coreMap) throws HomomorphismException {
        super();
        this.cache.put(HomomorphismProperty.Domain, domain);
        this.cache.put(HomomorphismProperty.Codomain, codomain);
        
        try {
            GroupTable table = (GroupTable) domain.getProperty(GroupProperty.MultiplicationTable);
            GroupTable cotable = (GroupTable) codomain.getProperty(GroupProperty.MultiplicationTable);
            this.cache.put(HomomorphismProperty.Map, createFullMap(domain, codomain, coreMap));
            
        } catch (EvaluationException ex) {
            String mess = "could not construct group homomorphism";
            Logger.getLogger(GroupHomomorphism.class.getName()).log(Level.SEVERE, mess, ex);
            throw new HomomorphismException(mess);
        }
    }
    
    /**
     * 
     * @param g an element of the domain
     * @return the element of the codomain to which g is mapped
     */
    public Element applyTo(Element g) throws EvaluationException{
        Map<Element, Element> map = (Map<Element, Element>)this.getProperty(HomomorphismProperty.Map);
        if(!map.containsKey(g)){
            throw new EvaluationException("cannot map: " + g.toString());
        }
        return map.get(g);
    }
    
    /**
     * 
     * @param coll
     * @return the image of the collection under the map, as a set
     * 
     * I.e. order is lost(for lists) and multiplicity is lost(for multisets)
     * @throws EvaluationException 
     */
    public Set<Element> applyTo(Collection<Element> coll) throws EvaluationException{
        Set<Element> result = new HashSet<Element>();
        for(Element g : coll){
            result.add(this.applyTo(g));
        }
        return result;
    }
    
    /**
     * 
     * @param morph
     * @return the composition of morph followed by this
     */
    public GroupHomomorphism after(GroupHomomorphism morph) throws EvaluationException{
        Group codomain1 = (Group) morph.getProperty(HomomorphismProperty.Codomain);
        Group domain1 = (Group) morph.getProperty(HomomorphismProperty.Domain);
        Group codomain2 = (Group) this.getProperty(HomomorphismProperty.Codomain);
        Group domain2 = (Group) this.getProperty(HomomorphismProperty.Domain);
            
        //make sure the domains of the two automorphisms match
        if(!codomain1.equals(domain2)){
            throw new EvaluationException("cannot multiply automorphisms with different domains");
        }
        
        Map<Element, Element> map1 = (Map<Element, Element>)morph.getProperty(HomomorphismProperty.Map);
        Map<Element, Element> map2 = (Map<Element, Element>)this.getProperty(HomomorphismProperty.Map);   
        
        // calculate the composite map
        Map<Element, Element> productMap = new HashMap<Element, Element>();
        for(Element g : domain1){
            productMap.put(g, map2.get(map1.get(g)));
        }
        
        try {
            return new GroupHomomorphism(domain1, codomain2, productMap);
        } catch (HomomorphismException ex) {
            String mess = "could not multiply homomorphisms";
            Logger.getLogger(AutomorphismMultiplicator.class.getName()).log(Level.SEVERE, mess, ex);
            throw new EvaluationException(mess);
        }
    }
    
    /**
     * 
     * @param morph
     * @return the composition of this followed by morph
     */
    public GroupHomomorphism before(GroupHomomorphism morph) throws EvaluationException{
        return morph.after(this);
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
    
    @Override
    public int hashCode() {
        try {
            int hash = 7;
            hash = 43 * hash + this.getProperty(HomomorphismProperty.Domain).hashCode();
            hash = 59 * hash + this.getProperty(HomomorphismProperty.Codomain).hashCode();
            hash = 61 * hash + this.getProperty(HomomorphismProperty.Map).hashCode();
            return hash;
        } catch (EvaluationException ex) {
            String mess = "cannot calculate hashCode() of GroupHomomorphism";
            Logger.getLogger(GroupHomomorphism.class.getName()).log(Level.SEVERE, mess, ex);
            throw new IllegalArgumentException(mess);
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GroupHomomorphism other = (GroupHomomorphism) obj;
        
        try {
            if (!this.getProperty(HomomorphismProperty.Domain).equals(other.getProperty(HomomorphismProperty.Domain))) {
                return false;
            }
            if (!this.getProperty(HomomorphismProperty.Codomain).equals(other.getProperty(HomomorphismProperty.Codomain))) {
                return false;
            }
            if (!this.getProperty(HomomorphismProperty.Map).equals(other.getProperty(HomomorphismProperty.Map))) {
                return false;
            }
        } catch (EvaluationException ex) {
            return false;
        }

        return true;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        try {

            sb.append(this.getProperty(HomomorphismProperty.Domain).toString());
            sb.append("=>");
            sb.append(this.getProperty(HomomorphismProperty.Codomain).toString());
            sb.append(": ");
            Map<Element, Element> map = (Map<Element, Element>) this.getProperty(HomomorphismProperty.Map);
            for(Element x : map.keySet()){
                sb.append(x.toString());
                sb.append("->");
                sb.append(map.get(x).toString());
                sb.append(" ");
            }

        } catch (EvaluationException ex) {
            String mess = "could not toString a GroupHomomorphism";
            Logger.getLogger(GroupHomomorphism.class.getName()).log(Level.SEVERE, mess, ex);
            return mess;
        }
        return sb.toString();
    }
}
