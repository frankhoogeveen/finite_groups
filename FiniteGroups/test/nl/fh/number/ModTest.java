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
package nl.fh.number;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class ModTest {
    
    @Test
    public void ModInverseTest(){
       assertEquals(1, Mod.inverse(1, 3)); 
       assertEquals(2, Mod.inverse(2, 3)); 
       
       assertEquals(6, Mod.inverse(11, 13));
       
       assertEquals(1, Mod.inverse(1, 7)); 
       assertEquals(2, Mod.inverse(4, 7)); 
       assertEquals(3, Mod.inverse(5, 7)); 
       assertEquals(4, Mod.inverse(2, 7)); 
       assertEquals(5, Mod.inverse(3, 7)); 
       assertEquals(6, Mod.inverse(6, 7));    
    }
}
