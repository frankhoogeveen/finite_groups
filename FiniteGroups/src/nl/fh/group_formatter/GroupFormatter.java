/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group_formatter;

import nl.fh.group.Group;

/**
 *
 *  Creates a (configurable) report on the properties of a group
 * 
 * @author frank
 */
public class GroupFormatter {

    private int PAGE_WIDTH = 80;
    private Group group;

    public GroupFormatter(){
        
    }
    
    public String createReport(Group g){
        this.group = g;
        
        StringBuilder sb = new StringBuilder();
        sb.append(header());
        
        sb.append(footer());
        return sb.toString();
    }

    private StringBuilder header() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("\n");
        for(int i = 0; i < PAGE_WIDTH; i++){
            sb.append("=");
        }
        sb.append("\n");
        
        sb.append(this.group.getName());
        sb.append("\n");
        return sb;
    }

    private StringBuilder footer() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("\n");
        for(int i = 0; i < PAGE_WIDTH; i++){
            sb.append("-");
        }
        sb.append("\n");

        return sb;
    }
    
}
