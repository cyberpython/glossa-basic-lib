/*
 * The MIT License
 *
 * Copyright 2012 Georgios Migdos <cyberpython@gmail.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package glossa.external.lib.basic.files.text;

import glossa.external.ExternalFunction;
import glossa.external.Parameter;
import glossa.types.Type;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Georgios Migdos <cyberpython@gmail.com>
 */
public class WriteLine implements ExternalFunction{
    @Override
    public String getName() {
        return "Εγγραφή_Γραμμής_Σε_Αρχείο";
    }

    @Override
    public String getPackageName() {
        return "Βασικές_Συναρτήσεις.Αρχεία_Κειμένου";
    }

    @Override
    public String getDescription() {
        return  "Γράφει το περιεχόμενο της παραμέτρου `γραμμή` σε μία καινούργι"
                + "α γραμμή στο αρχείο που υποδεικνύεται από το περιεχόμ"
                + "ενο της παραμέτρου `αρχείο`.\nΑν συμβεί κάποιο σφάλμα κατά τ"
                + "ην εγγραφή επιστρέφει την τιμή `ΨΕΥΔΗΣ` αλλιώς επιστρέφει τη"
                + "ν τιμή `ΑΛΗΘΗΣ`.";
    }

    @Override
    public List<Parameter> getParametersList() {
        List<Parameter> result = new ArrayList<Parameter>();
        result.add(new Parameter("αρχείο", Type.STRING, false));
        result.add(new Parameter("γραμμή", Type.STRING, false));
        return result;
    }

    @Override
    public Type getReturnType() {
        return Type.BOOLEAN;
    }

    @Override
    public Object execute(List<Object> parameters, PrintStream err) {
        return Boolean.valueOf(TextFileManager.getInstance().writeLine((String)parameters.get(0), (String)parameters.get(1)));
    }
}
