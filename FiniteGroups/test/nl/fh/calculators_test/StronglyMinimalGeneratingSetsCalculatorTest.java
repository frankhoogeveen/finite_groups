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
package nl.fh.calculators_test;

import nl.fh.group.Group;
import nl.fh.group.GroupDefinition;
import nl.fh.group_def_product.GroupProduct;
import nl.fh.group_definition_factory.GroupFactory;
import nl.fh.group.GroupProperty;
import nl.fh.calculator.EvaluationException;
import nl.fh.info_table_values.FamilyValue;
import nl.fh.info_table_values.IntValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class StronglyMinimalGeneratingSetsCalculatorTest {
    private boolean verbose = false;
    
    @Test
    public void S3Test() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group s3 = new Group(fac.getSymmetricGroup(3));
        
        FamilyValue val = (FamilyValue)(s3.getInfo().getValue(GroupProperty.StronglyMinimalGeneratingSets));
        boolean[][] content = val.content();
        int unit = ((IntValue)(s3.getInfo().getValue(GroupProperty.UnitElement))).content();
        
        if(verbose){
            System.out.print(val.toString(s3));
        }

        // s3 has six elements, thus 6 over 2 = 15 pairs
        // the unit combined with anything else does not generate the entire group (-5)
        // the two elements of order three combined do not generate the group (-1)
        // leaves 15-5-1 = 9 pairs of generating elements
        assertEquals(9, content.length);
        for(int i = 0 ; i < content.length; i++){
            
            // count the number of elements
            int count = 0;
            for(int j = 0; j < content[i].length; j++){
                if(content[i][j]){
                    count++;
                }
            }
            assertEquals(2, count);
            
            // the unit element should not be in a set of generators
            assertTrue(!content[i][unit]);
        }
    }
    
    
    
    @Test
    public void S4Test() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group s4 = new Group(fac.getSymmetricGroup(4));
        
        FamilyValue val = (FamilyValue)(s4.getInfo().getValue(GroupProperty.StronglyMinimalGeneratingSets));
        boolean[][] content = val.content();
        int unit = ((IntValue)(s4.getInfo().getValue(GroupProperty.UnitElement))).content();
        
        if(verbose){
            System.out.print(val.toString(s4));
        }

        assertEquals(108, content.length);
        for(int i = 0 ; i < content.length; i++){
            
            // count the number of elements
            int count = 0;
            for(int j = 0; j < content[i].length; j++){
                if(content[i][j]){
                    count++;
                }
            }
            assertEquals(2, count);
            
            // the unit element should not be in a set of generators
            assertTrue(!content[i][unit]);
        }
    }
    
    @Test
    public void C6Test() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        Group c6 = new Group(fac.getCyclicGroup(6));
        
        /*
        <x>, <x^2, x^3>, <x^4, x^3> <x^5> are the four minimal sets of generators
        but <x> and <x^5> are the two strongly minimal sets
        */
        
        FamilyValue val = (FamilyValue)(c6.getInfo().getValue(GroupProperty.StronglyMinimalGeneratingSets));
        boolean[][] content = val.content();
        
        assertEquals(2, content.length);
        
        int n1 = 0;
        int n2 = 0;
        for(int i = 0; i < content.length; i++){
            int count = 0;
            for(int j = 0; j < content[i].length; j++){
                if(content[i][j]){
                    count += 1;
                }
            }
            if(count == 1){
                n1++;
            }
            if(count == 2){
                n2++;
            }
        }
        assertEquals(2, n1);
        assertEquals(0, n2);        
    }
    
    @Test
    public void D3D4Test() throws EvaluationException{
        GroupFactory fac = new GroupFactory();

        Group d3 = new Group(fac.getDihedralGroup(3));
        Group d4 = new Group(fac.getDihedralGroup(4));
        
        
        boolean[][] content3 = ((FamilyValue)(d3.getInfo().getValue(GroupProperty.StronglyMinimalGeneratingSets))).content();
        boolean[][] content4 = ((FamilyValue)(d4.getInfo().getValue(GroupProperty.StronglyMinimalGeneratingSets))).content();
        
        assertEquals(9, content3.length);
        assertEquals(12, content4.length);
    }
    
    
    @Test
    public void MoreThanTwoGeneratorsTest() throws EvaluationException{
        GroupFactory fac = new GroupFactory();
        
        GroupDefinition[] factors = new GroupDefinition[2];
        factors[0] = fac.getDihedralGroup(3);
        factors[1] = fac.getDihedralGroup(4);
        
        Group product = new Group(GroupProduct.of(factors));
        
        FamilyValue val = (FamilyValue)(product.getInfo().getValue(GroupProperty.StronglyMinimalGeneratingSets));
        boolean[][] content = val.content();
        
        if(verbose){
            System.out.print(val.toString(product));
        }
        
        assertEquals(5376, content.length);
        
        int nLess = 0;
        int nThree = 0;
        int nMore = 0;
        for(int i = 0; i < content.length; i++){
            int count = 0;
            for(int j = 0; j < content[i].length; j++){
                if(content[i][j]){
                    count += 1;
                }
            }
            if(count == 3){
                nThree++;
            }
            if(count < 3){
                nLess++;
            }
            if(count > 3){
                nMore++;
            }
        }
        assertEquals(0, nLess);
        assertEquals(0, nMore);  
        assertEquals(content.length, nThree);
    }
}
