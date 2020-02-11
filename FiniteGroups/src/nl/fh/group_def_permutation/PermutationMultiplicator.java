/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group_def_permutation;

import nl.fh.group.Element;
import nl.fh.group.Multiplicator;

/**
 *
 * @author frank
 */
public class PermutationMultiplicator implements Multiplicator<PermutationElement> {

    private static PermutationElement unit;
    private int degree;

    public PermutationMultiplicator(int degree) {
        if(degree < 1){
            throw new IllegalArgumentException();
        }
        
        this.degree = degree;
        
        int[] unitPermutation = new int[degree];
        for(int i = 0; i < degree; i++){
            unitPermutation[i] = i;
        }
        this.unit = new PermutationElement(unitPermutation);
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

    @Override
    public Element getUnit() {
        return PermutationMultiplicator.unit;
    }
    
}
