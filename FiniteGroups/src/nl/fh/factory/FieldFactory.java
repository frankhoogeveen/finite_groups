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
package nl.fh.factory;

import java.util.HashMap;
import java.util.Map;
import nl.fh.field.Field;
import nl.fh.field.Polynomial;
import nl.fh.number.IntNumber;

/**
 *  This class defines fields. In particular it is the concern of this
 *  class to keep of which irreducible polynomial we are using to define which
 *  group.
 * 
 * @author frank
 */
public class FieldFactory {

    Map<Integer, Map<Integer, Polynomial>> irreduciblePolynomials;
    
    
    public FieldFactory(){
        this.irreduciblePolynomials = new HashMap<Integer,Map<Integer, Polynomial>>();
        
        this.irreduciblePolynomials.put(2, new HashMap<Integer, Polynomial>());
        this.irreduciblePolynomials.get(2).put(1, new Polynomial(new int[]{0,1}));   // TODO test if this works OK
        this.irreduciblePolynomials.get(2).put(2, new Polynomial(new int[]{1,1,1}));
        this.irreduciblePolynomials.get(2).put(3, new Polynomial(new int[]{1,1,0,1}));
        this.irreduciblePolynomials.get(2).put(4, new Polynomial(new int[]{1,1,0,0,1}));
        this.irreduciblePolynomials.get(2).put(5, new Polynomial(new int[]{1,0,1,0,0,1}));
        this.irreduciblePolynomials.get(2).put(6, new Polynomial(new int[]{1,1,0,0,0,0,1}));
        this.irreduciblePolynomials.get(2).put(7, new Polynomial(new int[]{1,1,0,0,0,0,0,1}));
        this.irreduciblePolynomials.get(2).put(8, new Polynomial(new int[]{1,1,0,1,1,0,0,0,1}));

        this.irreduciblePolynomials.put(3, new HashMap<Integer, Polynomial>());
        this.irreduciblePolynomials.get(3).put(1, new Polynomial(new int[]{0,1}));   // TODO test if this works OK
        this.irreduciblePolynomials.get(3).put(2, new Polynomial(new int[]{1,0,1}));
        this.irreduciblePolynomials.get(3).put(3, new Polynomial(new int[]{1,2,0,1}));
        this.irreduciblePolynomials.get(3).put(4, new Polynomial(new int[]{2,1,0,0,1}));
        this.irreduciblePolynomials.get(3).put(5, new Polynomial(new int[]{1,2,0,0,0,1}));  

        this.irreduciblePolynomials.put(5, new HashMap<Integer, Polynomial>());
        this.irreduciblePolynomials.get(5).put(1, new Polynomial(new int[]{0,1}));   // TODO test if this works OK
        this.irreduciblePolynomials.get(5).put(2, new Polynomial(new int[]{2,0,1}));
        this.irreduciblePolynomials.get(5).put(3, new Polynomial(new int[]{1,1,0,1}));
        this.irreduciblePolynomials.get(5).put(4, new Polynomial(new int[]{2,0,0,0,1}));      
        
        this.irreduciblePolynomials.put(7, new HashMap<Integer, Polynomial>());
        this.irreduciblePolynomials.get(7).put(1, new Polynomial(new int[]{0,1}));   // TODO test if this works OK
        this.irreduciblePolynomials.get(7).put(2, new Polynomial(new int[]{1,0,1}));    
        this.irreduciblePolynomials.get(7).put(2, new Polynomial(new int[]{2,0,0,1}));          
        
    }     
    
    
    public Field getField(int q) {
        if(!IntNumber.isPrimePower(q) || (q ==1)){
            throw new IllegalArgumentException("no field exists of order: " + Integer.toString(q));
        }
        
        Map<Integer, Integer> fac = IntNumber.factorize(q);
        
        int p = fac.keySet().iterator().next();
        int exp = fac.get(p);

        if(!this.irreduciblePolynomials.keySet().contains(p)){
            throw new UnsupportedOperationException("Prime Not supported yet."); 
        }
        
        if(!this.irreduciblePolynomials.get(p).keySet().contains(exp)){
            throw new UnsupportedOperationException("Exponent Not supported yet."); 
        }
        
        Polynomial poly = this.irreduciblePolynomials.get(p).get(exp);
        return new Field(p, poly);
    }

}
