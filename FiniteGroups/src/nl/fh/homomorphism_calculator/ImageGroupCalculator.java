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

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fh.calculator.Calculator;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.GroupException;
import nl.fh.group.Multiplicator;
import nl.fh.group_calculators.GroupProperty;
import nl.fh.homomorphism.GroupHomomorphism;

/**
 * return the image of a morphism as a group
 * @author frank
 */
public class ImageGroupCalculator implements Calculator<GroupHomomorphism> {

    @Override
    public Group evaluate(GroupHomomorphism morph) throws EvaluationException {
        String name = (String) morph.getProperty(HomomorphismProperty.Name);
        name = "Im(" + name + ")";
        
        Set<Element> set = (Set<Element>) morph.getProperty(HomomorphismProperty.ImageSet);
        
        Group codomain = (Group) morph.getProperty(HomomorphismProperty.Codomain);
        Multiplicator mult = (Multiplicator) codomain.getProperty(GroupProperty.MultiplicationTable);
        
        try {
            return new Group(name, set, mult);
        } catch (GroupException ex) {
            String mess = "could not create Image Group of Homomorphism";
            Logger.getLogger(ImageGroupCalculator.class.getName()).log(Level.SEVERE, mess, ex);
            throw new EvaluationException(mess);
        }
    }
}
