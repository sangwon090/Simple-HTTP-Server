/*
 * MIT License
 *
 * Copyright (c) 2018 Sangwon Ryu
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.yottabyte090.httpserver.logger;

import com.yottabyte090.httpserver.Application;

import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Sangwon Ryu <yottabyte090 at naver.com>
 * @since 2018-07-27
 */

public class FileLogger extends LoggerBase {
    private FileWriter logWriter;
    private String newLine;

    public FileLogger(File logFile, String newLine){
        try {
            this.logWriter = new FileWriter(logFile);
            this.newLine = newLine;
        }catch(Exception e){
            Application.exception(e);
        }
    }

    public void finalize() {
        try {
            logWriter.close();
        }catch(Exception e){
            Application.exception(e);
        }
    }

    @Override
    public void log(String message){
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            logWriter.write(String.format("%s %s %s", dateFormat.format(new Date()), message, newLine));
            logWriter.flush();
        }catch(Exception e){
            Application.exception(e);
        }
    }
}
