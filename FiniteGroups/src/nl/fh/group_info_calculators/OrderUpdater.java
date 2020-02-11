/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group_info_calculators;

import nl.fh.group_info.GroupInfoConstructionException;
import nl.fh.group_info.GroupInfoTable;

/**
 *
 * @author frank
 */
public class OrderUpdater{

    public int calculate(GroupInfoTable info) throws GroupInfoConstructionException {
        return info.getGroupElements().size();
    }
    
}
