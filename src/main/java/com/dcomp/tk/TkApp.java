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

import org.takes.Take;
import org.takes.facets.flash.TkFlash;
import org.takes.facets.fork.FkMethods;
import org.takes.facets.fork.FkRegex;
import org.takes.facets.fork.TkFork;
import org.takes.facets.forward.TkForward;
import org.takes.tk.TkGzip;
import org.takes.tk.TkWrap;

/**
 * App.
 *
 * @author Joao Figueiredo (joao.pedro.figueiredo@gmail.com)
 * @version $Id$
 * @since 1.0
 * @checkstyle ClassDataAbstractionCouplingCheck (500 lines)
 */
public class TkApp extends TkWrap {

    /**
     * Ctor.
     */
    public TkApp() {
        super(TkApp.app());
    }

    /**
     * Build app take.
     *
     * @return Takes
     */
    private static Take app() {
        return new TkFlash(
            new TkAppFallback(
                new TkForward(
                    new TkGzip(
                        new TkFork(
                            new FkRegex("/robots.txt", ""),
                            new FkRegex(
                                "/compile/latex",
                                new TkFork(
                                    new FkMethods("POST", new TkLatexCompile())
                                )
                            )
                        )
                    )
                )
            )
        );
    }

}

