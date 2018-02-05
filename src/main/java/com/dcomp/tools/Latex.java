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

import com.google.common.io.Files;
import com.jcabi.log.VerboseProcess;
import java.io.File;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import org.cactoos.Input;
import org.cactoos.io.InputOf;
import org.cactoos.io.LengthOf;
import org.cactoos.io.TeeInput;

/**
 * Latex document.
 *
 * @author Joao Figueiredo (joao.pedro.figueiredo@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class Latex {

    /**
     * Document source.
     */
    private final InputOf source;

    /**
     * Ctor.
     *
     * @param src Document source
     */
    public Latex(final String src) {
        this(new InputOf(src));
    }

    /**
     * Ctor.
     * @param src Document source
     */
    public Latex(final InputOf src) {
        this.source = src;
    }

    /**
     * Create pdf file.
     * @return Pdf content
     * @throws IOException If fails
     */
    public Input pdf() throws IOException {
        final File dir = Files.createTempDir();
        final File doc = new File(dir, "document.tex");
        try {
            new LengthOf(new TeeInput(this.source, doc)).intValue();
            Latex.compile(dir, doc.getName());
            return new InputOf(
                java.nio.file.Files.newInputStream(
                    new File(dir, "document.pdf").toPath(),
                    StandardOpenOption.DELETE_ON_CLOSE
                )
            );
        } finally {
            if (!doc.delete()) {
                doc.deleteOnExit();
            }
        }
    }

    /**
     * Compile file to directory.
     * @param dir Directory
     * @param file File name
     * @throws IOException If fails
     */
    private static void compile(final File dir, final String file)
        throws IOException {
        try (final VerboseProcess process = new VerboseProcess(
            new ProcessBuilder().command(
                "pdflatex",
                "-interaction=errorstopmode",
                "-halt-on-error",
                file
            ).directory(dir).start(),
            Level.INFO,
            Level.WARNING
        )) {
            final String out = process.stdout();
            if (out.contains("Error")) {
                throw new IllegalArgumentException(
                    "Failed to compile latex document"
                );
            }
        }
    }
}
