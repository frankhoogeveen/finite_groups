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
package nl.fh.checker;

import java.util.Map;
import java.util.Set;
import nl.fh.calculator.EvaluationException;
import nl.fh.field.Field;
import nl.fh.field.FieldElement;
import nl.fh.field_calculators.FieldProperty;

/**
 *
 *  Class to check if a field is following the definitions. This check is O(order^3), so
 * it is very expensive for larger fields.
 * 
 * @author frank
 */
public class FieldChecker {

    private boolean verbose;

    /**
     * create a non-verbose FieldChecker
     */
    public FieldChecker(){
        this.verbose = false;
    }
    
    /**
     * 
     * @param verbose If true, this checker will be verbose, when a test fails
     */
    public FieldChecker(boolean verbose){
        this.verbose = verbose;
    }    
    
    public boolean isField(Field field) {
        try{
            checkAdditiveClosed(field);
            checkZeroElement(field);
            checkNegatives(field);
            checkAdditiveAssociativity(field);
            checkAdditiveCommutative(field);
            
            checkMultiplicativeClosed(field);
            checkOneElement(field);
            checkInverses(field);
            checkMultiplicativeAssociativity(field);
            checkMultiplicativeCommutative(field);   
            
            checkDistributivity(field);
            
        } catch(EvaluationException e){
            if(verbose){
                System.out.println("+++ Checking field failed +++");
                System.out.println(field);
                System.out.println(e.getMessage());
            }
            return false;
        }
        return true;
    }

    private void checkAdditiveClosed(Field field) throws EvaluationException {
        Set<FieldElement> set = (Set<FieldElement>) field.getProperty(FieldProperty.ELEMENTS);
        Map<FieldElement, Map<FieldElement, FieldElement>> sum = (Map<FieldElement, Map<FieldElement, FieldElement>>) field.getProperty(FieldProperty.SUMTABLE);
        
        for(FieldElement f1 : set){
            for(FieldElement f2 : set){
                if(!set.contains(sum.get(f1).get(f2))){
                    throw new EvaluationException("field addition not closed");
                }
            }
        }
    }

    private void checkZeroElement(Field field) throws EvaluationException {
        FieldElement zero = (FieldElement) field.getProperty(FieldProperty.ZERO);
        Set<FieldElement> set = (Set<FieldElement>) field.getProperty(FieldProperty.ELEMENTS);
        Map<FieldElement, Map<FieldElement, FieldElement>> sum = (Map<FieldElement, Map<FieldElement, FieldElement>>) field.getProperty(FieldProperty.SUMTABLE);
        
        if(!set.contains(zero)){
            throw new EvaluationException("zero not contained in field set");
        }
        
        for(FieldElement f : set){
            if((!f.equals(sum.get(f).get(zero)) || (!f.equals(sum.get(zero).get(f))))){
                    throw new EvaluationException("field addition with zero incorrect");
            }
        }
    }

    private void checkNegatives(Field field) throws EvaluationException {
        FieldElement zero = (FieldElement) field.getProperty(FieldProperty.ZERO);
        Set<FieldElement> set = (Set<FieldElement>) field.getProperty(FieldProperty.ELEMENTS);
        Map<FieldElement, Map<FieldElement, FieldElement>> sum = (Map<FieldElement, Map<FieldElement, FieldElement>>) field.getProperty(FieldProperty.SUMTABLE);
        
        for(FieldElement f : set){
            FieldElement minusF = f.times(-1);
            if((!zero.equals(sum.get(f).get(minusF))) || (!zero.equals(sum.get(minusF).get(f))) ){
                    throw new EvaluationException("negative incorrect");
            }
        }
    }

    private void checkAdditiveAssociativity(Field field) throws EvaluationException {
        Set<FieldElement> set = (Set<FieldElement>) field.getProperty(FieldProperty.ELEMENTS);
        Map<FieldElement, Map<FieldElement, FieldElement>> sum = (Map<FieldElement, Map<FieldElement, FieldElement>>) field.getProperty(FieldProperty.SUMTABLE);
        
        for(FieldElement f1 : set){
            for(FieldElement f2 : set){
                for(FieldElement f3 : set){
                    FieldElement f12_3 = sum.get(sum.get(f1).get(f2)).get(f3);
                    FieldElement f1_23 = sum.get(f1).get(sum.get(f2).get(f3));
                    
                    if((!f12_3.equals(f1_23))){
                        throw new EvaluationException("addition not associative");
                    }                    
                    
                }
            }
        }
    }

    private void checkAdditiveCommutative(Field field) throws EvaluationException {
        Set<FieldElement> set = (Set<FieldElement>) field.getProperty(FieldProperty.ELEMENTS);
        Map<FieldElement, Map<FieldElement, FieldElement>> sum = (Map<FieldElement, Map<FieldElement, FieldElement>>) field.getProperty(FieldProperty.SUMTABLE);
        
        for(FieldElement f1 : set){
            for(FieldElement f2 : set){
                FieldElement f12 = sum.get(f1).get(f2);
                FieldElement f21 = sum.get(f2).get(f1);

                if((!f12.equals(f21))){
                    throw new EvaluationException("addition not commutative");
                }                    
            }
        }
    }

