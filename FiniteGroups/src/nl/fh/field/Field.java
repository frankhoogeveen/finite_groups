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
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fh.calculator.EvaluationException;
import nl.fh.field_calculators.FieldProperty;
import nl.fh.calculator.PropertyCache;
import nl.fh.number.IntNumber;
import nl.fh.number.Mod;
import nl.fh.number.Prime;

/**
 * Class representing a finite field. The elements of the finite field are 
 * polynomials in a single variable modulo a prime p
 * 
 * @author frank
 */
public class Field extends PropertyCache<FieldProperty>{

    private Polynomial poly;
    private int p;
    
    /**
     * 
     * @param p    prime number
     * @param poly irreducible polynomial modulo p
     */
    public Field(int p, Polynomial poly){
        if(!Prime.isPrime(p)){
            throw new IllegalArgumentException("p needs to be prime");
        }
        
        if(!poly.isIrreducible(p)){
            throw new IllegalArgumentException("polynomial needs to be irreducible");
        }

        this.poly = poly;
        this.p = p;
         
        int q = IntNumber.power(p, poly.degree());
        this.cache.put(FieldProperty.NAME, "F_" + Integer.toString(q));
        this.cache.put(FieldProperty.ORDER, q);
        this.cache.put(FieldProperty.CHARACTERISTIC, p);
        this.cache.put(FieldProperty.POLYNOMIAL, poly);
    }

    /**
     * 
     * @param p1
     * @param p2
     * @return the sum of two polynomials as an element of this field
     */
    public Polynomial getSum(Polynomial p1, Polynomial p2){
        return p1.plus(p2).reduceModulo(this);
    }
    
     /**
     * 
     * @param p1
     * @param p2
     * @return the sum of two polynomials as an element of this field
     */
    public Polynomial getDifference(Polynomial p1, Polynomial p2){
        return p1.minus(p2).reduceModulo(this);
    }
    
    /**
     * 
     * @param p1
     * @param p2
     * @return the product of two polynomials as an element of this field
     */
    public Polynomial getProduct(Polynomial p1, Polynomial p2){
        return p1.times(p2).reduceModulo(this);
    }
    
    /**
     * 
     * @param p1 a polynomial
     * @param s a scalar
     * @return the product of a polynomial and a scalar
     */
    public Polynomial getProduct(int s, Polynomial p1){
        return p1.times(s).reduceModulo(this);
    }   
    
    /**
     * 
     * @param p1
     * @param p2
     * @return the quotient of two polynomials as an element of this field
     */
    public Polynomial getQuotient(Polynomial p1, Polynomial p2){
        return p1.quotientModulo(p2, p).reduceModulo(this);
    }
    
    /**
     * 
     * @param p1
     * @param s  a scalar
     * @return the quotient of p1/s
     */
    public Polynomial getQuotient(Polynomial p1, int s){
        int sInverse = Mod.inverse(s, p);
        return getProduct(sInverse, p1);
    }    

    /**
     * 
     * @return the polynomial used to define this field
     */
    public Polynomial getPolynomial() {
        return poly;
    }

    /**
     * 
     * @return the characteristic of this field
     */
    public int getCharacteristic() {
        return p;
    }
    
    @Override
    public String toString(){
        
        try {
            StringBuilder sb = new StringBuilder();
            sb.append((String) this.getProperty(FieldProperty.NAME));
            sb.append("/");
            sb.append(this.poly);
            return sb.toString();
        } catch (EvaluationException ex) {
            String mess = "field with no name";
            Logger.getLogger(Field.class.getName()).log(Level.SEVERE, mess, ex);
            return mess;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.poly);
        hash = 37 * hash + this.p;
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
        final Field other = (Field) obj;
        if (this.p != other.p) {
            return false;
        }
        if (!Objects.equals(this.poly, other.poly)) {
            return false;
        }
        return true;
    }
}
