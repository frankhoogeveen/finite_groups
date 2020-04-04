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
package nl.fh.group_def_semidirect_product;

import java.util.Objects;
import nl.fh.group.Element;

/**
 *
 * @author frank
 */
public class SemiDirectProductElement implements Element{

    final Element elementN;
    final Element elementH;
    
    public SemiDirectProductElement(Element elementN, Element elementH){
        this.elementN = elementN;
        this.elementH = elementH;
    }
    
    @Override
    public String toString(){
        return "(" + elementN.toString() + "," + elementH.toString() + ")";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.elementN);
        hash = 41 * hash + Objects.hashCode(this.elementH);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SemiDirectProductElement other = (SemiDirectProductElement) obj;
        if (!Objects.equals(this.elementN, other.elementN)) {
            return false;
        }
        if (!Objects.equals(this.elementH, other.elementH)) {
            return false;
        }
        return true;
    }
}
