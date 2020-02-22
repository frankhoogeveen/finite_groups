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
package nl.fh.group_catalogue;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fh.group.Group;
import nl.fh.group.GroupDefinition;
import nl.fh.group_info_table.GroupInfoTableException;
import nl.fh.info_table.InfoTableException;

/**
 * A collection/ catalog of groups 
 * 
 * 
 * @author frank
 */
public class GroupCatalog {
    private static final Logger LOGGER = Logger.getLogger(GroupCatalog.class.getSimpleName());
    private List<Group> list;
    
    public GroupCatalog(){
        this.list = new ArrayList<Group>();
    }
    
    /**
     * 
     * @param def definition of a finite group
     * 
     * Add a group to the catalog
     */
    public void add(GroupDefinition def){
            try {
                this.list.add(new Group(def));
            } catch (InfoTableException ex) {
                String mess = "could not add group " + def.getName();
                Logger.getLogger(GroupCatalog.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public List<Group> getList() {
        return list;
    }
}