    private void checkMultiplicativeClosed(Field field) throws EvaluationException {
        Set<FieldElement> set = (Set<FieldElement>) field.getProperty(FieldProperty.ELEMENTS);
        Map<FieldElement, Map<FieldElement, FieldElement>> prod = (Map<FieldElement, Map<FieldElement, FieldElement>>) field.getProperty(FieldProperty.PRODUCTTABLE);
        
        for(FieldElement f1 : set){
            for(FieldElement f2 : set){
                if(!set.contains(prod.get(f1).get(f2))){
                    throw new EvaluationException("field multiplication not closed");
                }
            }
        }
    }

    private void checkOneElement(Field field) throws EvaluationException {
        FieldElement one = (FieldElement) field.getProperty(FieldProperty.ONE);
        Set<FieldElement> set = (Set<FieldElement>) field.getProperty(FieldProperty.ELEMENTS);
        Map<FieldElement, Map<FieldElement, FieldElement>> prod = (Map<FieldElement, Map<FieldElement, FieldElement>>) field.getProperty(FieldProperty.PRODUCTTABLE);
        
        if(!set.contains(one)){
            throw new EvaluationException("one not contained in field set");
        }
        
        for(FieldElement f : set){
            if((!f.equals(prod.get(f).get(one)) || (!f.equals(prod.get(one).get(f))))){
                    throw new EvaluationException("field multiplication with one incorrect");
            }
        }
    }

    private void checkInverses(Field field) throws EvaluationException {
        FieldElement one = (FieldElement) field.getProperty(FieldProperty.ONE);
        Set<FieldElement> set = (Set<FieldElement>) field.getProperty(FieldProperty.ELEMENTS);
        Map<FieldElement, Map<FieldElement, FieldElement>> prod = (Map<FieldElement, Map<FieldElement, FieldElement>>) field.getProperty(FieldProperty.PRODUCTTABLE);
        Map<FieldElement, FieldElement> inv = (Map<FieldElement, FieldElement>) field.getProperty(FieldProperty.INVERSETABLE);
        
        for(FieldElement f : set){
            if(!f.isZero()){
                if((!one.equals(prod.get(f).get(inv.get(f))))||(!one.equals(prod.get(inv.get(f)).get(f)))){
                    throw new EvaluationException("multiplicative inverse incorrect");
                }
            }
        }
    }

    private void checkMultiplicativeAssociativity(Field field) throws EvaluationException {
        Set<FieldElement> set = (Set<FieldElement>) field.getProperty(FieldProperty.ELEMENTS);
        Map<FieldElement, Map<FieldElement, FieldElement>> prod = (Map<FieldElement, Map<FieldElement, FieldElement>>) field.getProperty(FieldProperty.PRODUCTTABLE);
        
        for(FieldElement f1 : set){
            for(FieldElement f2 : set){
                for(FieldElement f3 : set){
                    FieldElement f12_3 = prod.get(prod.get(f1).get(f2)).get(f3);
                    FieldElement f1_23 = prod.get(f1).get(prod.get(f2).get(f3));
                    
                    if((!f12_3.equals(f1_23))){
                        throw new EvaluationException("multiplication not associative");
                    }                    
                }
            }
        }
    }

    private void checkMultiplicativeCommutative(Field field) throws EvaluationException {
        Set<FieldElement> set = (Set<FieldElement>) field.getProperty(FieldProperty.ELEMENTS);
        Map<FieldElement, Map<FieldElement, FieldElement>> product = (Map<FieldElement, Map<FieldElement, FieldElement>>) field.getProperty(FieldProperty.PRODUCTTABLE);
        
        for(FieldElement f1 : set){
            for(FieldElement f2 : set){
                FieldElement f12 = product.get(f1).get(f2);
                FieldElement f21 = product.get(f2).get(f1);

                if((!f12.equals(f21))){
                    throw new EvaluationException("multiplication not commutative");
                }                    
            }
        }
    }

    private void checkDistributivity(Field field) throws EvaluationException {
        Set<FieldElement> set = (Set<FieldElement>) field.getProperty(FieldProperty.ELEMENTS);
        Map<FieldElement, Map<FieldElement, FieldElement>> sum = (Map<FieldElement, Map<FieldElement, FieldElement>>) field.getProperty(FieldProperty.SUMTABLE);
        Map<FieldElement, Map<FieldElement, FieldElement>> prod = (Map<FieldElement, Map<FieldElement, FieldElement>>) field.getProperty(FieldProperty.PRODUCTTABLE);
        
        for(FieldElement f1 : set){
            for(FieldElement f2 : set){
                for(FieldElement f3 : set){
                    FieldElement f1_23 = prod.get(f1).get(sum.get(f2).get(f3));
                    FieldElement f12_13 = sum.get(prod.get(f1).get(f2)).get(prod.get(f1).get(f3));
                    
                    if((!f1_23.equals(f12_13))){
                        throw new EvaluationException("multiplication not distributive");
                    }                    
                }
            }
        }
    }
    
}
