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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Georgios Migdos <cyberpython@gmail.com>
 */
public class Random implements ExternalFunction{
    @Override
    public String getName() {
        return "Τυχαία_Τιμή";
    }

    @Override
    public String getPackageName() {
        return "Βασικές_Συναρτήσεις.Μαθηματικές_Συναρτήσεις";
    }

    @Override
    public String getDescription() {
        return "Επιστρέφει μία ψευδο-τυχαία τιμή στο πεδίο [ελάχιστο, μέγιστο)";
    }

    @Override
    public List<Parameter> getParametersList() {
        List<Parameter> result = new ArrayList<Parameter>();
        result.add(new Parameter("ελάχιστο", Type.REAL, false));
        result.add(new Parameter("μέγιστο", Type.REAL, false));
        return result;
    }

    @Override
    public Type getReturnType() {
        return Type.REAL;
    }

    @Override
    public Object execute(List<Object> parameters, PrintStream err) {
        BigDecimal min;
        BigDecimal max;
        Object param1 = parameters.get(0);
        Object param2 = parameters.get(1);
        if(param1 instanceof BigInteger){
            min = new BigDecimal((BigInteger)param1);
        }else{
            min = (BigDecimal) param1;
        }
        if(param2 instanceof BigInteger){
            max = new BigDecimal((BigInteger)param2);
        }else{
            max = (BigDecimal) param2;
        }
        if(min.compareTo(max)<0){
            return min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
        }else{
            throw new RuntimeException(String.format("Σφάλμα: Η ελάχιστη τιμή (%s) δε μπορεί να είναι μεγαλύτερη ή ίση με τη μέγιστη (%s) για κλήσεις στη συνάρτηση Τυχαία_Τιμή.", min.toPlainString(), max.toPlainString()));
        }
    }
}
