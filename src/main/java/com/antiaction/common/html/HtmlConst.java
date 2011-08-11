/*
 * Html tokens, used by tokenizer to report managed parts.
 * Copyright (C) 2005  Nicholas Clarke
 *
 */

/*
 * History:
 *
 * 12-Jan-2005 : First implementation.
 * 31-Jan-2005 : Tokens seem to have reached a stable state.
 *
 */

package com.antiaction.common.html;

/**
 * Html tokens, used by tokenizer to report managed parts.
 *
 * @version 1.00
 * @author Nicholas Clarke <mayhem[at]antiaction[dot]com>
 */
public class HtmlConst {

	public static final int T_ERROR = 0;
	public static final int T_EOS = 1;
	public static final int T_TEXT = 2;
	public static final int T_PROCESSING = 3;
	public static final int T_EXCLTYPE = 4;
	public static final int T_COMMENT = 5;
	public static final int T_ENDTAG = 6;
	public static final int T_TAG_START = 7;
	public static final int T_TAG_TEXT = 8;
	public static final int T_TAG_EQ = 9;
	public static final int T_TAG_TEXT_QUOTED = 10;
	public static final int T_TAG_CLOSED = 11;
	public static final int T_TAG_END = 12;
	public static final int T_DIRECTIVE_START = 13;

}
