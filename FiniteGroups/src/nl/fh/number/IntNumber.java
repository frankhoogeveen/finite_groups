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
package nl.fh.number;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that contains some methods to deal with (small) integers
 * 
 * 
 * @author frank
 */
public class IntNumber {
    
    /**
     * 
     * @param a
     * @param b
     * @return a^b  a raised to the b-th power 
     */
    public static int power(int a, int b) {
        if(b < 0 ){
            throw new IllegalArgumentException("integer to negative power");
        }
        
        int result = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                result *= a;        
            }
            b >>= 1;
            a *= a; 
        }
        return result;
    }
    
    /**
     * Euclid's algorithm to calculate the Greatest Common Divisor of two numbers
     * @param a
     * @param b
     * @return the greatest common divisor of a and b
     */
    public static int gcd(int a, int b){
        if((a < 0)||(b<0)){
            throw new IllegalArgumentException("gcd applies to positive numbers");
        }
        
        if(b==0){ return a;}
        return gcd(b, a%b);
    }
    
    /**
     * The least common multiple of two numbers
     */
    public static int lcm(int a, int b){
        if((a < 0)||(b<0)){
            throw new IllegalArgumentException("lcm applies to positive numbers");
        }
        
        if((a==0)||(b==0)){ return 0;}
        
        return ((a*b)/gcd(a,b));
    }
    
     /**
     * 
     * @param n
     * @return true if n is a power of a prime 
     * 
     * returns false for n=1, or n having >1 prime factor.
     */
    public static boolean isPrimePower(int n){
        return factorize(n).keySet().size() == 1;
    }

    /**
     * 
     * @param n an integer with prime factorization p1^k1 p2^k2...
     * @return a map that sends pi->ki
     * 
     * The resulting map only has entries for the prime numbers where the exponent
     * is positive.
     */
    public static Map<Integer, Integer> factorize(int n) {
        if( n < 1){
            throw new IllegalArgumentException("cannot factorize number < 1");
        }
        
        Map<Integer, Integer> result = new HashMap<Integer, Integer>();
        int currentPrime = 2;
        
        recursivelyFactorize(n, currentPrime, result);
        return result;
    }
    
    private static void recursivelyFactorize(int n, int p, Map<Integer, Integer> result) {
        if(n ==1){
            return;
        }
        
        if((n%p)==0){
            if(!result.containsKey(p)){
                result.put(p, 0);
            }
            result.put(p, result.get(p)+1);
            recursivelyFactorize(n/p, p, result);
            
        } else {
          recursivelyFactorize(n, Prime.after(p), result);
        }
    }
}
