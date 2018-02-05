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

import com.dcomp.hamcrest.HmRsBodyBinary;
import com.google.common.base.Joiner;
import com.jcabi.http.request.JdkRequest;
import com.jcabi.http.response.RestResponse;
import java.net.HttpURLConnection;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.takes.Take;
import org.takes.http.FtRemote;
import org.takes.rq.RqFake;
import org.takes.rs.RsPrint;

/**
 * Test case for {@link TkApp}.
 *
 * @author Joao Figueiredo (joao.pedro.figueiredo@gmail.com)
 * @version $Id$
 * @since 1.0
 * @checkstyle ClassDataAbstractionCouplingCheck (500 lines)
 */
public final class TkAppTest {

    /**
     * App can render latex compile.
     *
     * @throws Exception If fails
     */
    @Test
    public void renderLatexCompile() throws Exception {
        final String body =
            Joiner.on("\r\n").join(
                "--AaB0zz",
                "Content-Disposition: form-data; name=\"doc\"",
                "",
                "\\documentclass{article}\\begin{document}test\\end{document}",
                "--AaB0zz--"
            );
        new FtRemote(new TkApp()).exec(
            // @checkstyle AnonInnerLengthCheck (50 lines)
            home -> new JdkRequest(String.format("%s/compile/latex", home))
                .method("POST")
                .header(
                    "Content-Type",
                    "multipart/form-data; boundary=AaB0zz"
                ).header(
                    "Content-Length",
                    String.valueOf(body.getBytes().length)
                )
                .body()
                .set(body)
                .back()
                .fetch()
                .as(RestResponse.class)
                .assertStatus(HttpURLConnection.HTTP_OK)
                .assertBinary(new HmRsBodyBinary.NotEmpty())
        );
    }

    /**
     * App can render not found.
     *
     * @throws Exception If some problem inside
     */
    @Test
    public void renderNotFoundPage() throws Exception {
        final Take take = new TkApp();
        MatcherAssert.assertThat(
            new RsPrint(
                take.act(new RqFake("HEAD", "/not-found"))
            ).printBody(),
            Matchers.equalTo("Page not found")
        );
    }
}
