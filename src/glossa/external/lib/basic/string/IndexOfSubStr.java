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
package glossa.external.lib.basic.string;

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
public class IndexOfSubStr implements ExternalFunction{
    @Override
    public String getName() {
        return "Αναζήτηση_Συμβολοσειράς";
    }

    @Override
    public String getPackageName() {
        return "Βασικές_Συναρτήσεις.Συμβολοσειρές";
    }

    @Override
    public String getDescription() {
        return  "Επιστρέφει τη θέση της πρώτης εμφάνισης της τιμής της παραμέτρ"
                + "ου `Σ2` στην τιμή της παραμέτρου `Σ1`.\nΑν η τιμή της `Σ2` δ"
                + "εν περιέχεται στην τιμή της `Σ1`, τότε το αποτέλεσμα είναι "
                + "`0`.\n"
                + "Για παράδειγμα, η κλήση:\n\n"
                + "    Αναζήτηση_Συμβολοσειράς('Καλημέρα','λη')\n\n"
                + "θα επιστρέψει την τιμή `3`";
    }

    @Override
    public List<Parameter> getParametersList() {
        List<Parameter> result = new ArrayList<Parameter>();
        result.add(new Parameter("Σ1", Type.STRING, false));
        result.add(new Parameter("Σ2", Type.STRING, false));
        return result;
    }

    @Override
    public Type getReturnType() {
        return Type.INTEGER;
    }

    @Override
    public Object execute(List<Object> parameters, PrintStream err) {
        return ((String)parameters.get(0)).indexOf((String)parameters.get(1))+1;
    }
}
