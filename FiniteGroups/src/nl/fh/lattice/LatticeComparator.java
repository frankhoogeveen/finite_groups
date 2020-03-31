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
 * @author frank
 */
public interface LatticeComparator<T> {
    
    /**
     *
     * @param t1
     * @param t2
     * @return relation, such that t1 relation t2 holds
     */
    public LatticeComparison compare(T t1, T t2);
}
