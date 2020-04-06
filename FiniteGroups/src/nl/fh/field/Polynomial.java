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

import java.util.Arrays;
import nl.fh.number.Mod;
import nl.fh.number.Prime;

/**
 *  Represents a polynomial modulo p
 *
 * @author frank
 */
public class Polynomial {
    private static Polynomial unit;
    private static Polynomial zero;
    
    private int p;
    private int[] a;
    
    public Polynomial(int[] a, int p){
        if(!Prime.isPrime(p)){
            throw new IllegalArgumentException("Polynomial has to be modulo a prime");
        }
        
        this.a = a;
        this.p = p;
        
        normalize();
    }

    /**
     * bring the fields of this object to canonical form
     */
    private void normalize() {
        // bring all coefficients in the range 0<= a[i] < p
        for(int i = 0; i < a.length; i++){
            a[i] = a[i] % p;
            if( a[i] < 0){
                a[i] += p;
            }
        }
        
        // get rid of zero leading coefficients
        if(a[a.length-1] == 0){
            int leading = a.length-1;
            while((a[leading] == 0) && leading > 0){
                leading -= 1;
            }
        a = Arrays.copyOfRange(a, 0, leading+1);   
        }
    }    
    
    /**
     * 
     * @param other polynomial, should have the same modulus as this
     * @ return the sum of this and the other polynomial
     */
    public Polynomial plus(Polynomial other){
        if(this.p != other.p){
            throw new IllegalArgumentException("mod has to be the same to add two polynomials");
        }
        
        int size = Integer.max(this.a.length, other.a.length);
        int[] sum = new int[size];
        
        for(int i = 0; i < this.a.length; i++){
            sum[i] += this.a[i];
        }
        
        for(int i = 0; i < other.a.length; i++){
            sum[i] += other.a[i];
        }
        
        return new Polynomial(sum, this.p);
    }
     /**
     * 
     * @param other polynomial, should have the same modulus as this
     * @ return the product of this and the other polynomial
     */   
    public Polynomial times(Polynomial other){
        if(this.p != other.p){
            throw new IllegalArgumentException("mod has to be the same to multiply two polynomials");
        }
        
        int size = this.a.length + other.a.length - 1;
        int[] product = new int[size];
        
        for(int i = 0; i < this.a.length; i++){
            for(int j = 0; j < other.a.length; j++){
                product[i+j] += this.a[i] * other.a[j];
            }
        }
        
        return new Polynomial(product, this.p);
    }
    
    /**
     *
     * @param n
     * @return n times this polynomial
     */
    public Polynomial times(int n){
        int[] mult = new int[this.a.length];
        for(int i = 0; i < a.length; i++){
            mult[i] = n * a[i];
        }
        
        return new Polynomial(mult, this.p);
    }
    
    /**
     * 
     * @param other
     * @return this modulo other 
     */
    public Polynomial mod(Polynomial other){
        if(this.p != other.p){
            throw new IllegalArgumentException("both polynomials have to have the same index");
        }
        
        if(this.a.length < other.a.length){
            return this;
        }
        
        int exp = this.a.length - other.a.length;
        int[] factor = new int[exp+1];
        factor[exp] = - this.a[this.a.length-1] * Mod.inverse(other.a[other.a.length-1], p);
        
        Polynomial next = this.plus(other.times(new Polynomial(factor, this.p)));
        
        return next.mod(other);
    }
   
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        String prefix = "";
        for(int i = a.length-1; i >=0; i--){
            sb.append(prefix);
            prefix = "+";
            
            sb.append(a[i]);
            if(i>0){
                sb.append("x");
            }
            
            if(i > 1){
                sb.append("^");
                sb.append(i);
            }
        }
        
        sb.append(" (mod ");
        sb.append(p);
        sb.append(")");
        
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.p;
        hash = 41 * hash + Arrays.hashCode(this.a);
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
        final Polynomial other = (Polynomial) obj;
        if (this.p != other.p) {
            return false;
        }
        if (!Arrays.equals(this.a, other.a)) {
            return false;
        }
        return true;
    }
}
