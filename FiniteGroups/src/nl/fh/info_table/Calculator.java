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
package nl.fh.info_table;

/**
 *
 * @author frank
 */
public interface Calculator {
    
     /**
     * 
     * @return a enum describing the property calculated by this
     */
    public Property getProperty();
    
    /**
     * 
     * @param info the information on which the property will be based
     * @return the output of this calculator, using info as input
     * @throws InfoTableException when the calculation does not succeed
     */
    public Value evaluate(InfoTable info) throws InfoTableException;
}
