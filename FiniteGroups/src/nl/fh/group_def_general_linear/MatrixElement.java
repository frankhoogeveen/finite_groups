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

import java.util.Arrays;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fh.calculator.EvaluationException;
import nl.fh.field.Field;
import nl.fh.field.FieldElement;
import nl.fh.field.Polynomial;
import nl.fh.field_calculators.FieldProperty;
import nl.fh.group.Element;

/**
 * Represent a matrix of field elements as Element of a linear group
 * 
 * 
 * @author frank
 */
public class MatrixElement implements Element {

    private final FieldElement[][] matrix;
    private final Field field;
    private final int size;
    
    /**
     * 
     * @param matrix a square matrix of field elements, all with the same characteristic 
     * (i.e. the prime modulus used)
     * 
     */
    public MatrixElement(FieldElement[][] matrix) {
        check(matrix);
        
        this.matrix = matrix;
        this.size = matrix.length;
        this.field = matrix[0][0].getField();
    }

    FieldElement[][] getMatrix() {
        return matrix;
    }

    /**
     * 
     * @return  the dimension of the matrix
     */
    public int getSize() {
        return this.size;
    }
    
    /**
     * 
     * @return the field over which this matrix is defined
     */
    public Field getField(){
        return matrix[0][0].getField();
    }
    
    /**
     * 
     * @param other
     * @return the matrix sum of this and other
     */
    public MatrixElement plus(MatrixElement other){
        checkCompatibility(other);
        
        try {
            Map<FieldElement, Map<FieldElement, FieldElement>> sum = (Map<FieldElement, Map<FieldElement, FieldElement>>) this.field.getProperty(FieldProperty.SUMTABLE);
            
            FieldElement[][] m = new FieldElement[size][size];
            for(int i = 0; i < size; i++){
                for(int j = 0; j < size; j++){
                    m[i][j] = sum.get(this.matrix[i][j]).get(other.matrix[i][j]);
                }
            }
        
            return new MatrixElement(m);
            
            
        } catch (EvaluationException ex) {
            String mess = "error in matrix addition";
            Logger.getLogger(MatrixElement.class.getName()).log(Level.SEVERE, mess, ex);
            throw new IllegalStateException(mess);
        }
    }
    
    /**
     * 
     * @param scalar
     * @return scalar times this
     */
    public MatrixElement times(FieldElement scalar){
        try {
            Map<FieldElement, Map<FieldElement, FieldElement>> sum = (Map<FieldElement, Map<FieldElement, FieldElement>>) this.field.getProperty(FieldProperty.SUMTABLE);
            Map<FieldElement, Map<FieldElement, FieldElement>> prod = (Map<FieldElement, Map<FieldElement, FieldElement>>) this.field.getProperty(FieldProperty.PRODUCTTABLE);             
            
            
            FieldElement[][] m = new FieldElement[size][size];
            for(int i = 0; i < size; i++){
                for(int j = 0; j < size; j++){
                    m[i][j] = prod.get(scalar).get(this.matrix[i][j]);
                }
            }
        
            return new MatrixElement(m);
            
        } catch (EvaluationException ex) {
            String mess = "error in matrix scalar multiplication";
            Logger.getLogger(MatrixElement.class.getName()).log(Level.SEVERE, mess, ex);
            throw new IllegalStateException(mess);
        }  
    }
    
    /**
     * 
     * @param other
     * @return  the matrix product this times other 
     */
    public MatrixElement times(MatrixElement other){
        checkCompatibility(other);
        
        try {
            FieldElement zero = (FieldElement) this.field.getProperty(FieldProperty.ZERO);
            
            Map<FieldElement, Map<FieldElement, FieldElement>> sum = (Map<FieldElement, Map<FieldElement, FieldElement>>) this.field.getProperty(FieldProperty.SUMTABLE);
            Map<FieldElement, Map<FieldElement, FieldElement>> prod = (Map<FieldElement, Map<FieldElement, FieldElement>>) this.field.getProperty(FieldProperty.PRODUCTTABLE);            
            
            FieldElement[][] m = new FieldElement[size][size];
            for(int i = 0; i < size; i++){
                for(int j = 0; j < size; j++){
                    m[i][j] = zero;
                    for(int k = 0; k < size; k++){
                        m[i][j] = sum.get(m[i][j]).get(prod.get(this.matrix[i][k]).get(other.matrix[k][j]));
                    }
                }
            }
        
            return new MatrixElement(m);
            
            
        } catch (EvaluationException ex) {
            String mess = "error in matrix multiplication";
            Logger.getLogger(MatrixElement.class.getName()).log(Level.SEVERE, mess, ex);
            throw new IllegalStateException(mess);
        }
    }
    
