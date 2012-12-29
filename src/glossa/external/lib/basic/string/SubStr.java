/*
 * The MIT License
 *
 * Copyright 2012  Georgios Migdos <cyberpython@gmail.com>.
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
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Georgios Migdos <cyberpython@gmail.com>
 */
public class SubStr implements ExternalFunction{

    @Override
    public String getName() {
        return "Τμήμα_Συμβολοσειράς";
    }

    @Override
    public String getPackageName() {
        return "Βασικές_Συναρτήσεις.Συμβολοσειρές";
    }

    @Override
    public String getDescription() {
        return  "Εξάγει το τμήμα της συμβολοσειράς `Σ` που προσδιορίζεται από τις παραμέτρους `αρχή` και `τέλος`.\n"
              + "Για παράδειγμα, η κλήση:\n"
              + "    Τμήμα_Συμβολοσειράς('Καλημέρα',2,5)\n"
              + "θα επιστρέψει την τιμή `αλημ`";
    }

    @Override
    public List<Parameter> getParametersList() {
        List<Parameter> result = new ArrayList<Parameter>();
        result.add(new Parameter("Σ", Type.STRING, false));
        result.add(new Parameter("αρχή", Type.INTEGER, false));
        result.add(new Parameter("τέλος", Type.INTEGER, false));
        return result;
    }

    @Override
    public Type getReturnType() {
        return Type.STRING;
    }

    @Override
    public Object execute(List<Object> parameters, PrintStream err) {
        String srcStr = ((String) parameters.get(0));
        int startIndex = ((BigInteger) parameters.get(1)).intValue()-1;
        int endIndex = ((BigInteger) parameters.get(2)).intValue();
        try{
            if(startIndex==endIndex){throw new IndexOutOfBoundsException();}
            String result = srcStr.substring(startIndex, endIndex);
            return result;
        }catch(IndexOutOfBoundsException iobe){
            throw new RuntimeException(String.format("Μη έγκυρα όρια για τη συνάρτηση Τμήμα_Συμβολοσειράς. Συμβολοσειρά='%1$s' αρχή=%2$d, τέλος=%3$d.", srcStr, startIndex+1, endIndex));
        }
        
    }
    
    
    
}
