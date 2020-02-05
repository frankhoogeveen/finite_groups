/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.aaa;

import nl.fh.format.Formatter;
import nl.fh.group.SubstitutionGroupFactory;

/**
 *
 * @author frank
 */
public class Demo1 {
 
    /*
     * print D4 
     */
    
    public static void main(String[] args){
        SubstitutionGroupFactory fac = new SubstitutionGroupFactory();
        fac.addGenerator("a");
        fac.addGenerator("b");
        fac.addSubstitution("aa", "");
        fac.addSubstitution("bbbb", "");
        fac.addSubstitution("ba", "abbb");
        
        Formatter f = new Formatter(fac.createTable());
        System.out.println(f.listMultiplications());
    }
}
