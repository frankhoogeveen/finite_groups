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
package nl.fh.group_def_substitutions;

import java.util.Objects;
import nl.fh.group.Element;


/**
 * a group element that is represented by a string of characters
 * 
 * @author frank
 */
public class StringElement implements Element {

    String characters;
    
    public StringElement(String s) {
        this.characters = s;
    }
    
    @Override
    public String toString(){
        if(this.characters.equals("")){
            return "1";
        } else {
            return characters;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.characters);
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
        final StringElement other = (StringElement) obj;
        if (!Objects.equals(this.characters, other.characters)) {
            return false;
        }
        return true;
    }
}
