/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toBinaryString(number));
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
