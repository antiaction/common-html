/*
 * Created on 15/04/2014
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package com.antiaction.common.html;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestHtmlConst extends TestCase {

	@Test
	public void testHtmlComment() {
		HtmlConst htmlConst = new HtmlConst();
		Assert.assertNotNull( htmlConst );
	}

}
