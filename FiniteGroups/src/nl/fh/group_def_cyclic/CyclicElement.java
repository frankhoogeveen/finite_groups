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

import nl.fh.group.Element;

/**
 *
 * @author frank
 */
public class CyclicElement implements Element {
    int order = -1;
    int number = 0;

    /**
     * note: the constructor has package private access
     */
    CyclicElement(){
        
    }
    
    /**
     * 
     * @param order
     * @return  a generator of the cyclic group 
     */
    public static Element generatorOfOrder(int order) {
        
        if(order < 1){
            throw new IllegalArgumentException();
        }
        
        CyclicElement result = new CyclicElement();
        
        if(order == 1){
            result.number = 0;
        } else {
            result.number = 1;
        }
        
        result.order = order;
        
        return result;
    }
    
    /**
     * 
     * @param order
     * @return the zero element mod order 
     */
    public static Element unitOfOrder(int order){
                if(order < 1){
            throw new IllegalArgumentException();
        }
        
        CyclicElement result = new CyclicElement();
        result.number = 0;
        result.order = order;
        
        return result;
    }
    
        /**
     * 
     * @param k
     * @param order
     * @return the k-th element mod order 
     */
    public static Element create(int k, int order){
        if(order < 1){
            throw new IllegalArgumentException();
        }
        
        CyclicElement result = new CyclicElement();
        
        result.number = k % order;
        if(result.number < 0){
            result.number += order;
        }
        
        result.order = order;
        
        return result;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(number));
        sb.append("mod");
        sb.append(Integer.toString(order));
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.order;
        hash = 59 * hash + this.number;
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
        final CyclicElement other = (CyclicElement) obj;
        if (this.order != other.order) {
            return false;
        }
        if (this.number != other.number) {
            return false;
        }
        return true;
    }
    
}
