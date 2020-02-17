/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
