/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group_def_substitutions;

/**
 *
 * @author frank
 */
public class StringSubstitution {

    private final String from;
    private final String to;

    /**
     * add a relation that, when applied, substitutes from -> to
     * @param from
     * @param to 
     */
    public StringSubstitution(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
