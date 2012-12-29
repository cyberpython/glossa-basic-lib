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

import java.io.*;
import java.util.HashMap;

/**
 *
 * @author Georgios Migdos <cyberpython@gmail.com>
 */
public class TextFileManager {
    
    private static final int INVALIDATE_MARK_AFTER = 2048;
    
    private HashMap<String, BufferedWriter> outputFiles;
    private HashMap<String, BufferedReader> inputFiles;
    
    private TextFileManager() {
        outputFiles = new HashMap<String, BufferedWriter>();
        inputFiles = new HashMap<String, BufferedReader>();
    }
    
    public static TextFileManager getInstance() {
        return TextFileManagerHolder.INSTANCE;
    }
    
    private static class TextFileManagerHolder {

        private static final TextFileManager INSTANCE = new TextFileManager();
    }
    
    public boolean openFileForReading(String path){
        if(inputFiles.get(path)!=null){ // The file is already open for reading
            return true;
        }
        if(outputFiles.get(path)!=null){ // The file is already open for writing
            return false;
        }
        File f = new File(path);
        if(f.isFile()){
            try{
                BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));
                inputFiles.put(path, r);
                return true;
            }catch(Exception e){
            }
        }
        return false;
    }
    
    public boolean openFileForWriting(String path, boolean append){
        if(outputFiles.get(path)!=null){ // The file is already open for writing
            return true;
        }
        if(inputFiles.get(path)!=null){ // The file is already open for reading
            return false;
        }
        File f = new File(path);
        try{
            BufferedWriter w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, append), "UTF-8"));
            outputFiles.put(path, w);
            return true;
        }catch(Exception e){
        }
        return false;
    }
    
    public boolean closeFile(String path){
        Reader r = inputFiles.get(path);
        if(r!=null){
            try{
                r.close();
                inputFiles.remove(path);
            }catch(IOException ioe){
                return false;
            }
        }
        Writer w = outputFiles.get(path);
        if(w!=null){
            try{
                w.flush();
                w.close();
                outputFiles.remove(path);
            }catch(IOException ioe){
                return false;
            }
        }
        return true;
    }
    
    public String readLine(String path){
        BufferedReader r = inputFiles.get(path);
        if(r==null){
            throw new RuntimeException("Το αρχείο "+path+" δεν είναι ανοιχτό για ανάγνωση!");
        }else{
            try{
                String line = r.readLine();
                if(line == null){
                    return "";
                }else{
                    return line;
                }
            }catch(IOException ioe){
                return "";
            }
        }
    }
    
    public boolean eof(String path){
        BufferedReader r = inputFiles.get(path);
        if(r==null){
            throw new RuntimeException("Το αρχείο "+path+" δεν είναι ανοιχτό για ανάγνωση!");
        }else{
            try{
                r.mark(INVALIDATE_MARK_AFTER);
                String line = r.readLine();
                r.reset();
                if(line == null){
                    return true;
                }else{
                    return false;
                }
            }catch(IOException ioe){
                return false;
            }
        }
    }
    
    public boolean writeLine(String path, String line){
        BufferedWriter w = outputFiles.get(path);
        if(w==null){
            throw new RuntimeException("Το αρχείο "+path+" δεν είναι ανοιχτό για εγγραφή!");
        }else{
            try{
                w.write(line+"\n");
                return true;
            }catch(IOException ioe){
                return false;
            }
        }
    }
}
