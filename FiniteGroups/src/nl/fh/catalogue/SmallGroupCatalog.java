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
package nl.fh.catalogue;

import java.util.logging.Logger;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Group;
import nl.fh.group.GroupException;
import nl.fh.group_def_product.GroupProduct;
import nl.fh.factory.GroupFactory;

/**
 *
 * @author frank
 */
public class SmallGroupCatalog extends GroupCatalog {
    private static final Logger LOGGER = Logger.getLogger(SmallGroupCatalog.class.getSimpleName());
    private final GroupFactory factory;
    
    public SmallGroupCatalog() throws EvaluationException, GroupException{
        super();
        this.factory = new GroupFactory();
        
        /* order one */
        Group c1 = factory.getCyclicGroup(1);
        super.add(c1);

        /* order two */
        Group c2 = factory.getCyclicGroup(2);
        super.add(c2);
        
        /*order three */
        Group c3 = factory.getCyclicGroup(3);
        super.add(c3);
        
        /*order four*/
        Group c4 = factory.getCyclicGroup(4);
        super.add(c4);
        super.add(GroupProduct.of(new Group[]{c2, c2}));
        
        /* order five */
        Group c5 = factory.getCyclicGroup(5);
        super.add(c5);
        
        /* order six */
        Group c6 = factory.getCyclicGroup(6);
        super.add(c6);
        
        Group s3 = factory.getSymmetricGroup(3);
        super.add(s3);
        
        /* order seven */
        Group c7 = factory.getCyclicGroup(7);
        super.add(c7);
        
        /* order eight */
        Group c8 = factory.getCyclicGroup(8);
        super.add(c8);
        
        super.add(GroupProduct.of(new Group[]{c4, c2}));
        super.add(GroupProduct.of(new Group[]{c2, c2, c2}));
        
        Group d4 = factory.getDihedralGroup(4);
        super.add(d4);
        
        Group q2 = factory.getDicyclicGroup(2);
        super.add(q2);
        
        /* order nine */
        Group c9 = factory.getCyclicGroup(9);
        super.add(c9);
        
        Group c3xc3 = GroupProduct.of(new Group[]{c3, c3});
        super.add(c3xc3);
        
        /* order ten */
        Group c10 = factory.getCyclicGroup(10);
        super.add(c10);
        
        Group d5 = factory.getDihedralGroup(5);
        super.add(d5);      
        
        /* order eleven */
        Group c11 = factory.getCyclicGroup(11);
        super.add(c11);
        
        /* order twelve */
        Group c12 = factory.getCyclicGroup(12);
        super.add(c12);
        
        super.add(GroupProduct.of(new Group[]{c3, c2, c2}));
        
        Group d6 = factory.getDihedralGroup(6);
        super.add(d6);
        
        Group q3 = factory.getDicyclicGroup(3);
        super.add(q3);
        
        Group a4 = factory.getAlternatingGroup(4);
        super.add(a4);
        
        /* order thirteen */
        Group c13 = factory.getCyclicGroup(13);
        super.add(c13);
        
        /* order fourteen */
        Group c14 = factory.getCyclicGroup(14);
        super.add(c14);
        
        Group d7 = factory.getDihedralGroup(7);
        super.add(d7);
        
        /* order fifteen */
        Group c15 = factory.getCyclicGroup(15);
        super.add(c15);
        
        /* order sixteen */
        Group c16 = factory.getCyclicGroup(16);
        super.add(c16);
        
        super.add(GroupProduct.of(new Group[]{c8, c2}));
        super.add(GroupProduct.of(new Group[]{c4, c4}));
        super.add(GroupProduct.of(new Group[]{c4, c2, c2}));
        super.add(GroupProduct.of(new Group[]{c2, c2, c2, c2}));        
        
        super.add(GroupProduct.of(new Group[]{d4, c2}));
        super.add(GroupProduct.of(new Group[]{q2, c2}));
        
        Group d8 = factory.getDihedralGroup(8);
        super.add(d8);
        
        Group q4 = factory.getDicyclicGroup(4);
        super.add(q4);
        
        Group m4 = factory.getModular2Group(4);
        super.add(m4);
        
        Group sd4 = factory.getSemiDihedralGroup(4);
        super.add(sd4);
        
        Group mc443 = factory.getMetacyclicGroup(4,4,3);
        super.add(mc443);
        
        Group g16_1 = factory.getGroup16_1();
        super.add(g16_1);
        
        Group g16_2 = factory.getGroup16_2();
        super.add(g16_2);
        
        /* order seventeen */
        Group c17 = factory.getCyclicGroup(17);
        super.add(c17);
        
        /* order eightteen */
        Group c18 = factory.getCyclicGroup(18);
        super.add(c18);

        super.add(GroupProduct.of(new Group[]{c3, c3, c2})); 
        
        super.add(GroupProduct.of(new Group[]{c3, s3})); 
        
        Group d9 = factory.getDihedralGroup(9);
        super.add(d9);
        
        Group dihc3xc3 = factory.getGeneralizedDihedral(c3xc3);
        super.add(dihc3xc3);
        
        /* order nineteen */
        Group c19 = factory.getCyclicGroup(19);
        super.add(c19);    
        
        /* order twenty */
        Group c20 = factory.getCyclicGroup(20);
        super.add(c20);   
        
        super.add(GroupProduct.of(new Group[]{c10, c2})); 
        
        Group d10 = factory.getDihedralGroup(10);
        super.add(d10);
        
        Group q5 = factory.getDicyclicGroup(5);
        super.add(q5);
        
        Group mc542 = factory.getMetacyclicGroup(5,4,2);
        super.add(mc542);
        
        /* order twenty one */
        Group c21 = factory.getCyclicGroup(21);
        super.add(c21);
        
        Group mc732 = factory.getMetacyclicGroup(7,3,2);
        super.add(mc732);
        
        /* order twenty two */
        Group c22 = factory.getCyclicGroup(22);
        super.add(c22);
        
        Group d11 = factory.getDihedralGroup(11);
        super.add(d11);
        
        /* order twenty three */
        Group c23 = factory.getCyclicGroup(23);
        super.add(c23);
        
        /* order twenty four */
        Group c24 = factory.getCyclicGroup(24);
        super.add(c24);
        
        super.add(GroupProduct.of(new Group[]{c4, c3, c2})); 
        
        super.add(GroupProduct.of(new Group[]{c3, c2, c2, c2}));    
        
        super.add(GroupProduct.of(new Group[]{c2, d6})); 

        super.add(GroupProduct.of(new Group[]{c2, q3}));  
        
        super.add(GroupProduct.of(new Group[]{c2, a4})); 

        super.add(GroupProduct.of(new Group[]{c3, d4})); 
        
        super.add(GroupProduct.of(new Group[]{c3, q2}));          

        super.add(GroupProduct.of(new Group[]{c4, s3}));   

        Group s4 = factory.getSymmetricGroup(4);
        super.add(s4);
        
        Group d12 = factory.getDihedralGroup(12);
        super.add(d12);
        
        Group q6 = factory.getDicyclicGroup(6);
        
        Group sl23 = factory.getSL(2,3);
        
        Group mc382 = factory.getMetacyclicGroup(3,8,2);
        super.add(mc382);
        
        Group g24 = factory.getGroup24_1();
        super.add(g24);
        
        
    }
}
