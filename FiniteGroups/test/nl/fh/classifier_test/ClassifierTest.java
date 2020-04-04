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
package nl.fh.classifier_test;

import nl.fh.calculator.EvaluationException;
import nl.fh.group.Group;
import nl.fh.group.GroupException;
import nl.fh.group_calculators.GroupProperty;
import nl.fh.group_catalogue.GroupCatalog;
import nl.fh.group_catalogue.SmallGroupCatalog;
import nl.fh.group_classifier.GroupClassifier;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class ClassifierTest {
    
    @Test
    public void SmallGroupTest() throws EvaluationException, GroupException{
        GroupClassifier classifier = new GroupClassifier();
        
        GroupCatalog cat = new SmallGroupCatalog();
        for(Group g : cat){
            // System.out.println(g.toString());
            String groupName = (String) g.getProperty(GroupProperty.Name);
            String classified = classifier.identify(g);
            
            assertEquals(groupName, classified);
        }
    }
}
