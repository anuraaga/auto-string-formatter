/*
 * Copyright (C) 2016 Masahiro Ide
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.imasahiro.stringformatter.processor.benchmark;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

public class BooleanStringifyBench {
    private static final BooleanStringifyBenchFormatter.Formatter formatter =
            new BooleanStringifyBenchFormatter_Formatter();

    private static String javaStringFormat(String formatString, boolean a, boolean b, boolean c, boolean d) {
        return String.format(formatString, a, b, c, d);
    }

    @Benchmark
    public void javaStringFormat(Blackhole blackhole) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        blackhole.consume(javaStringFormat(BooleanStringifyBenchFormatter.FORMAT,
                                           random.nextBoolean(),
                                           random.nextBoolean(),
                                           random.nextBoolean(),
                                           random.nextBoolean()));
    }

    private static String javaStringConcat(boolean a, boolean b, boolean c, boolean d) {
        String s = "";
        s += a;
        s += " + ";
        s += b;
        s += " * ";
        s += c;
        s += " = ";
        s += d;
        return s;
    }

    @Benchmark
    public void javaStringConcat(Blackhole blackhole) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        blackhole.consume(javaStringConcat(random.nextBoolean(),
                                           random.nextBoolean(),
                                           random.nextBoolean(),
                                           random.nextBoolean()));
    }

    private static String stringBuilder(boolean a, boolean b, boolean c, boolean d) {
        return new StringBuilder()
                .append(a)
                .append(" + ")
                .append(b)
                .append(" * ")
                .append(c)
                .append(" = ")
                .append(d)
                .toString();
    }

    @Benchmark
    public void stringBuilder(Blackhole blackhole) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        blackhole.consume(stringBuilder(random.nextBoolean(),
                                        random.nextBoolean(),
                                        random.nextBoolean(),
                                        random.nextBoolean()));
    }

    @Benchmark
    public void autoStringFormatter(Blackhole blackhole) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        blackhole.consume(formatter.format(random.nextBoolean(),
                                           random.nextBoolean(),
                                           random.nextBoolean(),
                                           random.nextBoolean()));
    }
}
