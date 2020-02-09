/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group_permutation;

import nl.fh.group.Multiplicator;

/**
 *
 * @author frank
 */
public class PermutationMultiplicator implements Multiplicator<PermutationElement> {

    public PermutationMultiplicator(int degree) {
    }

    @Override
    public PermutationElement getProduct(PermutationElement e1, PermutationElement e2)  {
        if(e1.permutation.length != e2.permutation.length){
            throw new IllegalArgumentException();
        }
        
        int[] result = new int[e1.permutation.length];
        
        for(int i = 0; i < result.length; i++){
            result[i] = e1.permutation[e2.permutation[i]];
        }
        return new PermutationElement(result);
    }
    
}
