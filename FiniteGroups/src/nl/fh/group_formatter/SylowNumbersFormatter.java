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
package nl.fh.group_formatter;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Group;
import nl.fh.group_calculators.GroupProperty;

/**
 *
 * @author frank
 */
public class SylowNumbersFormatter implements ItemFormatter {

    public SylowNumbersFormatter(GroupFormatter aThis) {
    }

    @Override
    public StringBuilder format(Group g) {
        StringBuilder sb = new StringBuilder();
        sb.append("Sylow Numbers:\n");
        
        try {
            Map<Integer, Integer> sylowMap = (Map<Integer, Integer>) g.getProperty(GroupProperty.SylowMap);

            if(sylowMap.isEmpty()){
                sb.append("none\n");
            }
            
            for(Integer p : sylowMap.keySet()){
                sb.append("    n_");
                sb.append(p);
                sb.append(" = ");
                sb.append(sylowMap.get(p));
                sb.append("\n");
            }
            
        } catch (EvaluationException ex) {
            String mess = "could not calculate Sylow Numbers";
            Logger.getLogger(SylowNumbersFormatter.class.getName()).log(Level.SEVERE, mess, ex);
            sb.append(mess);
        }
        
        sb.append("\n");
        return sb;
    }
    
}
