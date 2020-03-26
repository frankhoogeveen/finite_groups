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
package nl.fh.group_def_coset;

import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group_calculators.GroupProperty;
import nl.fh.homomorphism.GroupHomomorphism;
import nl.fh.homomorphism_calculator.HomomorphismProperty;

/**
 *
 * @author frank
 */
public class CosetElement implements Element {

    private final Set<Element> coset;
    private final GroupHomomorphism morphism;
    private Element representative;
    private String postFix;

    public CosetElement(Set<Element> set, GroupHomomorphism morph) {
        this.coset = set;
        this.morphism = morph;
        
        if(set.isEmpty()){
            throw new IllegalArgumentException("cannot define an empty coset");
        }
        this.representative = set.iterator().next();
        
        try {
            Group groupH = (Group) morph.getProperty(HomomorphismProperty.Domain);
            this.postFix = " mod " +  (String)groupH.getProperty(GroupProperty.Name);
        } catch (EvaluationException ex) {
            String mess = "could not create name of CosetElement";
            Logger.getLogger(CosetElement.class.getName()).log(Level.SEVERE, mess, ex);
            this.postFix = mess;
        }
        
    }
    
    /**
     * 
     * @return a representative element of the coset
     */
    public Element getRepresentative(){
        return this.representative;
    }
    
    @Override
    public String toString(){
        return representative.toString() + this.postFix;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.coset);
        hash = 53 * hash + Objects.hashCode(this.morphism);
        return hash;
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
        final CosetElement other = (CosetElement) obj;
        if (!Objects.equals(this.coset, other.coset)) {
            return false;
        }
        if (!Objects.equals(this.morphism, other.morphism)) {
            return false;
        }
        return true;
    }
}
