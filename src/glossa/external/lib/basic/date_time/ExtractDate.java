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
package glossa.external.lib.basic.date_time;

import glossa.external.ExternalFunction;
import glossa.external.Parameter;
import glossa.types.Type;
import java.io.PrintStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Georgios Migdos <cyberpython@gmail.com>
 */
public class ExtractDate implements ExternalFunction{
    
    @Override
    public String getName() {
        return "Εξαγωγή_Ημερομηνίας_Ελλάδος";
    }

    @Override
    public String getPackageName() {
        return "Βασικές_Συναρτήσεις.Ώρα_Και_Ημερομηνία";
    }

    @Override
    public String getDescription() {
        return  "Επιστρέφει μια συμβολοσειρά που περιέχει την ημερομηνία που "
                + "αναπαριστάται από την παράμετρο `ΧΔ` ως χιλιοστά του "
                + "δευτερολέπτου (milliseconds) από την 01/01/197"
                + "0 [UTC](http://el.wikipedia.org/wiki/Συγχρονισμένος_Παγκόσμι"
                + "ος_Χρόνος).\n Η ημερομηνία είναι στη μορφή:\n\n *Ημέρα, ΗΗ/Μ"
                + "Μ/ΕΕΕΕ*\n\n"
                + "Μπορεί να χρησιμοποιηθεί σε συνδυασμό με τη συνάρτηση "
                + "`Τρέχων_Χρόνος` για την λήψη της τρέχουσας ημερομηνίας.";
    }

    @Override
    public List<Parameter> getParametersList() {
        List<Parameter> result = new ArrayList<Parameter>();
        result.add(new Parameter("ΧΔ", Type.INTEGER, false));
        return result;
    }

    @Override
    public Type getReturnType() {
        return Type.STRING;
    }

    @Override
    public Object execute(List<Object> parameters, PrintStream err) {
        return new SimpleDateFormat("EEEE, d/MM/yyyy", new Locale("el", "GR")).format(new Date((long)((BigInteger)parameters.get(0)).longValue()));
    }
}
