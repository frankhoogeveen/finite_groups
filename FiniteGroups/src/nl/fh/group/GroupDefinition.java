/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group;

import java.util.Collection;

/**
 * Defines a group as something with a name, a set of generators and a 
 * way to multiply them
 * 
 * 
 * @author frank
 */
public class GroupDefinition {

    private final String name;
    private final Collection<Element> generators;
    private final Multiplicator multiplicator;

    /**
     * abstractly defines a group
     * 
     * @param name
     * @param generators
     * @param multiplication 
     */
    public GroupDefinition(String name, Collection<Element> generators, Multiplicator multiplication) {
        this.name = name;
        this.generators = generators;
        this.multiplicator = multiplication;
    }

    public String getName() {
        return this.name;
    }

    public Collection<Element> getGenerators() {
        return generators;
    }

    public Multiplicator getMultiplicator() {
        return multiplicator;
    }
}
