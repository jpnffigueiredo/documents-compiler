/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Joao Figueiredo
 *
 * Permission is hereby granted, free of charge,  to any person obtaining
 * a copy  of  this  software  and  associated  documentation files  (the
 * "Software"),  to deal in the Software  without restriction,  including
 * without limitation the rights to use,  copy,  modify,  merge, publish,
 * distribute,  sublicense,  and/or sell  copies of the Software,  and to
 * permit persons to whom the Software is furnished to do so,  subject to
 * the  following  conditions:   the  above  copyright  notice  and  this
 * permission notice  shall  be  included  in  all copies or  substantial
 * portions of the Software.  The software is provided  "as is",  without
 * warranty of any kind, express or implied, including but not limited to
 * the warranties  of merchantability,  fitness for  a particular purpose
 * and non-infringement.  In  no  event shall  the  authors  or copyright
 * holders be liable for any claim,  damages or other liability,  whether
 * in an action of contract,  tort or otherwise,  arising from, out of or
 * in connection with the software or  the  use  or other dealings in the
 * software.
 */
package com.dcomp.hamcrest;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

/**
 * Binary response body Matcher.
 *
 * @author Joao Figueiredo (joao.pedro.figueiredo@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public interface HmRsBodyBinary extends Matcher<byte[]> {

    /**
     * Match empty body.
     */
    final class Empty extends BaseMatcher<byte[]> implements HmRsBodyBinary {

        @Override
        public boolean matches(final Object item) {
            return item instanceof byte[] && ((byte[]) item).length == 0;
        }

        @Override
        public void describeTo(final Description description) {
            description.appendText("an empty body");
        }

    }

    /**
     * Match not empty body.
     */
    final class NotEmpty extends BaseMatcher<byte[]> implements HmRsBodyBinary {

        /**
         * Matcher.
         */
        private final HmRsBodyBinary matcher;

        /**
         * Ctor.
         */
        public NotEmpty() {
            super();
            this.matcher = new HmRsBodyBinary.Empty();
        }

        @Override
        public boolean matches(final Object item) {
            return !this.matcher.matches(item);
        }

        @Override
        public void describeTo(final Description description) {
            description.appendText("a not empty body");
        }
    }

}
