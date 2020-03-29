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
import java.util.Iterator;
import java.util.List;
import nl.fh.group.Group;

/**
 * A collection/ catalog of groups 
 * 
 * 
 * @author frank
 */
public class GroupCatalog implements Iterable<Group> {
    private final List<Group> list;
    
    public GroupCatalog(){
        this.list = new ArrayList<Group>();
    }
    
    /**
     * 
     * @param group definition of a finite group
     * 
     * Add a group to the catalog
     */
    public void add(Group group){
        this.list.add(group);
    }

    @Override
    public Iterator<Group> iterator() {
        return list.iterator();
    }

    /**
     * 
     * @return the number of groups in the catalog
     */
    public int size() {
        return this.list.size();
    }
}
