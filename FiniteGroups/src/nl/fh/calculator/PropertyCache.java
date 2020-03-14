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
package nl.fh.calculator;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author frank
 */
public class PropertyCache<P extends Property> {
    
    protected final Map<P, Object> cache;
    
    public PropertyCache(){
        this.cache = new HashMap<P, Object>();
    }

    /**
     *
     * @param property
     * @return the value of the property for this group
     * @throws nl.fh.calculator.EvaluationException
     *
     *
     * The method caches all results
     * The calling code should know how to cast the resulting object
     */
    public Object getProperty(P property) throws EvaluationException {
        if (!cache.containsKey(property)) {
            cache.put(property, property.getCalculator().evaluate(this));
        }
        return cache.get(property);
    }
    
}
