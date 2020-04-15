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

/**
 *
 * @author frank
 * @param <T> the type of object on which this calculator acts
 */
public interface Calculator<T> {
    
    /**
     * 
     * @param subject of which a property will be calculated
     * @return the output of this calculator
     * @throws EvaluationException when the calculation does not succeed
     */
    public Object evaluate(T subject) throws EvaluationException;
    
}
