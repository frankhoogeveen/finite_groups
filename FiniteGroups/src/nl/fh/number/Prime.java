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

import java.util.ArrayList;

/**
 * The Sieve of Eratosthenes 
 * 
 * @author frank
 */
public class Prime {
    private static ArrayList<Boolean> sieve = new ArrayList();
    
    /**
     *
     * @param n
     * @return true is n is a positive prime, false otherwise
     */
    public static boolean isPrime(int n){
        if(n < 2){
            return false;
        }
        
        if(n >= sieve.size()){
            updateSieve(n);
        }
        
        return sieve.get(n);
    }

    /**
     * 
     * @param n the size of the new sieve of Erathostenes
     */
    private static void updateSieve(int n) {
        sieve = new ArrayList();
        
        for(int i = 0; i < n+1; i++){
            sieve.add(Boolean.TRUE);
        }
        
        sieve.set(0, false);
        sieve.set(1, false);
        
        for(int k = 0; k*k <= n; k++){
            if(sieve.get(k)){
                for(int mult = 2; mult*k < sieve.size(); mult++){
                      sieve.set(mult*k, Boolean.FALSE);
                }
            }
        }
    }
    
    /**
     * 
     * @param n
     * @return  the smallest prime > n
     */
    public static int after(int n){
        if(n < 2){
            return 2;
        }
        
        int k = n;
        while(true){
            k += 1;
            if(isPrime(k)){
                return k;
            }
        }
    }
}
