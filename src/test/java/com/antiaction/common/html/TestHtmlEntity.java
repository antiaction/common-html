/*
 * Created on 16/06/2013
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
public class TestHtmlEntity extends TestCase {

	@Test
	public void testHtmlEntity() {
		StringBuffer sb;
		StringBuffer sb2;

		HtmlEntity htmlEntity = new HtmlEntity();
		Assert.assertNotNull( htmlEntity );

		sb = HtmlEntity.encodeHtmlEntities( "" );
		Assert.assertNotNull( sb );
		Assert.assertEquals( "", sb.toString() );

		sb2 = HtmlEntity.encodeHtmlEntities( sb );
		Assert.assertNotNull( sb2 );
		Assert.assertEquals( "", sb2.toString() );
		Assert.assertTrue( sb == sb2 );

		sb = HtmlEntity.encodeHtmlEntities( "text" );
		Assert.assertNotNull( sb );
		Assert.assertEquals( "text", sb.toString() );

		sb2 = HtmlEntity.encodeHtmlEntities( sb );
		Assert.assertNotNull( sb2 );
		Assert.assertEquals( "text", sb2.toString() );
		Assert.assertTrue( sb == sb2 );

		sb = HtmlEntity.encodeHtmlEntities( "\'\"&<>" );
		Assert.assertNotNull( sb );
		Assert.assertEquals( "&#39;&quot;&amp;&lt;&gt;", sb.toString() );

		sb = new StringBuffer( "\'\"&<>" );
		sb2 = HtmlEntity.encodeHtmlEntities( sb );
		Assert.assertTrue( sb == sb2 );
		Assert.assertEquals( "&#39;&quot;&amp;&lt;&gt;", sb.toString() );
		Assert.assertEquals( "&#39;&quot;&amp;&lt;&gt;", sb2.toString() );
	}

}
