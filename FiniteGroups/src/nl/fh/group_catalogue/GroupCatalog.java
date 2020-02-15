/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group_catalogue;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fh.group.Group;
import nl.fh.group.GroupDefinition;
import nl.fh.group_info_table.GroupInfoTableException;

/**
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
            } catch (GroupInfoTableException ex) {
                String mess = "could not add group " + def.getName();
                Logger.getLogger(GroupCatalog.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    

    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Group g : this.list){
            sb.append(g.createReport());
        }
        return sb.toString();
    }
}
