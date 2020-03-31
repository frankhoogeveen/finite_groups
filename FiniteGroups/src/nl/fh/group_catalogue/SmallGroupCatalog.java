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
package nl.fh.group_catalogue;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Group;
import nl.fh.group.GroupException;
import nl.fh.group_def_product.GroupProduct;
import nl.fh.group_definition_factory.GroupFactory;
import nl.fh.group_formatter.GroupFormatter;

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
        /* order one */
        Group c2 = factory.getCyclicGroup(2);
        super.add(c2);
        
        /*order three */
        /* order one */
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
        
        super.add(GroupProduct.of(new Group[]{c3, c3}));
        
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
    }
    
    /**
     * main method that prints the entire catalog
     * @param args 
     */
    public static void main(String[] args){
  
        GroupCatalog cat = null;
        try {
            cat = new SmallGroupCatalog();
        } catch (EvaluationException | GroupException ex) {
            System.out.println("could not create SmallGroupCatalog");
            System.exit(-1);
        }
        
        GroupFormatter format = new GroupFormatter();
        String report = format.createReport(cat);
        String fileName = "./out/report.txt";
        
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(report);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(SmallGroupCatalog.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(SmallGroupCatalog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        System.out.println("done writing: " + fileName );
    }
}
