package com.langrsoft.bbtest;

public class RecordingTestContext implements TestContext {
   private StringBuffer buffer = new StringBuffer();
   private final MicroTest test;
   static final String SETUP = "SetUp";
   static final String TEARDOWN = "Teardown";
   static final String COMPLETED = "Completed";

   public RecordingTestContext(MicroTest test) {
      this.test = test;
   }

   public void completed(MicroTest completed) {
      junit.framework.Assert.assertSame(test, completed);
      buffer.append("Completed");
   }

   public void setUp() {
      buffer.append("SetUp");
   }

   public void tearDown() {
      buffer.append("Teardown");
   }

   public void assertSequence(String expected) {
      junit.framework.Assert.assertEquals(expected, buffer.toString());
   }

   public void assertCompleteSequence() {
      assertSequence(SETUP + TEARDOWN + COMPLETED);
   }
}
