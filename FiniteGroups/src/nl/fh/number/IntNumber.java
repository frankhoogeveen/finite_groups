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
}
