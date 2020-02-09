/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.free_group;

import java.util.Objects;
import nl.fh.group.Element;


/**
 * a group element that is represented by a string of characters
 * 
 * @author frank
 */
public class StringElement implements Element {

    String characters;
    
    public StringElement(String s) {
        this.characters = s;
    }
    
    public String toString(){
        if(this.characters.equals("")){
            return "1";
        } else {
            return characters;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.characters);
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
        final StringElement other = (StringElement) obj;
        if (!Objects.equals(this.characters, other.characters)) {
            return false;
        }
        return true;
    }
}
