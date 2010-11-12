package com.langrsoft.bbtest;

import junit.framework.*;

public class MicroTestTest extends TestCase {
   static final MicroTest PASSING_TEST = new MicroTest() {
      public void run() {
      }
   };

   static final MicroTest FAILING_TEST = new MicroTest() {
      public void run() {
         com.langrsoft.bbtest.Assert.fail();
      }
   };

   public void testFail() {
      FAILING_TEST.execute();
      junit.framework.Assert.assertFalse(FAILING_TEST.passed());
   }

   public void testExecutePass() {
      PASSING_TEST.execute();
      junit.framework.Assert.assertTrue(PASSING_TEST.passed());
   }

   public void testExecuteWithContextPasses() {
      RecordingTestContext context = new RecordingTestContext(PASSING_TEST);
      PASSING_TEST.execute(context);
      context.assertCompleteSequence();
      junit.framework.Assert.assertTrue(PASSING_TEST.passed());
   }

   public void testExecuteWithContextFailsWhenRunFails() {
      RecordingTestContext context = new RecordingTestContext(FAILING_TEST);
      FAILING_TEST.execute(context);
      context.assertCompleteSequence();
      junit.framework.Assert.assertFalse(FAILING_TEST.passed());
   }

   public void testExecuteWithContextFailsWhenTeardownThrows() {
      RecordingTestContext context = new RecordingTestContext(PASSING_TEST) {
         public void tearDown() {
            super.tearDown();
            throw new RuntimeException();
         }
      };
      PASSING_TEST.execute(context);
      context.assertCompleteSequence();
      junit.framework.Assert.assertFalse(PASSING_TEST.passed());
   }

   public void testExecuteWithContextFailsWhenSetupThrows() {
      RecordingTestContext context = new RecordingTestContext(PASSING_TEST) {
         public void setUp() {
            super.setUp();
            throw new RuntimeException();
         }
      };
      PASSING_TEST.execute(context);
      context.assertSequence(RecordingTestContext.SETUP + RecordingTestContext.COMPLETED);
      junit.framework.Assert.assertFalse(PASSING_TEST.passed());
   }
}
