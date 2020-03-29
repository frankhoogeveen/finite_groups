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

import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fh.calculator.Calculator;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Group;
import nl.fh.homomorphism.GroupHomomorphism;
import nl.fh.homomorphism.HomomorphismException;

/**
 * Calculates the embedding of the center of a group into that group.
 * Given f: G->H , this calculator returns ker(f) -> G
 * 
 * 
 * @author frank
 */
public class KernelEmbeddingCalculator implements Calculator<GroupHomomorphism> {

    @Override
    public GroupHomomorphism evaluate(GroupHomomorphism morph) throws EvaluationException {
        Group kernel = (Group) morph.getProperty(HomomorphismProperty.KernelGroup);
        Group domain = (Group) morph.getProperty(HomomorphismProperty.Domain);
        
        try {
            return domain.embed(kernel);
        } catch (HomomorphismException ex) {
            String mess = "could not embed kernel in group";
            Logger.getLogger(KernelEmbeddingCalculator.class.getName()).log(Level.SEVERE, mess, ex);
            throw new EvaluationException(mess);
        }
    }
}
