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
package nl.fh.field;

import java.util.Objects;
import nl.fh.number.IntNumber;
import nl.fh.number.Mod;

/**
 * represents an element of a finite field
 * with methods to define the arithmetic operations
 * 
 * 
 * @author frank
 */
public class FieldElement {

    private final Polynomial polynomial;
    private final Field field;
    
    /**
     * create an element of a finite field
     * 
     * @param poly
     * @param field 
     */
    public FieldElement(Polynomial poly, Field field){
        this.field = field;
        this.polynomial = poly.reduceModulo(field);
    }
    
    /**
     * create an element of the prime field of a finite field
     */
    public FieldElement(int n, Field field){
        this.field = field;
        this.polynomial = (new Polynomial(n)).reduceModulo(field);
    }
    
    public FieldElement plus(FieldElement other){
        checkCompatibility(other);
        
        Polynomial sum = this.polynomial.plus(other.polynomial);
        return new FieldElement(sum, field);
    }
    
    public FieldElement minus(FieldElement other){
        checkCompatibility(other);    
        
        Polynomial diff = this.polynomial.minus(other.polynomial);
        return new FieldElement(diff, field);
    }
    
    public FieldElement negative(){
        Polynomial neg = this.polynomial.times(-1);
        return new FieldElement(neg, field);
    }
    
    public FieldElement times(FieldElement other){
        checkCompatibility(other);
        
        Polynomial prod = this.polynomial.times(other.polynomial);
        return new FieldElement(prod, field);
    }
    
    public FieldElement times(int n){
        Polynomial prod = this.polynomial.times(n);
        return new FieldElement(prod, field);
    }
    
    public FieldElement power(int n){
        if(n < 0){
            return this.inverse().power(-n);
        }
        
        
        FieldElement result = new FieldElement(new Polynomial(new int[]{1}), this.field);
        FieldElement current = new FieldElement(this.polynomial, this.field);
        
        int nn = n;
        while(nn > 0){
            if(( nn & 1) ==1 ){
                result = result.times(current);
            }
            nn >>= 1;
            current = current.times(current);
        }
        
        return result;
    }
    
    public FieldElement inverse(){
        int p = this.field.getCharacteristic();
        int degree = this.field.getPolynomial().degree();
        int q = IntNumber.power(p, degree);
        
        return this.power(q-2);
    }
    
    public FieldElement divided(FieldElement other){
        checkCompatibility(other);
        if(other.isZero()){
            throw new IllegalArgumentException("cannot divide by zero");
        }
        
        return this.times(other.inverse());
    }
    
    public FieldElement divided(int n){
        if(n == 0){
            throw new IllegalArgumentException("cannot divide by zero");
        }
        
        int nInv = Mod.inverse(n, this.field.getCharacteristic());
        return this.times(nInv);
    }
 
    public Field getField(){
        return this.field;
    }
    
    public boolean isZero() {
        return this.polynomial.isZero();
    }    
    
    public boolean isInPrimeField(){
        return this.polynomial.degree() == 0;
    }
    
    private void checkCompatibility(FieldElement other) {
        if(!other.field.equals(this.field)){
            throw new IllegalArgumentException("cannot do operation with incompatible field elements");
        }
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        sb.append(this.polynomial);
        sb.append(" (");
        sb.append(this.field);
        sb.append(")");
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.polynomial);
        hash = 19 * hash + Objects.hashCode(this.field);
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
        final FieldElement other = (FieldElement) obj;
        if (!Objects.equals(this.polynomial, other.polynomial)) {
            return false;
        }
        if (!Objects.equals(this.field, other.field)) {
            return false;
        }
        return true;
    }
}
