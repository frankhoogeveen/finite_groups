/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group_def_product;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fh.group.Element;
import nl.fh.group.GroupDefinition;
import nl.fh.group.Multiplicator;
import nl.fh.group.MultiplicatorException;

/**
 * This class defines a multiplication on the direct product of groups,
 * based on the multiplications in the factors of the product
 * 
 * 
 * @author frank
 */
class ProductMultiplicator implements Multiplicator<ProductElement> {

    private ProductElement unit;
    private Multiplicator[] multiplicators;

    public ProductMultiplicator() {
    }

    ProductMultiplicator(List<GroupDefinition> defs) {
        this.unit = new ProductElement(getUnitElementsOfFactors(defs));
        
        this.multiplicators = new Multiplicator[defs.size()];
        for(int i = 0; i < defs.size(); i++){
            this.multiplicators[i] = defs.get(i).getMultiplicator();
        }
    }

    private static Element[] getUnitElementsOfFactors(List<GroupDefinition> defs) {
        Element[] result = new Element[defs.size()];
        for(int i =0; i< defs.size(); i++){
            result[i] = defs.get(i).getMultiplicator().getUnit();
        }
        return result;
    }
    
    @Override
    public ProductElement getProduct(ProductElement factor1, ProductElement factor2) throws MultiplicatorException {
        if(factor1.getDimension() != this.multiplicators.length){
            throw new IllegalArgumentException(factor1.toString());
        }
        
        if(factor2.getDimension() != this.multiplicators.length){
            throw new IllegalArgumentException(factor2.toString());
        }
        
        Element[] result = new Element[this.multiplicators.length];
        for(int i =0 ; i < this.multiplicators.length; i++){
            try {
                result[i] = this.multiplicators[i].getProduct(factor1.get(i), factor2.get(i));
            } catch (MultiplicatorException ex) {
                String mess = "could not multiply " + factor1.toString() + " " + factor2.toString();
                Logger.getLogger(ProductMultiplicator.class.getName()).log(Level.SEVERE, mess, ex);
                throw new MultiplicatorException(mess);
            }
        }
        return new ProductElement(result);
    }

    @Override
    public Element getUnit() {
        return this.unit;
    }
    
}
