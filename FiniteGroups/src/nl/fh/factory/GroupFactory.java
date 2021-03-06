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
package nl.fh.factory;

import nl.fh.group_def_general_linear.MatrixElement;
import nl.fh.group_def_general_linear.MatrixMultiplicator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fh.calculator.EvaluationException;
import nl.fh.field.Field;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.GroupException;
import nl.fh.group.Multiplicator;
import nl.fh.group_calculators.GroupProperty;
import nl.fh.catalogue.SmallGroupCatalog;
import nl.fh.field.FieldElement;
import nl.fh.field_calculators.FieldProperty;
import nl.fh.group_def_cyclic.CyclicElement;
import nl.fh.group_def_cyclic.CyclicMultiplicator;
import nl.fh.group_def_permutation.PermutationElement;
import nl.fh.group_def_permutation.PermutationMultiplicator;
import nl.fh.group_def_product.GroupProduct;
import nl.fh.group_def_substitutions.StringElement;
import nl.fh.group_def_substitutions.StringMultiplicator;
import nl.fh.group_def_substitutions.StringSubstitution;
import nl.fh.number.IntNumber;

/**
 * Factory object for groups. The concern of this class is to generate
 * families of groups, such as all cyclic groups, all GL(n, F),.....
 * 
 * @author frank
 */
public class GroupFactory {
    private static final Logger LOGGER = Logger.getLogger(SmallGroupCatalog.class.getSimpleName());
    
    
    
    
     /**
     * 
     * @param n the order
     * @return the result of the cyclic group of order n 
     */
    public Group getCyclicGroup(int n) {
        if(n < 1){
            String mess = "cannot not define cyclic group of order " + Integer.toString(n);
            LOGGER.log(Level.SEVERE, mess);
            throw new IllegalArgumentException(mess);
        }
        
        Set<Element> generators = new HashSet<Element>();
        generators.add(CyclicElement.generatorOfOrder(n));
        
        Multiplicator multiplication = new CyclicMultiplicator(n);
        
        String name = "C" + Integer.toString(n);
        
                
        Group group = null;
        try {
            group = new Group(name, generators, multiplication);
        } catch (GroupException ex) {
            Logger.getLogger(GroupFactory.class.getName()).log(Level.SEVERE, "could not create cyclic group", ex);
            System.exit(-1);
        }
        return group;
        
    }

    /**
    * @param orders the orders of the cyclic factors
    * @return the result of the product of several cyclic groups
    */
    public Group getAbeleanGroup(int[] orders)  {
        List<Group> defs = new ArrayList<Group>();
        
        for(int i = 0; i < orders.length; i++){
            defs.add(getCyclicGroup(orders[i]));
        }

       Group result = null;
        try {
            result =  GroupProduct.of(defs);
        } catch (EvaluationException | GroupException ex) {
            Logger.getLogger(GroupFactory.class.getName()).log(Level.SEVERE, "could not create abelean group", ex);
            System.exit(-1);
        } 
        return result;
    }
    
    /**
     * 
     * @param n
     * @return the result of the symmetric group of order n!
     */
    public Group getSymmetricGroup(int n) {
        if(n < 1){
            throw new IllegalArgumentException(" cannot define S_n for n < 1");
        }
        
        int[] cycle = new int[n];
        int[] flip = new int[n];
        for(int i = 0; i < n; i++){
            cycle[i] = i+1;
            flip[i] = i;
        }
        cycle[n-1] = 0;
        flip[0] = 1;
        flip[1] = 0;
        
        Set<Element> generators = new HashSet<Element>();
        generators.add(new PermutationElement(flip));
        generators.add(new PermutationElement(cycle));
        
        Multiplicator multiplication = new PermutationMultiplicator(n);
        
        String name = "S"+Integer.toString(n);
        
        Group result = null;
        try {
            result =  new Group(name, generators, multiplication);
        } catch (GroupException ex) {
            Logger.getLogger(GroupFactory.class.getName()).log(Level.SEVERE, "could not create symmetric group", ex);
            System.exit(-1);
        } 
        return result;
    }

