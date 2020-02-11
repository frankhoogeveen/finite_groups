/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group_info_table_checker;

import nl.fh.group_info.GroupInfoConstructionException;
import nl.fh.group_info.GroupInfoTable;

/**
 *
 * @author frank
 */
public class InfoTableChecker {
    
    public boolean isGroup(GroupInfoTable info) throws GroupInfoConstructionException{
        int order = info.getOrder();
        try{
            checkOrder(info);
            checkClosed(info);
            checkNeutralElement(info);
            checkInverses(info);
            checkAssociativity(info);
        } catch(InfoTableException e){
            return false;
        }
        return true;
    }

    private void checkOrder(GroupInfoTable info) throws InfoTableException, GroupInfoConstructionException {
        if(info.getOrder() < 1){
            throw new InfoTableException("Non positive order");
        }
    }

    private void checkClosed(GroupInfoTable info) throws InfoTableException, GroupInfoConstructionException {
        int n = info.getOrder();
        for(int i1 = 0; i1<n; i1++){
            for(int i2 = 0; i2<n; i2++){
                int i12 = info.multiply(i1, i2);
                if(i12 == GroupInfoTable.ERROR){
                    throw new InfoTableException("Table size");
                }
                if((i12<0) || (i12  >= n)){
                    throw new InfoTableException("Table not closed");
                }
            }
        }
    }

    private void checkNeutralElement(GroupInfoTable info) throws InfoTableException, GroupInfoConstructionException {
        int u = info.getUnit();
        for(int i = 0; i<info.getOrder(); i++){
            if((info.multiply(u, i) != i) || (info.multiply(i, u) != i)){
                throw new InfoTableException("Incorrect unit");
            }
        }
    }

    private void checkInverses(GroupInfoTable info) throws InfoTableException, GroupInfoConstructionException {
        for(int i = 0; i < info.getOrder(); i++){
            checkHasInverse(i, info);
        }
    }
    
    private void checkHasInverse(int i, GroupInfoTable info) throws InfoTableException, GroupInfoConstructionException {
        for(int j = 0; j < info.getOrder(); j++){
            if((info.multiply(i, j) == info.getUnit()) && (info.multiply(j, i) == info.getUnit())){
                return;
            }
        }
        throw new InfoTableException("Inverses do not exist for every element " + Integer.toString(i));
    }

    private void checkAssociativity(GroupInfoTable info) throws InfoTableException, GroupInfoConstructionException {
        for(int i1 = 0; i1 < info.getOrder(); i1++){
            for(int i2 = 0; i2 < info.getOrder(); i2++){
                for(int i3 = 0; i3 < info.getOrder(); i3++){
                    int n23 = info.multiply(i1, info.multiply(i2, i3));
                    int n12 = info.multiply(info.multiply(i1, i2), i3);
                    if(n12 != n23){
                        throw new InfoTableException("Not associative");
                    }
                } 
            }
        }
    }

}
