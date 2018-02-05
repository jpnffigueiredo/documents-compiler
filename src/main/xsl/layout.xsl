<?xml version="1.0"?>
<!--
The MIT License (MIT)

Copyright (c) 2018 Joao Figueiredo

Permission is hereby granted, free of charge,  to any person obtaining
a copy  of  this  software  and  associated  documentation files  (the
"Software"),  to deal in the Software  without restriction,  including
without limitation the rights to use,  copy,  modify,  merge, publish,
distribute,  sublicense,  and/or sell  copies of the Software,  and to
permit persons to whom the Software is furnished to do so,  subject to
the  following  conditions:   the  above  copyright  notice  and  this
permission notice  shall  be  included  in  all copies or  substantial
portions of the Software.  The software is provided  "as is",  without
warranty of any kind, express or implied, including but not limited to
the warranties  of merchantability,  fitness for  a particular purpose
and non-infringement.  In  no  event shall  the  authors  or copyright
holders be liable for any claim,  damages or other liability,  whether
in an action of contract,  tort or otherwise,  arising from, out of or
in connection with the software or  the  use  or other dealings in the
software.
-->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" version="1.0">
    <xsl:include href="/org/takes/rs/xe/sla.xsl"/>
    <xsl:include href="/org/takes/rs/xe/memory.xsl"/>
    <xsl:include href="/org/takes/rs/xe/millis.xsl"/>
    <xsl:include href="/org/takes/facets/flash/flash.xsl"/>
    <xsl:template match="/page">
        <html lang="en">
            <head>
                <meta charset="UTF-8"/>
                <meta name="viewport" content="width=device-width,minimum-scale=1,initial-scale=1"/>
                <link rel="stylesheet" href="/css/main.css"/>
                <link rel="stylesheet" href="http://yegor256.github.io/tacit/tacit.min.css"/>
                <xsl:apply-templates select="." mode="head"/>
            </head>
            <body>
                <section>
                    <header>
                        <nav>
                            <ul>
                                <li>
                                    <a href="{links/link[@rel='home']/@href}">
                                        <img src="/images/logo.svg" class="logo"/>
                                    </a>
                                </li>
                            </ul>
                        </nav>
                        <nav>
                            <ul class="menu">
                                <li>
                                    <xsl:if test="identity">
                                        <xsl:text>@</xsl:text>
                                        <xsl:value-of select="identity/login"/>
                                    </xsl:if>
                                    <xsl:if test="not(identity)">
                                        <a href="{links/link[@rel='takes:github']/@href}">
                                            <xsl:text>Login</xsl:text>
                                        </a>
                                    </xsl:if>
                                </li>
                                <xsl:if test="identity">
                                    <!-- add options here -->
                                </xsl:if>
                                <xsl:if test="identity">
                                    <li>
                                        <a href="{links/link[@rel='takes:logout']/@href}">
                                            <xsl:text>Exit</xsl:text>
                                        </a>
                                    </li>
                                </xsl:if>
                            </ul>
                        </nav>
                        <xsl:call-template name="takes_flash">
                            <xsl:with-param name="flash" select="flash"/>
                        </xsl:call-template>
                    </header>
                    <article>
                        <xsl:apply-templates select="." mode="body"/>
                    </article>
                    <footer>
                        <!-- add footer details here -->
                    </footer>
                </section>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