    /**
     * Checks that the matrix is square and the prime modulus is the same
     * for all matrix elements
     * 
     * 
     * @param matrix 
     */
    private void check(FieldElement[][] matrix) {
        
        int length = matrix.length;
        
        if(length < 1){
            throw new IllegalArgumentException("matrix dimension should be positive");
        }
        for(int i = 0; i < length; i++){
            if(matrix[i].length != length){
                throw new IllegalArgumentException("matrix should be square");
            }
        }
        
        Field field = matrix[0][0].getField();
        for(int i = 0; i < length; i++){
            for(int j = 0; j < length; j++){
                if(matrix[i][j].getField() != field){
                    throw new IllegalArgumentException("matrix should use unique field");
                }
            }
        }
    }
    
    /**
     * 
     * @param other 
     * 
     * Halts execution when an attempt is made to add or multiply two incompatible matrices
     */
    private void checkCompatibility(MatrixElement other) {
        if(this.size != other.size){
            throw new IllegalStateException("matrices should have the same size");
        }
        
        if(!this.field.equals(other.field)){
            throw new IllegalStateException("matrices should be based on the same field");
        }
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        String linePrefix = "[";
        
        for(int i = 0; i < size; i++){
            sb.append(linePrefix);
            linePrefix = " ";
            String itemPrefix = "";
            sb.append("[");
            for(int j = 0; j < size; j++){
                sb.append(itemPrefix);
                itemPrefix = ", ";
                sb.append(matrix[i][j].toString());
            }
            sb.append("]");
            if( i != (size -1)){
                sb.append(",");
            } else {
                sb.append("]");
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }
        
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Arrays.deepHashCode(this.matrix);
        hash = 23 * hash + this.size;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MatrixElement other = (MatrixElement) obj;
        if (this.size != other.size) {
            return false;
        }
        if (!Arrays.deepEquals(this.matrix, other.matrix)) {
            return false;
        }
        return true;
    }

    /**
     * 
     * This method is calculating the determinant using Laplace's algorithm.
     * This it not efficient for matrices that are not small.
     * 
     * @return the determinant of this matrix 
     */
    public FieldElement det() {

        // the terminating case
        if(this.size == 1){
            return this.matrix[0][0];
        }
        
        // the recursion
        FieldElement result = new FieldElement(new Polynomial(new int[]{0}), this.field);
        
         try {
            Map<FieldElement, Map<FieldElement, FieldElement>> sum = (Map<FieldElement, Map<FieldElement, FieldElement>>) this.field.getProperty(FieldProperty.SUMTABLE);
            Map<FieldElement, Map<FieldElement, FieldElement>> prod = (Map<FieldElement, Map<FieldElement, FieldElement>>) this.field.getProperty(FieldProperty.PRODUCTTABLE);             
            
            int sign = +1;
            for(int i = 0; i < this.size; i++){
                FieldElement term = prod.get(this.matrix[0][i]).get(this.getMinor(0,i).det());
                term = term.times(sign);
                result = sum.get(result).get(term);
                sign = -sign;
            }
        
        return result;            

            
        } catch (EvaluationException ex) {
            String mess = "error in determinant";
            Logger.getLogger(MatrixElement.class.getName()).log(Level.SEVERE, mess, ex);
            throw new IllegalStateException(mess);
        } 
    }

    
    /**
     * 
     * @param n
     * @param m
     * @return the (n,m) minor, i.e. the matrix with row n and column m removed 
     */
    private MatrixElement getMinor(int n, int m) {
        FieldElement[][] minor = new FieldElement[size-1][size-1];
        for(int row = 0; row < size-1; row++){
            for(int col = 0; col < size -1; col++){
                int irow = (row < n)? 0 : 1;
                int icol = (col < m)? 0 : 1;
                minor[row][col] = this.matrix[row+irow][col+icol];
            }
        }
        return new MatrixElement(minor);
    }
}
