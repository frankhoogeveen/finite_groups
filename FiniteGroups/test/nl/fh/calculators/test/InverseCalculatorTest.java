/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.calculators.test;

import java.util.HashSet;
import java.util.Set;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.GroupDefinition;
import nl.fh.group_def_substitutions.StringElement;
import nl.fh.group_def_substitutions.StringMultiplicator;
import nl.fh.group_def_substitutions.StringSubstitution;
import nl.fh.group_info_calculators.GroupProperty;
import nl.fh.info_table.InfoTable;
import nl.fh.info_table.InfoTableException;
import nl.fh.info_table_values.IntArray1dValue;
import nl.fh.info_table_values.IntArray2dValue;
import nl.fh.info_table_values.IntValue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class InverseCalculatorTest {
    @Test
    public void Y21Test() throws InfoTableException{
        Set<Element> generators = new HashSet<Element>();
        generators.add(new StringElement("x"));
        generators.add(new StringElement("y"));
        
        StringMultiplicator multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution("xxx", ""));
        multiplication.addSubstitution(new StringSubstitution("yyyyyyy", ""));
        multiplication.addSubstitution(new StringSubstitution("yx", "xyy"));
        
        String name = "Y21";
        
        GroupDefinition definition = new GroupDefinition(name, generators, multiplication);
        
        Group g = new Group(definition);
        InfoTable info =  g.getInfo();
        
        
        
        int[][] table = ((IntArray2dValue)info.getValue(GroupProperty.MultiplicationTable)).content();
        int[] inv  = ((IntArray1dValue)info.getValue(GroupProperty.Inverses)).content();
        int unit   = ((IntValue)info.getValue(GroupProperty.UnitElement)).content();
        
        assertEquals(21, table.length);
        
        for(int i = 0; i < 21; i++){
            assertEquals(unit, table[i][inv[i]]);
            assertEquals(unit, table[inv[i]][i]);
        }
    }
}
