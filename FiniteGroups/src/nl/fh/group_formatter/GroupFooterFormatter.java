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
package nl.fh.group_formatter;

import nl.fh.group.Group;

/**
 *
 * @author frank
 */
public class GroupFooterFormatter implements ItemFormatter {
    
    private final int PAGE_WIDTH = 80;
    private final GroupFormatter overall;

    GroupFooterFormatter(GroupFormatter overall) {
        this.overall = overall;
    }
    
    @Override
    public StringBuilder format(Group g) {
        StringBuilder sb = new StringBuilder();
        
        if(this.overall.isVerbose()){
            sb.append("\n");
            for(int i = 0; i < PAGE_WIDTH; i++){
                sb.append("-");
            }
        }
        sb.append("\n");

        return sb;
    }
    
}
