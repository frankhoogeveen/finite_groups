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
package nl.fh.info_table_values;

import java.util.Map;
import java.util.Objects;
import nl.fh.info_table.Value;

/**
 * Map from subsets (as boolean[]) to subsets (as boolean[])
 * Used to map from sets of generators to the subgroups generated 
 * by these sets.
 *
 * @author frank
 */
public class GeneratorMapValue implements Value {

    private final Map<boolean[], boolean[]> map;

    public GeneratorMapValue(Map<boolean[], boolean[]> map) {
        this.map = map;
    }
    
    public Map<boolean[], boolean[]> content(){
        return this.map;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(boolean[] key : map.keySet()){
            for(int i = 0; i < key.length; i++){
                if(key[i]){
                    sb.append(Integer.toString(i));
                    sb.append(" ");
                }
            }
            sb.append("-> ");
            boolean[] val = this.map.get(key);
            for(int i = 0; i < val.length; i++){
                if(val[i]){
                    sb.append(Integer.toString(i));
                    sb.append(" ");
                }
            }
            
            sb.append("\n");
        }
        
        return sb.toString();
        }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.map);
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
        final GeneratorMapValue other = (GeneratorMapValue) obj;
        if (!Objects.equals(this.map, other.map)) {
            return false;
        }
        return true;
    }
    
}
