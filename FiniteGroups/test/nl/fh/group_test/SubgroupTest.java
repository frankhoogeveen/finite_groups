/*
 * Copyright (C) 2020 Frank Hoogeveen
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package nl.fh.group_test;

import java.util.ArrayList;
import java.util.List;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.GroupDefinition;
import nl.fh.group.SubgroupDefinition;
import nl.fh.group_definition_factory.GroupDefinitionFactory;
import nl.fh.group_info_calculators.GroupProperty;
import nl.fh.group_info_table.GroupInfoTableChecker;
import nl.fh.info_table.InfoTableException;
import nl.fh.info_table_values.BooleanValue;
import nl.fh.info_table_values.FamilyValue;
import nl.fh.info_table_values.SubsetValue;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class SubgroupTest {
    
    @Test
    public void S4Test() throws InfoTableException{
        GroupDefinitionFactory fac = new GroupDefinitionFactory();
        Group s4 = new Group(fac.getSymmetricGroup(4));
        
        FamilyValue val = (FamilyValue)(s4.getInfo().getValue(GroupProperty.StronglyMinimalGeneratingSets));
        boolean[][] content = val.content();
        
        GroupInfoTableChecker checker = new GroupInfoTableChecker();
        for(int i = 0; i < content.length; i++){
            List<Element> list = new ArrayList<Element>();
            
            for(int j = 0; j < content[i].length; j++){
                if(content[i][j]){
                    list.add(s4.getElements().get(j));
                }
            }
            
            Group sub = new Group(new SubgroupDefinition(s4.getDefinition(), list));
            
            assertTrue(checker.isGroup(sub.getInfo()));
            
        }
    }
    
    @Test
    public void S5CenterTest() throws InfoTableException{
        GroupDefinitionFactory fac = new GroupDefinitionFactory();
        Group s5 = new Group(fac.getSymmetricGroup(5));
        
        SubsetValue center = (SubsetValue) s5.getInfo().getValue(GroupProperty.Center);
        boolean[] centerBoolean = center.content();
        
        List<Element> list = new ArrayList<Element>();
        for(int i = 0; i < centerBoolean.length; i++){
            if(centerBoolean[i]){
                list.add(s5.getElements().get(i));
            }
        }
        
        Group sub =new Group( new SubgroupDefinition(s5.getDefinition(), list));

        GroupInfoTableChecker checker = new GroupInfoTableChecker();
        assertTrue(checker.isGroup(sub.getInfo()));
        
        boolean isAbelean = ((BooleanValue)(sub.getInfo().getValue(GroupProperty.IsAbelean))).content();
        assertTrue(isAbelean);
    }
    
    
}
