/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group_formatter;

import nl.fh.group.Group;

/**
 *
 * Interface to format items from the group info tables.
 * 
 * @author frank
 */
interface ItemFormatter {

    /**
     * 
     * @param g
     * @return a string builder containing a formatted report of an info item of g
     */
    public StringBuilder format(Group g);
    
}
