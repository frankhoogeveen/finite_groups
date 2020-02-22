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
package nl.fh.group_info_calculators;

import nl.fh.info_table_values.IntArray2dValue;
import nl.fh.group_info_table.GroupInfoTableException;
import nl.fh.group.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fh.info_table.InfoTable;
import nl.fh.info_table.Property;
import nl.fh.info_table.Value;

/**
 * Instances of this class calculate the multiplication table based on a list
 * of elements and the multiplicator.
 * 
 * @author frank
 */
public class MultiplicationTableCalculator implements GroupCalculator{

    private final List<Element> elements;
    private final Multiplicator multiplicator;

    public MultiplicationTableCalculator(List<Element> elements, Multiplicator multiplicator) {
        this.elements = elements;
        this.multiplicator = multiplicator;
    }

    @Override
    public Property getProperty() {
        return GroupProperty.MultiplicationTable;
    }
    
    @Override
    public Value evaluate(InfoTable info) throws GroupInfoTableException {
        int[][] result = new int[elements.size()][];
        for(int i1 = 0; i1 < elements.size(); i1++){
            result[i1] = new int[elements.size()];
            for(int i2 = 0; i2 < elements.size(); i2++){
                Element g1 = elements.get(i1);
                Element g2 = elements.get(i2);

                try {
                    Element g3 = multiplicator.getProduct(g1,g2);
                    result[i1][i2] = elements.indexOf(g3);
                } catch (MultiplicatorException ex) {
                    throw new GroupInfoTableException("could not build multiplication table [operation not defined]");
                }

                if(result[i1][i2] < 0){
                    throw new GroupInfoTableException("could not build multiplication table [not closed]");
                }
            }
        }
        
        return new IntArray2dValue(result);
    }
}
