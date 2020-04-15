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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import nl.fh.number.IntNumber;
import nl.fh.number.Mod;

/**
 *  Represents a polynomial with integer coefficients
 * This should be an immutable object
 *
 * @author frank
 */
public class Polynomial {
    
    private int[] a;
    
    /**
     * 
     * create the polynomial 
     * 
     * a[0]*1 + a[1]*x + a[2]*x^2 +...+ a[n]*x^n
     * 
     * @param a 
     */
    public Polynomial(int[] a){
        
        this.a = Arrays.copyOf(a, a.length);
        normalize();
    }

    /**
     * create the constant polynomial 
     * 
     * a * 1
     * 
     * @param a 
     */
    public Polynomial(int a){
        this.a = new int[1];
        this.a[0] = a;
    }
    
    /**
     * bring the fields of this object to canonical form:
     * - leading coefficient non-zero unless the entire polynomial is zero
     * - zero is represented as {0}
     */
    private void normalize() {
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
     * @return the degree of this polynomial 
     */
    public int degree(){
        return a.length-1;
    }
    
    /**
     * 
     * @param other polynomial
     * @ return the sum of this and the other polynomial
     */
    public Polynomial plus(Polynomial other){
        
        int size = Integer.max(this.a.length, other.a.length);
        int[] sum = new int[size];
        
        for(int i = 0; i < this.a.length; i++){
            sum[i] += this.a[i];
        }
        
        for(int i = 0; i < other.a.length; i++){
            sum[i] += other.a[i];
        }
        
        return new Polynomial(sum);
    }
    
    /**
     * 
     * @param other polynomial
     * @ return the difference of this and the other polynomial
     */
    public Polynomial minus(Polynomial other){
        
        int size = Integer.max(this.a.length, other.a.length);
        int[] sum = new int[size];
        
        for(int i = 0; i < this.a.length; i++){
            sum[i] += this.a[i];
        }
        
        for(int i = 0; i < other.a.length; i++){
            sum[i] -= other.a[i];
        }
        
        return new Polynomial(sum);
    }    
     /**
     * 
     * @param other polynomial
     * @ return the product of this and the other polynomial
     */   
    public Polynomial times(Polynomial other){
        
        int size = this.a.length + other.a.length - 1;
        int[] product = new int[size];
        
        for(int i = 0; i < this.a.length; i++){
            for(int j = 0; j < other.a.length; j++){
                product[i+j] += this.a[i] * other.a[j];
            }
        }
        
        return new Polynomial(product);
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
        
        return new Polynomial(mult);
    }

    /**
     * 
     * @param p
     * @return this polynomial (modulo p) with all coefficients in the range 0<= a[i] < p
     * 
     */
    public Polynomial reduceModulo(int p){
        int[] aa = new int[this.a.length];
        for(int i = 0; i < a.length; i++){
            aa[i] = a[i] % p;
            if( aa[i] < 0){
                aa[i] += p;
            }
        }
        return new Polynomial(aa);
    }
    
    
    /**
     * 
     * @param other another polynomial
     * @param p prime number
     * @return the remainder of this when dividing by other, modulo p
     */
     public Polynomial reduceModulo(Polynomial other, int p){
         
        if(this.a.length < other.a.length){
            return this.reduceModulo(p);
        }
        
        int exp = this.a.length - other.a.length;
        int[] factor = new int[exp+1];
        factor[exp] = - this.a[this.a.length-1] * Mod.inverse(other.a[other.a.length-1], p);
        
        Polynomial next = this.plus(other.times(new Polynomial(factor))).reduceModulo(p);
        return next.reduceModulo(other, p);
     }
     
     /**
      * 
      * @return this polynomial modulo a field 
      */
    public Polynomial reduceModulo(Field f){
        return this.reduceModulo(f.getPolynomial(), f.getCharacteristic());
    }
    
    /**
     * 
     * @param other
     * @param p prime number
     * @return (this -/- reduceModulo(other, p))/other
     */
     public Polynomial quotientModulo(Polynomial other, int p){

        if(this.a.length < other.a.length){
            return new Polynomial(0);
        }
        
        int exp = this.a.length - other.a.length;
        int[] term = new int[exp+1];
        term[exp] = Mod.inverse(other.a[other.a.length-1], p) * this.a[this.a.length-1];
        Polynomial termP = new Polynomial(term);
        
        Polynomial next = this.minus(termP.times(other)).reduceModulo(p);
        
        return (termP.plus(next.quotientModulo(other, p))).reduceModulo(p);
     }
     
     /**
      * 
      * @param p
      * @return true if this cannot be written as the product of two polynomials
      * of lower degree, modulo p. False otherwise
      */
     public boolean isIrreducible(int p){
         if(this.degree() < 2){
             return true;
         }
         
         // maximum degree for one of the factors
         int n = (this.degree() + 1)/2; 
         for(int k = 1; k <= n; k++){
             for(Polynomial poly : listAllMonicOfDegree(k, p)){
                 if(this.reduceModulo(poly, p).isZero()){
                     return false;
                 }
             }
         }
         return true;
     }
     
    /**
     * 
     * @param k the degree of the polynomials
     * @param p the modulus
     * @return a list of all monic polynomials of degree k
     */ 
    public static List<Polynomial> listAllMonicOfDegree(int k, int p) {
         List<Polynomial> result = new ArrayList<Polynomial>();
         int[] arr = new int[k+1];
         arr[k] = 1;
         
         for(int i = 0; i < IntNumber.power(p, k); i++){
             result.add(new Polynomial(arr));
             increment(arr, p);
         }
         
         return result;
    }
     
    /**
     * 
     */
    public static void increment(int[] arr, int p){
        int current = 0;
        while(current < arr.length){
            arr[current] += 1;
            if(arr[current] <= p){
                return;
            }
            arr[current] = 0;
            current +=1;
        }
    }
    
     /**
     * 
     * @return  true if this polynomial is the constant zero
     */
    public boolean isZero() {
        return ((a.length == 1) && (a[0] == 0));
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
        
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        if (!Arrays.equals(this.a, other.a)) {
            return false;
        }
        return true;
    }
}
