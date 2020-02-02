/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group.test;

import nl.fh.group.CyclicGroupFactory;
import nl.fh.group.GroupFactory;
import nl.fh.group.GroupTable;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class CyclicTest {
    @Test
    public void Cyclic7Test(){
        GroupFactory fac =  new CyclicGroupFactory(7);
        GroupTable table = fac.createTable();
        assertTrue(table.checkDefinition());
    }
}
