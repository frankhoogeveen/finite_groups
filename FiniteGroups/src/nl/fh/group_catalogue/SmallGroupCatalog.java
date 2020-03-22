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

import java.util.logging.Logger;
import nl.fh.group_definition_factory.GroupFactory;
import nl.fh.group_formatter.GroupFormatter;

/**
 *
 * @author frank
 */
public class SmallGroupCatalog extends GroupCatalog {
    private static final Logger LOGGER = Logger.getLogger(SmallGroupCatalog.class.getSimpleName());
    private final GroupFactory factory;
    
    public SmallGroupCatalog(){
        super();
        this.factory = new GroupFactory();
        
        /* order one */
        super.add(factory.getCyclicGroup(1));

        /* order two */
        super.add(factory.getCyclicGroup(2));
        
        /*order three */
        super.add(factory.getCyclicGroup(3));
        
        /*order four*/
        super.add(factory.getCyclicGroup(4));
        super.add(factory.getAbeleanGroup(new int[]{2,2}));
        
        /* order five */
        super.add(factory.getCyclicGroup(5));
        
        /* order six */
        super.add(factory.getCyclicGroup(6));
        super.add(factory.getSymmetricGroup(3));
        
        /* order seven */
        super.add(factory.getCyclicGroup(7));
        
        /* order eight */
        super.add(factory.getCyclicGroup(8));
        super.add(factory.getAbeleanGroup(new int[]{4,2}));
        super.add(factory.getAbeleanGroup(new int[]{2,2,2}));
        super.add(factory.getDihedralGroup(4));
        super.add(factory.getDicyclicGroup(2));
        
        /* order nine */
        super.add(factory.getCyclicGroup(9));
        super.add(factory.getAbeleanGroup(new int[]{3,3}));
        
        /* order ten */
        super.add(factory.getCyclicGroup(10));
        super.add(factory.getDihedralGroup(5));      
        
        /* order eleven */
        super.add(factory.getCyclicGroup(11));
        
        /* order twelve */
        super.add(factory.getCyclicGroup(12));
        super.add(factory.getAbeleanGroup(new int[]{3,2,2}));
        super.add(factory.getDihedralGroup(6));
        super.add(factory.getDicyclicGroup(3));
        super.add(factory.getAlternatingGroup(4));
        
        /* order thirteen */
        super.add(factory.getCyclicGroup(13));
    }
    
    /**
     * main method that prints the entire catalog
     * @param args 
     */
    public static void main(String[] args){
  
        GroupCatalog cat = new SmallGroupCatalog();
        GroupFormatter format = new GroupFormatter();
        System.out.println(format.createReport(cat));
    }
}
