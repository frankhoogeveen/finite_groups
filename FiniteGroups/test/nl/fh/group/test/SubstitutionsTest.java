/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group.test;

import nl.fh.group.GroupTable;
import nl.fh.group.SubstitutionGroupFactory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class SubstitutionsTest {
    @Test
    public void D3Test(){
        SubstitutionGroupFactory fac = new SubstitutionGroupFactory();
        fac.addGenerator("a");
        fac.addGenerator("b");
        fac.addSubstitution("aa", "");
        fac.addSubstitution("bbb", "");
        fac.addSubstitution("ba", "abb");
        
        GroupTable table = fac.createTable();
        assertEquals(6, table.getOrder());
        assertTrue(table.checkDefinition());
    }
    
    @Test
    public void Y21Test(){ 
        SubstitutionGroupFactory fac = new SubstitutionGroupFactory();
        fac.addGenerator("a");
        fac.addGenerator("b");
        fac.addSubstitution("aaa", "");
        fac.addSubstitution("bbbbbbb", "");
        fac.addSubstitution("ba", "abb");
        
        GroupTable table = fac.createTable();
        assertEquals(21, table.getOrder());
        assertTrue(table.checkDefinition());
    }
}
