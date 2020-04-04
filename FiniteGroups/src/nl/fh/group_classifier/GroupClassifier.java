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
package nl.fh.group_classifier;

import java.util.Map;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Group;
import nl.fh.group_calculators.GroupProperty;

/**
 * This class has as to concern to identify a group.
 * 
 * 
 * @author frank
 */
public class GroupClassifier {

    private static GroupClassifier instance;

    private String UNKNOWN = "unidentified";  // this could be a group, but we dont know which one
    private String ERROR = "error";  // something is definitely wrong
    
    /**
     * 
     * @return an instance of GroupClassifier
     */
    public static GroupClassifier getInstance() {
        if(GroupClassifier.instance == null){
            GroupClassifier.instance = new GroupClassifier();
        }
        return GroupClassifier. instance;
    }
    /**
     * 
     * @param group A Group. It is not explicitely check that group is well defined.
     * @return A String identifying the type of group
     * @throws EvaluationException 
     * 
     * In case the argument group is not well defined, the behavior of this method 
     * cannot be predicted. It is not guaranteed that an exception is thrown.
     */
    public String identify(Group group) throws EvaluationException{
        int order = (int) group.getProperty(GroupProperty.Order);
        
        switch(order){
            case 1  : return "C1";
            case 2  : return "C2";
            case 3  : return "C3";
            case 4  : return classify4(group);
            case 5  : return "C5";
            case 6  : return classify6(group);
            case 7  : return "C7";
            case 8  : return classify8(group);
            case 9  : return classify9(group);
            case 10 : return classify10(group);
            case 11 : return "C11";
            case 12 : return classify12(group);
            case 13 : return "C13";
            case 14 : return classify14(group);
            case 15 : return "C15";
            case 16 : return classify16(group);
            case 17 : return "C17";
            case 18 : return classify18(group);        
            case 19 : return "C19";
            case 20 : return classify20(group);                       
            
            default : return UNKNOWN;
        }
    }
    
    /**
     * 
     * @param order the order of the elements in the conjugation class
     * @param size  the size of the conjugation class
     * @param profile
     * @return profile.get(order).get(size), i.e. the number of conjugation classes with the required properties
     */
    private int getCount(int order, int size, Map<Integer, Map<Integer, Integer>> profile) {
        if(!profile.containsKey(order)){
            return 0;
        }
        
        if(!profile.get(order).containsKey(size)){
            return 0;
        }
        
        return profile.get(order).get(size);
    }

    private String classify4(Group group) throws EvaluationException {
        Map<Integer, Map<Integer, Integer>> profile = ( Map<Integer, Map<Integer, Integer>>)group.getProperty(GroupProperty.ConjugationProfile);
        int order = 2;
        int size = 1;
        int count = getCount(order, size, profile);
        if(count == 3){
            return "C2xC2";
        } else {
            return "C4";
        }
    }

    private String classify6(Group group) throws EvaluationException {
        Map<Integer, Map<Integer, Integer>> profile = ( Map<Integer, Map<Integer, Integer>>)group.getProperty(GroupProperty.ConjugationProfile);
        
        if(getCount(3, 2, profile) == 1){ return "S3";}
        if(getCount(6, 1, profile) == 2){ return "C6";}
        
        return ERROR;
    }

    private String classify8(Group group) throws EvaluationException {
        Map<Integer, Map<Integer, Integer>> profile = ( Map<Integer, Map<Integer, Integer>>)group.getProperty(GroupProperty.ConjugationProfile);
        
        if(getCount(8, 1, profile) == 4){ return "C8";}
        if(getCount(4, 1, profile) == 4){ return "C4xC2";}
        if(getCount(2, 1, profile) == 7){ return "C2xC2xC2";}
        if(getCount(4, 2, profile) == 1){ return "D4";}
        if(getCount(4, 2, profile) == 3){ return "Q2";}
        
        return ERROR;
    }

    private String classify9(Group group) throws EvaluationException {
        Map<Integer, Map<Integer, Integer>> profile = ( Map<Integer, Map<Integer, Integer>>)group.getProperty(GroupProperty.ConjugationProfile);
        
        if(getCount(9, 1, profile) == 6){ return "C9";}
        if(getCount(3, 1, profile) == 8){ return "C3xC3";}
        
        return ERROR;
    }

