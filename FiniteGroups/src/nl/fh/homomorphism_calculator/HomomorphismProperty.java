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
package nl.fh.homomorphism_calculator;

import nl.fh.calculator.Calculator;
import nl.fh.calculator.Property;
import nl.fh.homomorphism.GroupHomomorphism;

/**
 * The enum list of all properties of Morphisms for which a calculator is
 * defined.
 * 
 * 
 * @author frank
 */
public enum HomomorphismProperty implements Property {

    // properties that are set when defining the homomorphism
    Domain(null),
    Codomain(null),
    Map(null),   
    
    // properties that could be calculated at a later stage
    Name(new NameCalculator()),
    
    IsMono(new IsMonoCalculator()),
    IsEpi(new IsEpiCalculator()),
    IsEndo(new IsEndoCalculator()),
    IsIso(new IsIsoCalculator()),
    IsAuto(new IsAutoCalculator()),
    IsEmbedding(new IsEmbeddingCalculator()),
    IsNormal(new IsNormalCalculator()),
    
    KernelSet(new KernelSetCalculator()),
    KernelGroup(new KernelGroupCalculator()),
    KernelEmbedding(new KernelEmbeddingCalculator());

    private final Calculator calculator;

    /**
     * 
     * @param calc the calculator
     * 
     * The caller should know what to do with the output of the calculator
     * , in particular to what class to cast it,
     * 
     */
    private HomomorphismProperty(Calculator<GroupHomomorphism> calc){
        this.calculator = calc;
    }

    @Override
    public Calculator getCalculator() {
        return this.calculator;
    }
}
