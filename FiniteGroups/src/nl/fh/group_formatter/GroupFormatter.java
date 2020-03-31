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

import java.util.ArrayList;
import java.util.List;
import nl.fh.group.Group;
import nl.fh.group_catalogue.GroupCatalog;

/**
 *
 *  Creates a (configurable) report on the properties of a group
 * 
 * @author frank
 */
public class GroupFormatter {

    private final List<ItemFormatter> list;
    private boolean verbose = false;
    
    public GroupFormatter(){
        this.list = new ArrayList<ItemFormatter>();
        this.list.add(new GroupHeaderFormatter(this));
        this.list.add(new OrderFormatter(this));
        this.list.add(new IsAbeleanFormatter(this));
        this.list.add(new CenterFormatter(this));
        this.list.add(new InversesFormatter(this));
        this.list.add(new ConjugationClassesFormatter(this));
        this.list.add(new DerivedGroupFormatter(this));
        this.list.add(new AbeleanizationFormatter(this));
        this.list.add(new InnerAutomorphismGroupFormatter(this));
        this.list.add(new AutomorphismGroupFormatter(this));
        this.list.add(new OuterAutomorphismGroupFormatter(this));
        this.list.add(new SubgroupFormatter(this));
        this.list.add(new GroupFooterFormatter(this));
    }
    
    public StringBuilder createReport(Group g){
        
        StringBuilder sb = new StringBuilder();
        for(ItemFormatter item : this.list){
            sb.append(item.format(g));
        }
        return sb;
    }
    
    public String createReport(GroupCatalog catalog){
        StringBuilder sb = new StringBuilder();
        sb.append(reportHeader(catalog));
        
        for(Group g : catalog){
            sb.append(createReport(g));
        }
        
        sb.append(reportFooter(catalog));
        return sb.toString();
    }

    public boolean isVerbose() {
        return verbose;
    }
    
    private StringBuilder reportHeader(GroupCatalog catalog){
        StringBuilder sb = new StringBuilder();
        sb.append("*** ");
        sb.append(catalog.getClass().getSimpleName());
        sb.append(" [");
        sb.append(Integer.toString(catalog.size()));
        sb.append(" items] ***");
        sb.append("\n");
        
        return sb;
    }
    
    private StringBuilder reportFooter(GroupCatalog catalog){
        StringBuilder sb = new StringBuilder();
        sb.append("--- end of  ");
        sb.append(catalog.getClass().getSimpleName());
        sb.append(" [");
        sb.append(Integer.toString(catalog.size()));
        sb.append(" items] ---");
        sb.append("\n");
        
        return sb;
    }    
}
