/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author frank
 */
public class SubstitutionGroupFactory implements GroupFactory{

    private Set<String> gen = new HashSet<String>();
    private Map<String, String> sub = new HashMap<String, String>();
    private int Nmax = 100; 
    
    /**
     * 
     * @param a  a one character string that represents a generator
     */
    public void addGenerator(String a) {
        if(a.length() != 1){ throw new IllegalArgumentException();}
        gen.add(a);
    }

    /**
     * a substitution from -> to
     * @param from
     * @param to 
     */
    public void addSubstitution(String from, String to) {
        sub.put(from, to);
    }

    @Override
    public GroupTable createTable() {
        // set up the infra structure
        List<String> elements =  new ArrayList<String>();
        elements.add("");
        elements.addAll(gen);
        
        // iteratively add new elements, until nothing is added
        int oldNumber;
        int newNumber;
        do{
          oldNumber = elements.size();  
          for(int i = 0; i < oldNumber; i++){
              for(int j = 0; j < oldNumber; j++){
                  String s = multiply(elements.get(i), elements.get(j));
                  if(!elements.contains(s)){
                      elements.add(s);
                  }
              }
          }
          newNumber = elements.size();
        } while(oldNumber < newNumber);
        
        // fill the group table
        GroupTable tab = new GroupTable();
        tab.order = elements.size();
        tab.unit = 0;
        tab.table = new int[tab.order][];
        for(int i = 0; i < tab.order; i++){
            tab.table[i] = new int[tab.order];
            for(int j = 0; j < tab.order; j++){
                String si = elements.get(i);
                String sj = elements.get(j);
                String sij = multiply(si, sj);
                tab.table[i][j] = elements.indexOf(sij);
            }
        }
         return tab;
    }

    /**
     * 
     * @param s1
     * @param s2
     * @return the concatenated string, with the substitutions applied
     */
    private String multiply(String s1, String s2) {
        String pre = s1 + s2;
        String post = pre;
        int count = 0;
        
        do{
            pre = post;
            for(String key : sub.keySet()){
                post = post.replace(key, sub.get(key));
            }
            
          if(count++ >= Nmax){
              throw new RuntimeException("Number of iterations in multiply too large");
          }
        } while (!pre.equals(post));
        
        return post;
    }
    
}
