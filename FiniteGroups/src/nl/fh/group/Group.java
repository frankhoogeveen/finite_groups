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
package nl.fh.group;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fh.group_info_table.GroupInfoTable;
import nl.fh.info_table.InfoTableException;

/**
 *
 * @author frank
 */
public class Group {
    private GroupDefinition def;
    private List<Element> elements;
    private GroupInfoTable info;
    
    private final int MAX_ITER = 10000;
    
    /**
     * 
     * @param def the definition of the group
     * @throws nl.fh.group_info_table.GroupInfoTableException
     */
    public Group(GroupDefinition def) throws InfoTableException {
        this.def = def;
        try {
            this.elements = GroupElementsConstructor.construct(def, MAX_ITER);
            this.info = new GroupInfoTable(elements, def.getMultiplicator());
        } catch (MultiplicatorException ex) {
            String mess = "could not construct group " + this.def.getName();
            Logger.getLogger(Group.class.getName()).log(Level.SEVERE, mess, ex);
            
        }
    }

    public GroupDefinition getDefinition() {
        return def;
    }

    public List<Element> getElements() {
        return elements;
    }

    public GroupInfoTable getInfo() {
        return info;
    }

    public String getName() {
        return this.def.getName();
    }
}
