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
package nl.fh.group_def_product;

import java.util.Arrays;
import nl.fh.group.Element;

/**
 *
 * @author frank
 */
class ProductElement implements Element {

    private final Element[] factors;

    ProductElement(Element[] factors) {
        this.factors = factors;
    }
    
    @Override
    public String toString(){
        if(factors.length == 0) {
            return "1";
        }
              
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(factors[0].toString());
        for(int i = 1; i < factors.length; i++){
            sb.append(" ,");
            sb.append(factors[i].toString());
        }
        sb.append(")");
        
        return sb.toString();
    }

    int getDimension() {
        return factors.length;
    }
    
    Element get(int i){
        return this.factors[i];
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Arrays.deepHashCode(this.factors);
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
        final ProductElement other = (ProductElement) obj;
        if (!Arrays.deepEquals(this.factors, other.factors)) {
            return false;
        }
        return true;
    }
}
