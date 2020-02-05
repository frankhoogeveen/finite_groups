/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.format;

import nl.fh.group.GroupTable;

/**
 *
 * @author frank
 */
public class Formatter {
    private GroupTable table;
    
    /**
     * 
     * @param table 
     */
    public Formatter(GroupTable table){
        this.table = table;
    }
    
    /**
     * 
     * @return a string with all multiplications of the group
     */
    public String listMultiplications(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < table.getOrder(); i++){
            for(int j = 0; j < table.getOrder(); j++){
                sb.append(table.getString(i));
                sb.append(" ");
                sb.append(table.getString(j));
                sb.append(" = ");
                sb.append(table.getString(table.multiply(i,j)));
                sb.append("\n");
            }
        }
        return sb.toString();
    }
    
    
}
