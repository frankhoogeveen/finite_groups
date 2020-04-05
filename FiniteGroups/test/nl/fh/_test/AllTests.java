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

import nl.fh.classifier.ClassifierTest;
import nl.fh.group.GroupFactoryTest;
import nl.fh.group.GroupPermutationConstructorTest;
import nl.fh.group.SquareOfGroupTest;
import nl.fh.group.SubgroupTest;
import nl.fh.group_calculators.CenterCalculatorTest;
import nl.fh.group_calculators.ConjugationClassCalculatorTest;
import nl.fh.group_calculators.ElementOrderCalculatorTest;
import nl.fh.group_calculators.InverseCalculatorTest;
import nl.fh.group_calculators.PowerTableCalculatorTest;
import nl.fh.group_calculators.StronglyMinimalGeneratingSetsCalculatorTest;
import nl.fh.group_def_cyclic.GroupCyclicConstructorTest;
import nl.fh.group_def_product.GroupProductConstructorTest;
import nl.fh.group_formatter.FactorizationFormattingTest;
import nl.fh.lattice.LatticeTest;
import nl.fh.group_def_semidirect.*;
import nl.fh.group_def_substitutions.*;
import nl.fh.homomorphism.AutomorphismTest;
import nl.fh.homomorphism.EmbeddingTest;
import nl.fh.homomorphism.GroupHomomorphismTest;
import nl.fh.homomorphism.InnerAutomorphismTest;
import nl.fh.homomorphism.KernelTest;
import nl.fh.homomorphism.OuterAutomorphismTest;
import nl.fh.number.PrimeFactorsTest;
import nl.fh.number.PrimesTest;
import nl.fh.subgroups.*;
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
    SubgroupTest.class,
    PowerTableCalculatorTest.class,
    GroupHomomorphismTest.class,
    InnerAutomorphismTest.class,
    AutomorphismTest.class,
    EmbeddingTest.class,
    SquaresTest.class,
    SquareOfGroupTest.class,
    KernelTest.class,
    DerivedGroupTest.class,
    CenterTest.class,
    FactorGroupTest.class,
    OuterAutomorphismTest.class,
    AbeleanizationTest.class,
    ClassifierTest.class,
    PrimesTest.class,
    PrimeFactorsTest.class,
    AllSubgroupsTest.class,
    LatticeTest.class,
    SemiDirectProductTest.class,
    FactorizationFormattingTest.class
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
