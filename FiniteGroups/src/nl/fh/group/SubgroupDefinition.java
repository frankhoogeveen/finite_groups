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

import java.util.Collection;

/**
 *
 * @author frank
 */
public class SubgroupDefinition extends GroupDefinition{

    private final GroupDefinition parent;
    
    public SubgroupDefinition(GroupDefinition def, Collection<Element> generators) {
        super("subgroup of " + def.getName(), generators, def.getMultiplicator());
        this.parent = def;
    }
   
}
