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
package com.dcomp.tk;

import com.dcomp.tools.Latex;
import java.io.IOException;
import java.util.logging.Level;
import org.cactoos.io.BytesOf;
import org.cactoos.io.InputOf;
import org.cactoos.iterator.LengthOf;
import org.takes.Request;
import org.takes.Response;
import org.takes.Take;
import org.takes.facets.flash.RsFlash;
import org.takes.facets.forward.RsForward;
import org.takes.rq.RqMultipart;
import org.takes.rq.multipart.RqMtBase;
import org.takes.rs.RsWithBody;
import org.takes.rs.RsWithHeaders;

/**
 * LaTeX compile.
 *
 * @author Joao Figueiredo (joao.pedro.figueiredo@gmail.com)
 * @version $Id$
 * @since 1.0
 * @checkstyle ClassDataAbstractionCouplingCheck (500 lines)
 */
public final class TkLatexCompile implements Take {

    @Override
    public Response act(final Request req) throws IOException {
        final RqMultipart base = new RqMtBase(req);
        final int total = new LengthOf(base.names().iterator()).intValue();
        if (total == 0) {
            throw new RsForward(
                new RsFlash("Must upload one document", Level.WARNING)
            );
        }
        if (total > 1) {
            throw new RsForward(
                new RsFlash("One document at a time", Level.WARNING)
            );
        }
        final String name = base.names().iterator().next();
        return new RsWithHeaders(
            new RsWithBody(
                new BytesOf(
                    new Latex(
                        new InputOf(base.part(name).iterator().next().body())
                    ).pdf()
                ).asBytes()
            ),
            "Content-Type: application/pdf",
            String.format(
                "Content-Disposition: attachment; filename=%s.pdf", name
            )
        );
    }

}
