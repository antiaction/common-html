/*
 * Created on 16/06/2013
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package com.antiaction.common.html;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TestHtmlDirective extends TestCase {

	public void testHtmlDirective() {
		HtmlDirective htmlItem;
		HtmlDirective htmlItem2;

		htmlItem = new HtmlDirective( null );
		Assert.assertNotNull( htmlItem );
		Assert.assertEquals( HtmlItem.T_DIRECTIVE, htmlItem.getType() );
		Assert.assertEquals( null, htmlItem.getText() );

		htmlItem2 = (HtmlDirective)htmlItem.clone();
		Assert.assertNotNull( htmlItem2 );
		Assert.assertEquals( HtmlItem.T_DIRECTIVE, htmlItem2.getType() );
		Assert.assertEquals( null, htmlItem2.getText() );
	}

}
