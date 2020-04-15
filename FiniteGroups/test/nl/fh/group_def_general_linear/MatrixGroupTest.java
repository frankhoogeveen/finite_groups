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
package nl.fh.group_def_general_linear;

import java.util.Set;
import nl.fh.calculator.EvaluationException;
import nl.fh.factory.FieldFactory;
import nl.fh.group.Group;
import nl.fh.group_calculators.GroupProperty;
import nl.fh.factory.GroupFactory;
import nl.fh.field.Field;
import nl.fh.field.FieldElement;
import nl.fh.field_calculators.FieldProperty;
import nl.fh.group.Element;
import nl.fh.group.Multiplicator;
import nl.fh.group_classifier.GroupClassifier;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class MatrixGroupTest {
    
    @Test
    public void MatrixMultiplicationTest() throws EvaluationException{
        FieldFactory ff = new FieldFactory();
        Field F2 = ff.getField(2);
        
        FieldElement unit = (FieldElement) F2.getProperty(FieldProperty.ONE);
        FieldElement zero = (FieldElement) F2.getProperty(FieldProperty.ZERO);
        
        FieldElement[][] me = new FieldElement[][]{new FieldElement[]{unit, zero},new FieldElement[]{zero, unit}};
        FieldElement[][] mx = new FieldElement[][]{new FieldElement[]{zero, unit},new FieldElement[]{unit, zero}};
        FieldElement[][] ma = new FieldElement[][]{new FieldElement[]{unit, unit},new FieldElement[]{zero, unit}};
        FieldElement[][] mb = new FieldElement[][]{new FieldElement[]{unit, unit},new FieldElement[]{unit, zero}};
        FieldElement[][] mc = new FieldElement[][]{new FieldElement[]{unit, zero},new FieldElement[]{unit, unit}};
        FieldElement[][] md = new FieldElement[][]{new FieldElement[]{zero, unit},new FieldElement[]{unit, unit}};        
        
        MatrixElement e = new MatrixElement(me);
        MatrixElement x = new MatrixElement(mx);
        MatrixElement a = new MatrixElement(ma); 
        MatrixElement b = new MatrixElement(mb);  
        MatrixElement c = new MatrixElement(mc);  
        MatrixElement d = new MatrixElement(md);          
        
        Multiplicator mult = new MatrixMultiplicator();
        
        assertEquals(e, mult.getProduct(e, e));
        assertEquals(x, mult.getProduct(e, x));
        assertEquals(e, mult.getProduct(a, a));
        assertEquals(d, mult.getProduct(b, b));
        assertEquals(e, mult.getProduct(d, b));
        assertEquals(d, mult.getProduct(c, x));        
        
    }
    
    
    
    @Test
    public void GL2F2CreationTest() throws EvaluationException{
        GroupFactory factory = new GroupFactory();
        
        Group group = factory.getGL(2,2);   //GL(2,F2)
        
        int order = (int) group.getProperty(GroupProperty.Order);
        assertEquals(6, order);
        
        GroupClassifier classifier = new GroupClassifier();
        assertEquals("S3", classifier.identify(group));
    }
    
    @Test
    public void GL2CreationTest() throws EvaluationException{
        GroupFactory factory = new GroupFactory();
        
        Group f2 = factory.getGL(2,2);   //GL(2,F2)    
        int order = (int) f2.getProperty(GroupProperty.Order);
        assertEquals(6, order);
        
        Group f3 = factory.getGL(2,3);   //GL(2,F3)    
        order = (int) f3.getProperty(GroupProperty.Order);
        assertEquals((9-1)*(9-3), order);
        
        Group f4 = factory.getGL(2,4);   //GL(2,F4)    
        order = (int) f4.getProperty(GroupProperty.Order);
        assertEquals((16-1)*(16-4), order);
        
        Group f5 = factory.getGL(2,5);   //GL(2,F5)    
        order = (int) f5.getProperty(GroupProperty.Order);
        assertEquals((25-1)*(25-5), order);
        
//        Group f7 = factory.getGL(2,7);   //GL(2,F7)    
//        order = (int) f7.getProperty(GroupProperty.Order);
//        assertEquals((49-1)*(49-7), order);        
    }
    
    @Test
    public void GL3CreationTest() throws EvaluationException{
        GroupFactory factory = new GroupFactory();
        
        Group f2 = factory.getGL(3,2);   //GL(3,F2)    
        int order = (int) f2.getProperty(GroupProperty.Order);
        assertEquals(168, order);
        
//        Group f3 = factory.getGL(3,3);   //GL(3,F3)    
//        order = (int) f3.getProperty(GroupProperty.Order);
//        assertEquals(11232, order);
    }
    
    @Test
    public void SL2CreationTest() throws EvaluationException{
        GroupFactory factory = new GroupFactory();
        int order;
        
        Group f2 = factory.getSL(2,2);   //SL(2,F2)                 
        order = (int) f2.getProperty(GroupProperty.Order);
        assertEquals((4-1)*(4-2)/(2-1), order);
        
        Group f3 = factory.getSL(2,3);   //SL(2,F3)    
        order = (int) f3.getProperty(GroupProperty.Order);
        assertEquals((9-1)*(9-3)/(3-1), order);
        
        Group f4 = factory.getSL(2,4);   //SL(2,F4)    
        order = (int) f4.getProperty(GroupProperty.Order);
        assertEquals((16-1)*(16-4)/(4-1), order);
        
        Group f5 = factory.getSL(2,5);   //SL(2,F5)    
        order = (int) f5.getProperty(GroupProperty.Order);
        assertEquals((25-1)*(25-5)/(5-1), order);
        
//        Group f7 = factory.getGL(2,7);   //SL(2,F7)    
//        order = (int) f7.getProperty(GroupProperty.Order);
//        assertEquals((49-1)*(49-7), order);        
    }
}
