package com.langrsoft.bbtest;

import junit.framework.*;

public class AssertTest extends TestCase {
   public void testAssertTruePasses() {
      com.langrsoft.bbtest.Assert.assertTrue(true);
   }

   public void testAssertFalseFails() {
      try {
         com.langrsoft.bbtest.Assert.assertTrue(false);
         junitFail();
      } catch (com.langrsoft.bbtest.AssertionException expected) {
         junit.framework.Assert.assertEquals("", expected.getMessage());
      }
   }

   public void testAssertEqualsPassesWithTwoEqualValues() {
      com.langrsoft.bbtest.Assert.assertEquals(0, 0);
   }

   public void testAssertEqualsFailsWithTwoDifferentValues() {
      try {
         com.langrsoft.bbtest.Assert.assertEquals(0, 1);
         junitFail();
      } catch (com.langrsoft.bbtest.AssertionException expected) {
         junit.framework.Assert.assertEquals("expected <0> but was <1>", expected.getMessage());
      }
   }

   private void junitFail() {
      junit.framework.Assert.fail();
   }

   public void testAssertEqualsPassesWithTwoEqualReferences() {
      com.langrsoft.bbtest.Assert.assertEquals(new Integer(15), new Integer(15));
   }

   public void testAssertEqualsFailsWithTwoUnequalReferences() {
      try {
         com.langrsoft.bbtest.Assert.assertEquals(new Integer(15), new Integer(16));
         junitFail();
      } catch (com.langrsoft.bbtest.AssertionException expected) {
         junit.framework.Assert.assertEquals("expected <15> but was <16>", expected.getMessage());
      }
   }

   public void testAssertEqualsPassesWithTwoNullReferences() {
      com.langrsoft.bbtest.Assert.assertEquals(null, null);
   }

   public void testAssertEqualsFailsWithNullExpectedNonNullActual() {
      try {
         com.langrsoft.bbtest.Assert.assertEquals(null, new Integer(16));
         junitFail();
      } catch (com.langrsoft.bbtest.AssertionException expected) {
         junit.framework.Assert.assertEquals("expected <null> but was <16>", expected.getMessage());
      }
   }

   public void testFailFails() {
      try {
         com.langrsoft.bbtest.Assert.fail();
         junitFail();
      } catch (com.langrsoft.bbtest.AssertionException expected) {
         junit.framework.Assert.assertEquals("", expected.getMessage());
      }
   }
}
