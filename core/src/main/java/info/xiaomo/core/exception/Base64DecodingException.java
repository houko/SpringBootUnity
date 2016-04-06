package info.xiaomo.core.exception;

/*
 * Base64Util decoding exception.
 * Copyright (C) 2002 Stephen Ostermiller
 * http://ostermiller.org/contact.pl?regarding=Java+Utilities
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * See COPYING.TXT for details.
 */

import java.io.*;

/**
 * Exception that is thrown when an unexpected character is encountered
 * during Base64Util decoding.  One could catch this exception and use
 * the unexpected character for some other purpose such as including it
 * with data that comes at the end of a Base64Util encoded section of an email
 * message.
 *
 * @author Stephen Ostermiller http://ostermiller.org/contact.pl?regarding=Java+Utilities
 * @since ostermillerutils 1.00.00
 */
public class Base64DecodingException extends IOException {
	private char c;

	/**
	 * Construct an new exception.
	 *
	 * @param message message later to be returned by a getMessage() call.
	 * @param c character that caused this error.
	 *
	 * @since ostermillerutils 1.00.00
	 */
	public Base64DecodingException(String message, char c){
		super(message);
		this.c = c;
	}

	/**
	 * Get the character that caused this error.
	 *
	 * @return the character that caused this error.
	 *
	 * @since ostermillerutils 1.00.00
	 */
	public char getChar(){
		return c;
	}
}
