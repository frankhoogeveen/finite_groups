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
    private final Multiplicator<Element> multN;
    private final Multiplicator<Element> multH;

    /**
     * Multiplicator for the semi direct product.
     * 
     * (n1, h1) (n2, h2) -> (n1.phi(h1)(n2), h1.h2)
     * 
     *  
     * @param G the group containing N (as normal) and H
     * @param HtoAutN the map H -> Aut(N)
     */
    public SemiDirectProductMultiplicator(Group N, Group H, GroupHomomorphism HtoAutN) throws EvaluationException{
        
        this.multN = (Multiplicator<Element>) N.getProperty(GroupProperty.MultiplicationTable);
        this.multH = (Multiplicator<Element>) H.getProperty(GroupProperty.MultiplicationTable);
        this.HtoAutN = HtoAutN;
    }
    
    
    @Override
    public SemiDirectProductElement getProduct(SemiDirectProductElement factor1, SemiDirectProductElement factor2) throws EvaluationException {
        Element n1 = factor1.elementN;
        Element h1 = factor1.elementH;
        
        Element n2 = factor2.elementN;
        Element h2 = factor2.elementH;
        
        Automorphism phi = (Automorphism) HtoAutN.applyTo(h1);
        
        Element h12 = this.multH.getProduct(h1, h2);
        Element n12 = this.multN.getProduct(n1,phi.applyTo(n2));
        
        return new SemiDirectProductElement(h12, n12);
    }
}