    private String classify10(Group group) throws EvaluationException {
        Map<Integer, Map<Integer, Integer>> profile = ( Map<Integer, Map<Integer, Integer>>)group.getProperty(GroupProperty.ConjugationProfile);
        
        if(getCount(10, 1, profile) == 4){ return "C10";}
        if(getCount(5, 2, profile) == 2){ return "D5";}
        
        return ERROR;
    }
    
    private String classify12(Group group) throws EvaluationException {
        Map<Integer, Map<Integer, Integer>> profile = ( Map<Integer, Map<Integer, Integer>>)group.getProperty(GroupProperty.ConjugationProfile);
        
        if(getCount(12, 1, profile) == 4){ return "C12";}
        if(getCount(6, 1, profile) == 6){ return "C3xC2xC2";}
        if(getCount(2, 3, profile) == 2){ return "D6";}
        if(getCount(4, 3, profile) == 2){ return "Q3";}
        if(getCount(3, 4, profile) == 2){ return "A4";}  
        
        return ERROR;
    } 
    
    private String classify14(Group group) throws EvaluationException {
        Map<Integer, Map<Integer, Integer>> profile = ( Map<Integer, Map<Integer, Integer>>)group.getProperty(GroupProperty.ConjugationProfile);
        
        if(getCount(14, 1, profile) == 6){ return "C14";}
        if(getCount(7, 2, profile) == 3){ return "D7";}
        
        return ERROR;
    }
    
    private String classify16(Group group) throws EvaluationException {
        Map<Integer, Map<Integer, Integer>> profile = ( Map<Integer, Map<Integer, Integer>>)group.getProperty(GroupProperty.ConjugationProfile);
        
        if(getCount(16, 1, profile) == 8){ return "C16";}
        if(getCount(8, 1, profile) == 8){ return "C8xC2";}
        if(getCount(4, 1, profile) == 12){ return "C4xC4";}
        if(getCount(4, 1, profile) == 8){ return "C4xC2xC2";}
        if(getCount(2, 1, profile) == 15){ return "C2xC2xC2xC2";}  
        if(getCount(4, 2, profile) == 2){ return "D4xC2";}
        if(getCount(2, 4, profile) == 2){ return "D8";}
        if(getCount(4, 4, profile) == 2){ return "Q4";}
        if(getCount(8, 2, profile) == 4){ return "M4";}
        if(getCount(4, 4, profile) == 1){ return "SD4";}  
        if(getCount(4, 2, profile) == 4){ return "G16_1";}
        if(getCount(4, 2, profile) == 3){ return "G16_2";}
        if(getCount(4, 2, profile) == 6){ return classifySubcase16(group);}

        return ERROR;
    } 

    private String classifySubcase16(Group group) throws EvaluationException {
        Group ab = (Group) group.getProperty(GroupProperty.Abelianization);
        switch(identify(ab)){
            case "C2xC2xC2" : return "Q2xC2";
            case "C4xC2"    : return "MC(4,4,3)";
        }
        return ERROR;
    }

    private String classify18(Group group) throws EvaluationException {
        Map<Integer, Map<Integer, Integer>> profile = ( Map<Integer, Map<Integer, Integer>>)group.getProperty(GroupProperty.ConjugationProfile);
        
        if(getCount(18, 1, profile) == 6){ return "C18";}
        if(getCount(9, 2, profile) == 3){ return "D9";}
        if(getCount(6, 1, profile) == 8){ return "C3xC3xC2";}
        if(getCount(6, 3, profile) == 2){ return "C3xS3";}
        if(getCount(3, 2, profile) == 4){ return "C2⋊C3xC3";}  

        return ERROR;
    }
    
    private String classify20(Group group) throws EvaluationException {
        Map<Integer, Map<Integer, Integer>> profile = ( Map<Integer, Map<Integer, Integer>>)group.getProperty(GroupProperty.ConjugationProfile);
        
        if(getCount(20, 1, profile) == 8){ return "C20";}
        if(getCount(2, 5, profile) == 2){ return "D10";}
        if(getCount(10, 1, profile) == 12){ return "C10xC2";}
        if(getCount(2, 5, profile) == 1){ return "MC(5,4,2)";}  

        return "Q5";
    }    
}
