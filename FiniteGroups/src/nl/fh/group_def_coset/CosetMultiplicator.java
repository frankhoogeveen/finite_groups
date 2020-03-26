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

import java.util.Map;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
import nl.fh.group.Multiplicator;

/**
 *
 * @author frank
 */
public class CosetMultiplicator implements Multiplicator {

    // the multiplicator of the original group in which the cosets live
    private final Multiplicator multiplicator;
    
    // maps each element of a group to the coset around that element
    private final Map<Element, Element> factorMap;


    /**
     * 
     * @param factorMap maps
     */
    public CosetMultiplicator(Multiplicator multiplicator, Map<Element, Element> factorMap) {
         this.factorMap = factorMap;
         this.multiplicator = multiplicator;
    }

    @Override
    public Element getProduct(Element factor1, Element factor2) throws EvaluationException {
        CosetElement coset1 = (CosetElement) factor1;
        CosetElement coset2 = (CosetElement) factor2;
        
        Element g1 = coset1.getRepresentative();
        Element g2 = coset2.getRepresentative();
                
        Element product = this.multiplicator.getProduct(g1, g2);
        return this.factorMap.get(product);
    }
    
}