    /**
     * 
     * @param n
     * @return the Alternating group A_n 
     */
    public Group getAlternatingGroup(int n){
        
        Group Sn = getSymmetricGroup(n);
        
        Set<Element> set = new HashSet<Element>();
        for(Element g : Sn){
            PermutationElement pg = (PermutationElement) g;
            if(pg.sign() > 0){
                set.add(g);
            }
        }
        
        String name = "A"+Integer.toString(n);
        
        Group result = null;
        try {
            Multiplicator multiplication = (Multiplicator)Sn.getProperty(GroupProperty.MultiplicationTable);
            result =  new Group(name, set, multiplication);
        } catch (EvaluationException | GroupException ex) {
            Logger.getLogger(GroupFactory.class.getName()).log(Level.SEVERE, "could not create alternating group", ex);
            System.exit(-1);
        }
        
        return result;
    }

    public Group getDihedralGroup(int n){
        if(n < 2){
            String mess = "cannot not define dihedral group of order 2*" + Integer.toString(n);
            LOGGER.log(Level.SEVERE, mess);
            throw new IllegalArgumentException(mess);
        }
        
        Set<Element> generators = new HashSet<Element>();
        generators.add(new StringElement("a"));
        generators.add(new StringElement("b"));
        
        StringMultiplicator multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution(repeat("a",n), ""));
        multiplication.addSubstitution(new StringSubstitution("bb", ""));
         multiplication.addSubstitution(new StringSubstitution("ba", repeat("a", n-1) + "b"));      
        String name = "D" + Integer.toString(n);
        Group result = null;
        try {
            result =  new Group(name, generators, multiplication);
        } catch (GroupException ex) {
            Logger.getLogger(GroupFactory.class.getName()).log(Level.SEVERE, "could not create dihedral group", ex);
            System.exit(-1);
        } 
        return result;
    }
    
    public Group getGeneralizedDihedral(Group g){
        try {
            Group dih = (Group) g.getProperty(GroupProperty.DihedralGroup);
            return dih; 
            
        } catch (EvaluationException ex) {
            String mess = "could not create generalized dihedral group";
            Logger.getLogger(GroupFactory.class.getName()).log(Level.SEVERE, mess, ex);
        }
        
        System.exit(-1);
        return null;
    }

    private String repeat(String a, int n) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            sb.append(a);
        }
        return sb.toString();
    }

    public Group getDicyclicGroup(int n) {
        if(n < 1){
            String mess = "cannot not define dicyclic group of order 4*" + Integer.toString(n);
            LOGGER.log(Level.SEVERE, mess);
            throw new IllegalArgumentException(mess);
        }
        
        Set<Element> generators = new HashSet<Element>();
        generators.add(new StringElement("a"));
        generators.add(new StringElement("b"));
        
        StringMultiplicator multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution(repeat("a",2*n), ""));
        multiplication.addSubstitution(new StringSubstitution("bb", repeat("a",n)));
        multiplication.addSubstitution(new StringSubstitution("ba", repeat("a", 2*n-1) + "b"));
        
        String name = "Q" + Integer.toString(n);
        Group result = null;
        try {
            result =  new Group(name, generators, multiplication);
        } catch (GroupException ex) {
            Logger.getLogger(GroupFactory.class.getName()).log(Level.SEVERE, "could not create dicyclic group", ex);
            System.exit(-1);
        } 
        return result;
    }
    
    public Group getSemiDihedralGroup(int n){
        if(n < 2){
            String mess = "cannot not define SemiDihedral group: " + Integer.toString(n);
            LOGGER.log(Level.SEVERE, mess);
            throw new IllegalArgumentException(mess);
        }
        
        int expR = (1 << (n-1));   // 2** (n-1)
        int expComm = (1 << (n-2)) -1;
        
        Set<Element> generators = new HashSet<Element>();
        generators.add(new StringElement("r"));
        generators.add(new StringElement("s"));
        
        StringMultiplicator multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution(repeat("s",2), ""));
        multiplication.addSubstitution(new StringSubstitution(repeat("r", expR), ""));
        multiplication.addSubstitution(new StringSubstitution("sr", repeat("r", expComm) + "s"));
        
        String name = "SD" + Integer.toString(n);
        Group result = null;
        try {
            result =  new Group(name, generators, multiplication);
        } catch (GroupException ex) {
            Logger.getLogger(GroupFactory.class.getName()).log(Level.SEVERE, "could not create SemiDihedral group", ex);
            System.exit(-1);
        } 
        return result;
    }
    
    public Group getModular2Group(int n){
        if(n < 2){
            String mess = "cannot not define SemiDihedral group: " + Integer.toString(n);
            LOGGER.log(Level.SEVERE, mess);
            throw new IllegalArgumentException(mess);
        }
        
        int expR = (1 << (n-1));   // 2** (n-1)
        int expComm = (1 << (n-2)) + 1;
        
        Set<Element> generators = new HashSet<Element>();
        generators.add(new StringElement("r"));
        generators.add(new StringElement("s"));
        
        StringMultiplicator multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution(repeat("s",2), ""));
        multiplication.addSubstitution(new StringSubstitution(repeat("r", expR), ""));
        multiplication.addSubstitution(new StringSubstitution("sr", repeat("r", expComm) + "s"));
        
        String name = "M" + Integer.toString(n);
        Group result = null;
        try {
            result =  new Group(name, generators, multiplication);
        } catch (GroupException ex) {
            Logger.getLogger(GroupFactory.class.getName()).log(Level.SEVERE, "could not create Modular2Group", ex);
            System.exit(-1);
        } 
        return result;
    }
    
  /**
   * 
   * @param n
   * @param m
   * @param p
   * @return the group with relations
   *  a^n = b^m = 1 b.a = a^p * b
   * 
   * Note that p^m = 1 mod n has to be true for the group to be well defined
   */  
        public Group getMetacyclicGroup(int n, int m , int p) {
        if(n < 1){
            String mess = "cannot not define dicyclic group of order 4*" + Integer.toString(n);
            LOGGER.log(Level.SEVERE, mess);
            throw new IllegalArgumentException(mess);
        }
        
        int pow = IntNumber.power(p, m) - 1;
        if((pow % n) != 0){
            throw new IllegalArgumentException("condition for arguments of getMetaCyclicGroup not met");
        }
        
        Set<Element> generators = new HashSet<Element>();
        generators.add(new StringElement("a"));
        generators.add(new StringElement("b"));
        
        StringMultiplicator multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution(repeat("a",n), ""));
        multiplication.addSubstitution(new StringSubstitution(repeat("b",m), ""));

        multiplication.addSubstitution(new StringSubstitution("ba", repeat("a", p) + "b"));
        
        String name = "MC(" + Integer.toString(n) + "," + Integer.toString(m) + "," + Integer.toString(p) + ")";
        Group result = null;
        try {
            result =  new Group(name, generators, multiplication);
        } catch (GroupException ex) {
            Logger.getLogger(GroupFactory.class.getName()).log(Level.SEVERE, "could not create metacyclic group", ex);
            System.exit(-1);
        } 
        return result;
    }
        
    public Group getGroup16_1(){
        
        Set<Element> generators = new HashSet<Element>();
        generators.add(new StringElement("a"));
        generators.add(new StringElement("b"));
        generators.add(new StringElement("c"));        
        
        StringMultiplicator multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution(repeat("a",4), ""));
        multiplication.addSubstitution(new StringSubstitution(repeat("b",2), ""));
        multiplication.addSubstitution(new StringSubstitution(repeat("c",2), ""));  
        
        multiplication.addSubstitution(new StringSubstitution("ba", "ab"));
        multiplication.addSubstitution(new StringSubstitution("ca", "abc"));
        multiplication.addSubstitution(new StringSubstitution("cb", "bc"));
        
        
        String name = "G16_1";
        Group result = null;
        try {
            result =  new Group(name, generators, multiplication);
        } catch (GroupException ex) {
            Logger.getLogger(GroupFactory.class.getName()).log(Level.SEVERE, "could not create group", ex);
            System.exit(-1);
        } 
        return result;
    }
    
    public Group getGroup16_2(){
        
        Set<Element> generators = new HashSet<Element>();
        generators.add(new StringElement("a"));
        generators.add(new StringElement("b"));
        generators.add(new StringElement("c"));        
        
        StringMultiplicator multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution(repeat("a",4), ""));
        multiplication.addSubstitution(new StringSubstitution(repeat("b",2), ""));
        multiplication.addSubstitution(new StringSubstitution(repeat("c",2), "aa"));  
        
        multiplication.addSubstitution(new StringSubstitution("ba", "aaab"));
        multiplication.addSubstitution(new StringSubstitution("ca", "ac"));
        multiplication.addSubstitution(new StringSubstitution("cb", "bc"));
        
        String name = "G16_2";
        Group result = null;
        try {
            result =  new Group(name, generators, multiplication);
        } catch (GroupException ex) {
            Logger.getLogger(GroupFactory.class.getName()).log(Level.SEVERE, "could not create group", ex);
            System.exit(-1);
        } 
        return result;
    }    

    public Group getGroup24_1() {
        Set<Element> generators = new HashSet<Element>();
        generators.add(new StringElement("a"));
        generators.add(new StringElement("b"));
        generators.add(new StringElement("c"));        
        
        StringMultiplicator multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution(repeat("a",4), ""));
        multiplication.addSubstitution(new StringSubstitution(repeat("b",2), ""));
        multiplication.addSubstitution(new StringSubstitution(repeat("c",3), ""));  
        
        multiplication.addSubstitution(new StringSubstitution("ba", "aaab"));
        multiplication.addSubstitution(new StringSubstitution("ca", "acc"));
        multiplication.addSubstitution(new StringSubstitution("cb", "bc"));
        
        String name = "G24_1";
        Group result = null;
        try {
            result =  new Group(name, generators, multiplication);
        } catch (GroupException ex) {
            Logger.getLogger(GroupFactory.class.getName()).log(Level.SEVERE, "could not create group", ex);
            System.exit(-1);
        } 
        return result;    }

    /**
     * 
     * @param q the order of the field
     * @param n the dimension of the vector space on which the group acts
     * @return the General Linear group in n dimensions of the field F_q 
     */
    public Group getGL(int n, int q){
        // check the parameters for validity
        if(n < 1){
            throw new IllegalArgumentException("dimension of GL has to be positive");
        }
        
        if(!IntNumber.isPrimePower(q)){
            throw new IllegalArgumentException("GL uses invalid field");
        }
        
         // create the set to contain the elements of the group
        Set<Element> setElements = new HashSet<Element>();
        
        // create the field
        FieldFactory ff = new FieldFactory();
        Field Fq = ff.getField(q);
        
        // add a set of generators to the set
        try {
            
            setElements.add(singleUpperDiagonal(n, Fq));
            setElements.add(singleDiagonal(n, Fq));
            setElements.addAll(permutations(n, Fq));
            
        } catch (EvaluationException ex) {
            String mess = "could not find members of a field";
            Logger.getLogger(GroupFactory.class.getName()).log(Level.SEVERE, mess, ex);
            throw new IllegalStateException(mess);
        }

        //set the name
        String name = "GL(" + Integer.toString(n) + ",F_" + Integer.toString(q);
        
        // finally, set the multiplicator
        Multiplicator mult = new MatrixMultiplicator();
        
        try {
            return new Group(name, setElements, mult);
        } catch (GroupException ex) {
            String mess = "could not create group " + name;
            Logger.getLogger(GroupFactory.class.getName()).log(Level.SEVERE, mess, ex);
            throw new IllegalStateException(mess);
        }
    }
    
    
    /**
     * 
     * @param q the order of the field
     * @param n the dimension of the vector space on which the group acts
     * @return the Special Linear group in n dimensions of the field F_q 
     * 
     */
    public Group getSL(int n, int q) {
        // check the parameters for validity
        if(n < 1){
            throw new IllegalArgumentException("dimension of SL has to be positive");
        }
        
        if(!IntNumber.isPrimePower(q)){
            throw new IllegalArgumentException("SL uses invalid Field");
        }
        
        // find the single prime factor of q
        int p = IntNumber.factorize(q).keySet().iterator().next();
        
        try {
            
            Group gl = getGL(n,q);
            
            Set<MatrixElement> setGL = (Set<MatrixElement>) gl.getProperty(GroupProperty.Elements);
            MatrixElement unit = (MatrixElement) gl.getProperty(GroupProperty.UnitElement);
            Field Fq = unit.getField();
            FieldElement one = (FieldElement) Fq.getProperty(FieldProperty.ONE);
            
            Set<Element> setSL = new HashSet<Element>();
            
            for(MatrixElement g : setGL){
                if(g.det().equals(one)){
                    setSL.add(g);
                }
            }
            
            Multiplicator mult = (Multiplicator) gl.getProperty(GroupProperty.MultiplicationTable);
            String name = "SL(" + Integer.toString(n) + ",F_" + Integer.toString(q) + ")";
            
            return new Group(name, setSL, mult);
            
        } catch (EvaluationException | GroupException ex) {
            String mess = "cannot create Special Linear Group";
            Logger.getLogger(GroupFactory.class.getName()).log(Level.SEVERE, mess, ex);
            throw new IllegalStateException(mess);
        }
    }

    /**
     * 
     * @param n
     * @param unit
     * @return a matrix with ones on the diagonal and a single one off diagonal
     * 
     * The off diagonal is in the [0][1] position. The exceptional case is n==1,
     * then no off diagonal element is created. In this case, this method returns 
     * the 1x1 unit matrix. 
     */
    private MatrixElement singleUpperDiagonal(int n, Field Fq) throws EvaluationException {
        FieldElement unit = (FieldElement) Fq.getProperty(FieldProperty.ONE);
        FieldElement zero = (FieldElement) Fq.getProperty(FieldProperty.ZERO);
        FieldElement generator = (FieldElement) Fq.getProperty(FieldProperty.PRIMITIVE);        
        
        FieldElement[][] matrix = new FieldElement[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = zero;
            }
            matrix[i][i] = unit;
        }
        
        if(n>1){
            matrix[0][1] = unit;
        }
        
        return new MatrixElement(matrix);
    }

    /**
     * 
     * @param n
     * @param zero
     * @param unit
     * @param generator a multiplicative generator of the Field
     * @return a matrix that is diagonal( generator, unit, unit,...., unit) 
     */
    private MatrixElement singleDiagonal(int n, Field Fq) throws EvaluationException {
        FieldElement unit = (FieldElement) Fq.getProperty(FieldProperty.ONE);
        FieldElement zero = (FieldElement) Fq.getProperty(FieldProperty.ZERO);
        FieldElement generator = (FieldElement) Fq.getProperty(FieldProperty.PRIMITIVE);           
        
        FieldElement[][] matrix = new FieldElement[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = zero;
            }
            matrix[i][i] = unit;
        }
        
        matrix[0][0] = generator;
        
        return new MatrixElement(matrix);
    }
    
    /**
     * 
     * @param n
     * @param unit
     * @param zero 
     * @return all permutation matrices
     * 
     * For any sigma in S_n, mat_ij = 1 iff sigma: i-> j amd zero otherwise
     */
    private Set<MatrixElement> permutations(int n, Field Fq) throws EvaluationException {
        FieldElement unit = (FieldElement) Fq.getProperty(FieldProperty.ONE);
        FieldElement zero = (FieldElement) Fq.getProperty(FieldProperty.ZERO);
        FieldElement generator = (FieldElement) Fq.getProperty(FieldProperty.PRIMITIVE);           
        
        GroupFactory fac = new GroupFactory();
        Group sn = fac.getSymmetricGroup(n);
        Set<PermutationElement> set = (Set<PermutationElement>) sn.getProperty(GroupProperty.Elements);
        
        Set<MatrixElement> result = new HashSet<MatrixElement>();
        for(PermutationElement pe : set){
            int[] perm = pe.getPermutation();
            
            FieldElement[][] matrix = new FieldElement[n][n];
            
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    matrix[i][j] = zero;
                }
                matrix[i][perm[i]] = unit;
            }
            
            result.add(new MatrixElement(matrix));
        }
        
        return result;
        
    }  
}
