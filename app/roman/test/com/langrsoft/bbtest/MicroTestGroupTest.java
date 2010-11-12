package com.langrsoft.bbtest;

import com.langrsoft.bbtest.sampletests.*;
import junit.framework.*;

public class MicroTestGroupTest extends TestCase {
   private SimpleTestCollector collector;
   private MicroTestGroup group;

   protected void setUp() {
      collector = new SimpleTestCollector();
      group = new MicroTestGroup();
      group.setListener(collector);
   }

   public void testClassName() {
      MicroTestGroup test = new OnePass();
      assertEquals("OnePass", test.className());
   }

   public void testSetup() {
      MicroTestGroup group = new WithSetup();
      group.setListener(collector);
      group.execute();

      collector.assertPassFailCounts(2, 0);
   }

   public void testPassMany() {
      group.add(new OnePass());
      group.add(new TwoPass());

      group.execute();

      collector.assertPassFailCounts(3, 0);
   }

   public void testPassAndFailMany() {
      group.add(new TwoPass());
      group.add(new OneFail());
      group.add(new OnePass());

      group.execute();
      collector.assertPassFailCounts(3, 1);
   }
   
   public void testSample() {
      group.add(new SampleTest());
      group.execute();
      collector.assertPassFailCounts(2, 1);
   }

   public void testTeardown() {
      WithTeardown.wasReset = false;

      MicroTestGroup group = new WithTeardown();
      group.setListener(collector);

      group.execute();

      collector.assertPassFailCounts(1, 0);
      junit.framework.Assert.assertTrue(WithTeardown.wasReset);
   }
}
