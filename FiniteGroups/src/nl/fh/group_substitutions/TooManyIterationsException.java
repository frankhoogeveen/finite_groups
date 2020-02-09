/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group_substitutions;

import nl.fh.group.GroupInfoConstructionException;

/**
 *
 * @author frank
 */
class TooManyIterationsException extends GroupInfoConstructionException {

    public TooManyIterationsException(String message) {
        super(message);
    }
}
