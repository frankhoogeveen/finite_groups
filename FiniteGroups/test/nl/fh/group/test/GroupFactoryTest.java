/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group.test;

import nl.fh.group.Group;
import nl.fh.group.GroupDefinition;
import nl.fh.group_definition_factory.GroupDefinitionFactory;
import nl.fh.group_info_calculators.GroupProperty;
import nl.fh.info_table.InfoTableException;
import nl.fh.info_table_values.IntValue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class GroupFactoryTest {
    
    public GroupFactoryTest(){
        
    }
    
    @Test
    public void CyclicTest() throws InfoTableException{
        GroupDefinitionFactory fac = new GroupDefinitionFactory();
        Group c3 = new Group(fac.getCyclicGroup(3));
        assertEquals(3, ((IntValue)c3.getInfo().getValue(GroupProperty.Order)).content());
    }
    
    public void AbeleanTest() throws InfoTableException{
        GroupDefinitionFactory fac = new GroupDefinitionFactory();
        Group c30 = new Group(fac.getAbeleanGroup(new int[]{2,3,5}));
        
        assertEquals(2*3*5, ((IntValue)c30.getInfo().getValue(GroupProperty.Order)).content());
    }
    
}
