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
     * Throws Illegal Argument Exception if x does not exist
     */
    public static int inverse(int a, int b){
        int x=1, y=1; 
        int gcd = gcdExtended(a, b, x, y);
        if(gcd != 1){
            throw new IllegalArgumentException("cannot invert number in Mod");
        }
        
        return x;
    }
    
    /**
     * 
     * @param a
     * @param b
     * @param x
     * @param y
     * @return 
     */
    private static int gcdExtended(int a, int b, int x, int y) 
    { 
        // Base Case 
        if (a == 0) 
        { 
            x = 0; 
            y = 1; 
            return b; 
        } 
  
        int x1=1, y1=1; // To store results of recursive call 
        int gcd = gcdExtended(b%a, a, x1, y1); 
  
        // Update x and y using results of recursive 
        // call 
        x = y1 - (b/a) * x1; 
        y = x1; 
  
        return gcd; 
    } 
}
