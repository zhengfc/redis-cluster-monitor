package com.redis.cluster;

import com.redis.cluster.support.core.ClusterConnectionTest;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Unit test for App.
 */
public class AppTest{
	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		TestSuite suite= new TestSuite("TestSuite for app");
		suite.addTest(new JUnit4TestAdapter(ClusterConnectionTest.class));
		return suite;
	}

}
