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
package nl.fh.lattice;

/**
 *
 *  The outcome of a comparison between two items in a lattice
 * 
 * @author frank
 */
public enum LatticeComparison {
    Greater,
    Smaller,
    Equal,
    Unrelated;
    
    /**
     *
     * @param comparison
     * @return
     */
    public static boolean above(LatticeComparison comparison){
        if(comparison.equals(Greater)){
            return true;
        } 
        return false;
    }
    
    /**
     *
     * @param comparison
     * @return
     */
    public static boolean aboveEqual(LatticeComparison comparison){
        if(comparison.equals(Greater)){
            return true;
        } 
        if(comparison.equals(Equal)){
            return true;
        } 
        return false;
    }
    
        /**
     *
     * @param comparison
     * @return
     */
    public static boolean below(LatticeComparison comparison){
        if(comparison.equals(Smaller)){
            return true;
        } 
        return false;
    }
    
    /**
     *
     * @param comparison
     * @return
     */
    public static boolean belowEqual(LatticeComparison comparison){
        if(comparison.equals(Smaller)){
            return true;
        } 
        if(comparison.equals(Equal)){
            return true;
        } 
        return false;
    }
}
