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
import java.util.Map;
import nl.fh.group.Element;

/**
 *
 * @author frank
 */
public class GroupPowerTable extends HashMap<Element, Map<Integer, Element>> {
    private final int order;
    
    
    public GroupPowerTable(int order){
        this.order = order;
    }
    
    /**
     * 
     * @param g
     * @param n integer, can be negative or zero
     * @return g^n 
     */
    public Element power(Element g, int n){
        int exp = n % this.order;
        if(exp < 0){
            exp += order;
        }
        return this.get(g).get(exp);
    };
}
