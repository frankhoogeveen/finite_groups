/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.info_table_values;

import nl.fh.info_table.Value;

/**
 *
 * @author frank
 */
public class BooleanValue implements Value{

    private final boolean content;

    public BooleanValue(boolean content){
        this.content = content;
    }
    
    public boolean content() {
        return this.content;
    }
    
}
