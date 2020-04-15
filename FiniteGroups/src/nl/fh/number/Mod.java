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

/**
 *
 * @author frank
 */
public class Mod {
    
    /**
     * 
     * @param a
     * @param b
     * @return the inverse of a mod b. I.e. an x such that a.x = 1 mod b 
     * 
     * Throws Illegal Argument Exception if x does not exist,
     * this happens when gcd(a,b) > 1
     * 
     * The algorithm used is https://en.wikipedia.org/wiki/Extended_Euclidean_algorithm#Description
     */
    public static int inverse(int a, int b){
        
        if( b < 1){
            throw new IllegalArgumentException("Modulo a non positive number");
        }
        
        if(a % b == 0){
            throw new IllegalArgumentException("Inverse of zero does not exists");
        }
        
        
        int r0 = a;
        int s0 = 1;
        int t0 = 0;
        
        int r1 = b;
        int s1 = 0;
        int t1 = 1;
        
        while(r1 != 0){
            int r2 = r0 % r1;
            int q = (r0 - r2)/ r1;
            int s2 = s0 - q * s1;
            int t2 = t0 - q * t1;
            
            r0 = r1;
            r1 = r2;
            
            s0 = s1;
            s1 = s2;
            
            t0 = t1;
            t1 = t2;
        }
        
        int gcd = r0;
        if(gcd != 1){
            throw new IllegalArgumentException(" gcd not one");
        }
        
        s0 = s0 % b;
        if(s0 < 0){
            s0 += b;
        }
        
        return s0;
    }
}
