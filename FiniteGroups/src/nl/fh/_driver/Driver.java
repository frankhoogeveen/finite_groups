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
package nl.fh._driver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.GroupException;
import nl.fh.group_catalogue.GroupCatalog;
import nl.fh.group_catalogue.SmallGroupCatalog;
import nl.fh.group_formatter.GroupFormatter;

/**
 *
 * @author frank
 */
public class Driver {
    /**
     * main method that prints the entire catalog
     * @param args 
     */
    public static void main(String[] args){
  
        GroupCatalog cat = null;
        try {
            cat = new SmallGroupCatalog();
        } catch (EvaluationException | GroupException ex) {
            System.out.println("could not create Catalog");
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
