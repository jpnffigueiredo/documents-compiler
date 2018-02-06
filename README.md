
[![PDD status](http://www.0pdd.com/svg?name=jpnffigueiredo/documents-compiler)](http://www.0pdd.com/p?name=jpnffigueiredo/documents-compiler)

**ATTENTION**: This is an _experiment_ to create an application using 
[takes framework](https://github.com/yegor256/takes) and trying to apply OOP 
ideas from [Yegor Bugayenko](http://www.yegor256.com/).

**What does it do?**.

Expose a service used to compile uploaded LaTeX documents to pdf.

**How to use**.

* Do POST request to &lt;url&gt;/compile/latext 
* Use only one file at a time

Example:
```
curl -v -F 'doc=@file.tex' <url>/compile/latex > result.pdf
```

**License**

(The MIT License)

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

