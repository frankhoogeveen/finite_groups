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
package nl.fh.group_def_permutation;

import nl.fh.group.Multiplicator;

/**
 *
 * @author frank
 */
public class PermutationMultiplicator implements Multiplicator<PermutationElement> {

    private int degree;

    public PermutationMultiplicator(int degree) {
        if(degree < 1){
            throw new IllegalArgumentException();
        }
        
        this.degree = degree;
    }

    @Override
    public PermutationElement getProduct(PermutationElement e1, PermutationElement e2)  {
        if((e1.permutation.length != this.degree) ||(e1.permutation.length != this.degree)) {
            throw new IllegalArgumentException();
        }
        
        int[] result = new int[e1.permutation.length];
        
        for(int i = 0; i < result.length; i++){
            result[i] = e1.permutation[e2.permutation[i]];
        }
        return new PermutationElement(result);
    }
}
