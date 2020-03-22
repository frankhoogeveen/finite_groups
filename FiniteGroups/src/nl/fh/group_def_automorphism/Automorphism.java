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
import nl.fh.homomorphism.GroupHomomorphism;
import nl.fh.homomorphism.HomomorphismException;
import nl.fh.homomorphism_calculator.HomomorphismProperty;

/**
 *
 * 
 * @author frank
 */
public class Automorphism extends GroupHomomorphism implements Element{
    
    public Automorphism(Group group, Map<Element, Element> coreMap) throws HomomorphismException {
        super(group, group, coreMap);
        
        try {
            // check whether the defined group is truly an automorphism
            if(!(boolean) this.getProperty(HomomorphismProperty.IsAuto)){
                String mess = "isAutomorphism not consistent";
                Logger.getLogger(Automorphism.class.getName()).log(Level.SEVERE, mess);
                throw new HomomorphismException(mess);
            }
        } catch (EvaluationException ex) {
            String mess = "could not define automorphism";
            Logger.getLogger(Automorphism.class.getName()).log(Level.SEVERE, mess, ex);
            throw new HomomorphismException(mess);
        }
    }
}
