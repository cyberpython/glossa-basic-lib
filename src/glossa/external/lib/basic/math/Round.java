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
package glossa.external.lib.basic.math;

import glossa.external.ExternalFunction;
import glossa.external.Parameter;
import glossa.types.Type;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Georgios Migdos <cyberpython@gmail.com>
 */
public class Round implements ExternalFunction {

    @Override
    public String getName() {
        return "Στρογγυλοποίηση";
    }

    @Override
    public String getPackageName() {
        return "Βασικές_Συναρτήσεις.Μαθηματικές_Συναρτήσεις";
    }

    @Override
    public String getDescription() {
        return "Επιστρέφει την τιμή του Χ στρογγυλοποιημένη σε Ν δεκαδικά ψηφί"
                + "α σύμφωνα με τους κανόνες της αριθμητικής που διδάσκονται στ"
                + "ο δημοτικό σχολείο. Για παράδειγμα, η κλήση:\n\n"
                + "    Στρογγυλοποίηση(12.3246, 3)\n\n"
                + "θα δώσει το απότελεσμα `12.325` .";
    }

    @Override
    public List<Parameter> getParametersList() {
        List<Parameter> result = new ArrayList<Parameter>();
        result.add(new Parameter("Χ", Type.REAL, false));
        result.add(new Parameter("Ν", Type.INTEGER, false));
        return result;
    }

    @Override
    public Type getReturnType() {
        return Type.REAL;
    }

    @Override
    public Object execute(List<Object> parameters, PrintStream err) {
        BigDecimal val;
        Object param1 = parameters.get(0);
        int numOfDecimals = ((BigInteger) parameters.get(1)).intValue();
        if(numOfDecimals < 0){
            throw new RuntimeException(String.format("Συνάρτηση Στρογγυλοποίηση: Η τιμή της παραμέτρου Ν (%d) δε μπορεί να είναι αρνητική.", numOfDecimals));
        }
        if (param1 instanceof BigInteger) {
            return new BigDecimal((BigInteger) param1);
        } else {
            val = (BigDecimal) param1;
        }
        String strVal = val.toPlainString();
        int precision = strVal.indexOf(".");
        if(precision>=0){
            if( new BigInteger(strVal.substring(0, precision)).equals(BigInteger.ZERO)){
                precision = 0;
            }
            precision = precision + numOfDecimals;
            MathContext mc = new MathContext(precision, RoundingMode.HALF_UP);
            return val.round(mc);
        }
        return val;
    }
}
