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
package nl.fh.group_calculators;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fh.calculator.Calculator;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.GroupException;
import nl.fh.group_def_automorphism.Automorphism;
import nl.fh.group_def_automorphism.AutomorphismMultiplicator;
import nl.fh.homomorphism.HomomorphismException;

/**
 * Given a group, this constructs the inner automorphism group,
 * 
 * i.e. all automorphisms of the kind x ->  g x g^(-1)
 * 
 * @author frank
 */
class InnerAutomorphismGroupCalculator implements Calculator<Group> {

    @Override
    public Group evaluate(Group group) throws EvaluationException {
        Set<Element> inner = new HashSet<Element>();
        Map<Element, Element> inverse = (Map<Element, Element>) group.getProperty(GroupProperty.Inverses);
        GroupTable table = (GroupTable) group.getProperty(GroupProperty.MultiplicationTable);
        String groupName = (String) group.getProperty(GroupProperty.Name);
        
        for(Element g : group){
            Map<Element, Element> map = new HashMap<Element, Element>();
            for(Element h : group){
                Element conj = table.getProduct(g, table.getProduct(h, inverse.get(g)));
                map.put(h, conj);
            }
            try {
                inner.add(new Automorphism(group, map));
            } catch (HomomorphismException ex) {
                String mess = "could not create inner automorphism";
                Logger.getLogger(InnerAutomorphismGroupCalculator.class.getName()).log(Level.SEVERE, mess, ex);
                throw new EvaluationException(mess);
            }
        }
        
        String name = "Inn(" + groupName + ")";
        AutomorphismMultiplicator mult = new AutomorphismMultiplicator();
        
        try {
            return new Group(name, inner, mult);
        } catch (GroupException ex) {
            String mess = "could not create inner automorphism group";
            Logger.getLogger(InnerAutomorphismGroupCalculator.class.getName()).log(Level.SEVERE, mess, ex);
            throw new EvaluationException(mess);
        }
    }
}
