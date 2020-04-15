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
package nl.fh._tasks;

import java.util.ArrayList;
import java.util.List;
import nl.fh.field.Polynomial;

/**
 *
 * @author frank
 */
public class ListIrreduciblePolynomials {
    
    public static void main (String[] args){
     
        int degree = 1;
        int prime = 2;
        
        System.out.println("Listing irreducible polynomials");
        System.out.println("degree : " + Integer.toString(degree));
        System.out.println("prime  : " + Integer.toString(prime) + "\n");
        
        List<Polynomial> list = Polynomial.listAllMonicOfDegree(degree, prime);
        List<Polynomial> irreducibles = new ArrayList<Polynomial>();
        
        for(Polynomial poly : list){
            if(poly.isIrreducible(prime)){
                irreducibles.add(poly);
            }
        }
        
        System.out.println("Number of monic irreducible polynomials found: " + Integer.toString(irreducibles.size())) ;
        System.out.println(irreducibles.get(0));
//        
//        for(Polynomial poly : irreducibles){
//            System.out.println(poly);
//        }    
    }
}
