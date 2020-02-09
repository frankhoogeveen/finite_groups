/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author frank
 */
public class GroupDefinition {

    private final String name;
    private final Set<Element> generators;
    private final Multiplicator multiplication;
    private final int MAX_ITERATIONS = 10000;

    /**
     * abstractly defines a group
     * 
     * @param name
     * @param generators
     * @param multiplication 
     */
    public GroupDefinition(String name, Set<Element> generators, Multiplicator multiplication) {
        this.name = name;
        this.generators = generators;
        this.multiplication = multiplication;
    }

    public GroupInfoTable constructInfoTable() throws GroupInfoConstructionException{
        Set<Element> elements = findAllElements();
        return createTable(elements);
    }   

    private Set<Element> findAllElements() throws TooManyIterationsException {
        Set<Element> currentElements = new HashSet<Element>(generators);
        int startSize;
        int iterations = 0;
        
        do{
          if(iterations > MAX_ITERATIONS){
              StringBuilder sb = new StringBuilder();
              sb.append(this.getClass().getCanonicalName());
              sb.append(" hit ");
              sb.append(Integer.toString(MAX_ITERATIONS));
              sb.append(" iterations when looking for elements");
              
              throw new TooManyIterationsException(sb.toString());
          }
        
          startSize = currentElements.size();
          Set<Element> products = new HashSet<Element>();
          
          for(Element g1 : currentElements){
              for(Element g2 : currentElements){
                  products.add(this.multiplication.getProduct(g1, g2));
              }
          }
          
          currentElements.addAll(products);
        } while(currentElements.size() > startSize);
        
        return currentElements;
    }

    private GroupInfoTable createTable(Set<Element> elements) throws GroupInfoConstructionException{
        GroupInfoTable info = new GroupInfoTable();
        
        info.groupElements = new ArrayList<Element>(elements);
        info.order = info.groupElements.size();
            info.multiplicationTable = fillMultiplicationTable(elements);
            info.unit = findUnit(info);
        return info;
    }

    private int findUnit(GroupInfoTable info) throws UnitNotFoundException {
        for(int i = 0; i < info.order; i++){
            boolean isUnit = true;
            for(int j = 0; j < info.order; j++){
                isUnit = isUnit && (info.multiplicationTable[i][j] == j);
                isUnit = isUnit && (info.multiplicationTable[j][i] == j);
            }
            
            if(isUnit){
                return i;
            }
        }
        
        throw new UnitNotFoundException("Unit not found " + info.definition.name);
    }

    /**
     *
     * @param elements the set of all elements of the group
     */
    private int[][] fillMultiplicationTable(Set<Element> elements) throws TooManyIterationsException {
        List<Element> elementList = new ArrayList(elements);
        
        int[][] result = new int[elements.size()][];
        for(int i1 = 0; i1 < elements.size(); i1++){
            result[i1] = new int[elements.size()];
            for(int i2 = 0; i2 < elements.size(); i2++){
                Element g1 = elementList.get(i1);
                Element g2 = elementList.get(i2);
                Element g3 = this.multiplication.getProduct(g1,g2);
                result[i1][i2] = elementList.indexOf(g3);
            }
        }
        return result;
    }
}
