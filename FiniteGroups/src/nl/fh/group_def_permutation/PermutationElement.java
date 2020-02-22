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

import java.util.Arrays;
import nl.fh.group.Element;

/**
 *
 * @author frank
 */
public class PermutationElement implements Element{

    int[] permutation;

    public PermutationElement() {
    }

    /**
     *
     * @param perm a permutation 
     */
    public PermutationElement(int[] perm) {
        checkPermutation(perm);
        this.permutation = perm;        
    }
    
    private void checkPermutation(int[] perm) {
        for(int i = 0; i < perm.length; i++){
            if((perm[i] < 0) || perm[i] >= perm.length){
                throw new IllegalArgumentException("argument is not a permutation/ out of range");
            }
            for(int j = 0; j < perm.length; j++){
                if((i!=j) && perm[i] == perm[j]){
                    throw new IllegalArgumentException("argument is not a permutation/ not bijective");
                }
            }
        }
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(permutation[0]);
        for(int i = 1; i < permutation.length; i++){
            sb.append(", ");
            sb.append(permutation[i]);
        }
        sb.append("]");
        
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Arrays.hashCode(this.permutation);
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
        final PermutationElement other = (PermutationElement) obj;
        if (!Arrays.equals(this.permutation, other.permutation)) {
            return false;
        }
        return true;
    }
    
    
}


