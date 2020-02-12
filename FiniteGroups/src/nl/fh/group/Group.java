/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group;

import java.util.List;
import nl.fh.group_info_table.GroupInfoTable;
import nl.fh.group_info_table.GroupInfoTableException;

/**
 *
 * @author frank
 */
public class Group {
    private GroupDefinition def;
    private final List<Element> elements;
    private final GroupInfoTable info;
    private final int MAX_ITER = 10000;
    
    /**
     * 
     * @param def the definition of the group
     * @throws nl.fh.group_info_table.GroupInfoTableException
     */
    public Group(GroupDefinition def) throws GroupInfoTableException {
        this.elements = GroupElementsConstructor.construct(def, MAX_ITER);
        this.info = new GroupInfoTable(elements, def.getMultiplicator());
    }
}
