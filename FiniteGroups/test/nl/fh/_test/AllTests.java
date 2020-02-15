package nl.fh._test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import nl.fh.calculators.test.ElementOrderCalculatorTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import nl.fh.group.test.*;

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
    ElementOrderCalculatorTest.class
}

)
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
