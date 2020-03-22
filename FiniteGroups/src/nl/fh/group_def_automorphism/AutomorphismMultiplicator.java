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
package nl.fh.group_def_automorphism;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.Multiplicator;
import nl.fh.homomorphism.HomomorphismException;
import nl.fh.homomorphism_calculator.HomomorphismProperty;

/**
 *
 * @author frank
 */
public class AutomorphismMultiplicator implements Multiplicator<Automorphism> {

    @Override
    public Automorphism getProduct(Automorphism aut1, Automorphism aut2) throws EvaluationException {
        Group group = (Group)aut1.getProperty(HomomorphismProperty.Domain);
        Map<Element, Element> map = (Map<Element, Element>) aut1.after(aut2).getProperty(HomomorphismProperty.Map);
        Automorphism result;
        try {
            result = new Automorphism(group, map);
        } catch (HomomorphismException ex) {
            String mess = "could not get product of automorphisms";
            Logger.getLogger(AutomorphismMultiplicator.class.getName()).log(Level.SEVERE, mess, ex);
            throw new EvaluationException(mess);
        }
        return result;
    }
    
}
