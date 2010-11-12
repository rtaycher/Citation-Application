package com.langrsoft.bbtest;

import java.util.*;

public class SimpleTestCollector implements ResultListener {
   Vector allTests = new Vector();
   Vector passingTests = new Vector();
   Vector failingTests = new Vector();

   public void ran(MicroTest test) {
      if (test.passed)
         passingTests.addElement(test);
      else
         failingTests.addElement(test);
      allTests.addElement(test);
   }

   public int passed() {
      return passingTests.size();
   }

   public int failed() {
      return failingTests.size();
   }

   public void assertPassFailCounts(int expectedPassed, int expectedFailed) {
      junit.framework.Assert.assertEquals(expectedPassed, passed());
      junit.framework.Assert.assertEquals(expectedFailed, failed());
   }
}
