/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
                throw new IllegalArgumentException("argument is not a permutation");
            }
            for(int j = 0; j < perm.length; j++){
                if((i!=j) && perm[i] == perm[j]){
                    throw new IllegalArgumentException("argument is not a permutation");
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


