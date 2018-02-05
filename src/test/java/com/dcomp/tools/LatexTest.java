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
package com.dcomp.tools;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.cactoos.Input;
import org.cactoos.io.LengthOf;
import org.cactoos.io.StickyInput;
import org.cactoos.io.TeeInput;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Test case for {@link Latex}.
 *
 * @author Joao Figueiredo (joao.pedro.figueiredo@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class LatexTest {

    /**
     * Latex can render pdf.
     *
     * @throws IOException If some problem inside
     */
    @Test
    public void renders() throws IOException {
        final Input document = new StickyInput(
            new Latex(
                "\\documentclass{article}\\begin{document}test\\end{document}"
            ).pdf()
        );
        final File file = Files.createTempFile("doc", ".pdf").toFile();
        file.deleteOnExit();
        new LengthOf(new TeeInput(document, file)).intValue();
        MatcherAssert.assertThat(
            new LengthOf(document).intValue(),
            Matchers.greaterThan(0)
        );
    }

}
