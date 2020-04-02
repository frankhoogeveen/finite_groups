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
package nl.fh.group_def_semidirect_product;

import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.Multiplicator;
import nl.fh.group_calculators.GroupProperty;
import nl.fh.group_def_automorphism.Automorphism;
import nl.fh.homomorphism.GroupHomomorphism;

/**
 *
 * @author frank
 */
public class SemiDirectProductMultiplicator implements Multiplicator<SemiDirectProductElement>{
    
    private final GroupHomomorphism HtoAutN;
    private final Multiplicator mult;

    /**
     * Multiplicator for the semi direct product.
     * 
     * (n1, h1) (n2, h2) -> (n1.phi(h1)(n2), h1.h2)
     * 
     *  
     * @param G the group containing N (as normal) and H
     * @param HtoAutN the map H -> Aut(N)
     */
    public SemiDirectProductMultiplicator(Group G, GroupHomomorphism HtoAutN){
        
        Multiplicator mult = null;
        
        try {
            mult = (Multiplicator) G.getProperty(GroupProperty.MultiplicationTable);
        } catch (EvaluationException ex) {
            String mess = "could not find multiplication table";
            Logger.getLogger(SemiDirectProductMultiplicator.class.getName()).log(Level.SEVERE, mess, ex);
            System.exit(-1);
        }
        
        this.mult = mult;
        this.HtoAutN = HtoAutN;
    }
    
    
    @Override
    public SemiDirectProductElement getProduct(SemiDirectProductElement factor1, SemiDirectProductElement factor2) throws EvaluationException {
        Element n1 = factor1.elementN;
        Element h1 = factor1.elementH;
        
        Element n2 = factor2.elementN;
        Element h2 = factor2.elementH;
        
        Automorphism phi = (Automorphism) HtoAutN.applyTo(h1);
        
        Element h12 = this.mult.getProduct(h1, h2);
        Element n12 = this.mult.getProduct(n1,phi.applyTo(n2));
        
        return new SemiDirectProductElement(n12, h12);
    }
}
