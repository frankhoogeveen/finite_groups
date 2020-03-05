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
package nl.fh.group_def_cyclic;

import nl.fh.group.Multiplicator;

/**
 *
 * @author frank
 */
public class CyclicMultiplicator implements Multiplicator<CyclicElement> {

    private final int order;
    private CyclicElement unit;

    public CyclicMultiplicator(int order) {
        this.order = order;
    }

    @Override
    public CyclicElement getProduct(CyclicElement factor1, CyclicElement factor2){
        if((factor1.order != this.order) || (factor2.order != this.order)){
            throw new IllegalArgumentException();
        }
        
        CyclicElement result = new CyclicElement();
        result.order = factor1.order;
        result.number = (factor1.number + factor2.number) % result.order;
        
        return result;
    }    
}
