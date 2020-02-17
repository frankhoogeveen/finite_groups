/*
 * To change this license groupHeader, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

    private int PAGE_WIDTH = 80;
    private List<ItemFormatter> list;
    

    public GroupFormatter(){
        this.list = new ArrayList<ItemFormatter>();
        this.list.add(new OrderFormatter());
        this.list.add(new IsAbeleanFormatter());
        this.list.add(new ConjugationClassesFormatter());
    }
    
    public String createReport(GroupCatalog catalog){
        StringBuilder sb = new StringBuilder();
        sb.append(reportHeader(catalog));
        
        for(Group g : catalog.getList()){
            sb.append(createReport(g));
        }
        
        sb.append(reportFooter(catalog));
        return sb.toString();
    }
    
    private StringBuilder reportHeader(GroupCatalog catalog){
        StringBuilder sb = new StringBuilder();
        sb.append("*** ");
        sb.append(catalog.getClass().getSimpleName());
        sb.append(" [");
        sb.append(Integer.toString(catalog.getList().size()));
        sb.append(" items] ***");
        sb.append("\n");
        
        return sb;
    }
    
    private StringBuilder reportFooter(GroupCatalog catalog){
        StringBuilder sb = new StringBuilder();
        sb.append("--- end of  ");
        sb.append(catalog.getClass().getSimpleName());
        sb.append(" [");
        sb.append(Integer.toString(catalog.getList().size()));
        sb.append(" items] ---");
        sb.append("\n");
        
        return sb;
    }
    
    
    public StringBuilder createReport(Group g){
        
        StringBuilder sb = new StringBuilder();
        sb.append(groupHeader(g));
        
        for(ItemFormatter item : this.list){
            sb.append(item.format(g));
        }
        
        sb.append(groupFooter(g));
        return sb;
    }

    private StringBuilder groupHeader(Group g) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("\n");
        for(int i = 0; i < PAGE_WIDTH; i++){
            sb.append("=");
        }
        sb.append("\n");
        
        sb.append(g.getName());
        sb.append("\n");
        return sb;
    }

    private StringBuilder groupFooter(Group g) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("\n");
        for(int i = 0; i < PAGE_WIDTH; i++){
            sb.append("-");
        }
        sb.append("\n");

        return sb;
    }
}
