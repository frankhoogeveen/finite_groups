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

package nl.fh._test;


import nl.fh.calculators_test.CenterCalculatorTest;
import nl.fh.calculators_test.ConjugationClassCalculatorTest;
import nl.fh.calculators_test.ElementOrderCalculatorTest;
import nl.fh.calculators_test.InverseCalculatorTest;
import nl.fh.calculators_test.StronglyMinimalGeneratingSetsCalculatorTest;
import nl.fh.group_test.GroupCyclicConstructorTest;
import nl.fh.group_test.GroupFactoryTest;
import nl.fh.group_test.GroupPermutationConstructorTest;
import nl.fh.group_test.GroupProductConstructorTest;
import nl.fh.group_test.GroupSubstitutionConstructorTest;
import nl.fh.group_test.GroupSubstitutionInconsistentTest;
import nl.fh.value_test.SubsetValueTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author frank
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    TestTheTest.class,
    GroupSubstitutionConstructorTest.class,
    GroupCyclicConstructorTest.class,
    GroupProductConstructorTest.class,
    GroupPermutationConstructorTest.class,
    ElementOrderCalculatorTest.class,
    InverseCalculatorTest.class,
    GroupSubstitutionInconsistentTest.class,
    CenterCalculatorTest.class,
    ConjugationClassCalculatorTest.class,
    GroupFactoryTest.class,
    StronglyMinimalGeneratingSetsCalculatorTest.class,
    SubsetValueTest.class
})


public class AllTests {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
