/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group_product;

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
