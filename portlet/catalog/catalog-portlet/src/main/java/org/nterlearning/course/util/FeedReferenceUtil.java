/*
 * National Training and Education Resource (NTER)
 * Copyright (C) 2012 SRI International
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 */

package org.nterlearning.course.util;

import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FeedReferenceUtil {

    /**
     * Creates a SHA-1 hash value of the href and converts it to a String
     * value to be stored in the database.
     *
     * @param href Original HREF to create a hash value for
     *
     * @return String value of the href, or null if an error occurred.
     */
    public static String generateHash(String href) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(href.getBytes("UTF-8"));
            byte output[] = md.digest();

            return Hex.encodeHexString(output);
        }
        catch (NoSuchAlgorithmException nsae) {
            return null;
        }
        catch (UnsupportedEncodingException e) {
            return null;
        }
    }

}
