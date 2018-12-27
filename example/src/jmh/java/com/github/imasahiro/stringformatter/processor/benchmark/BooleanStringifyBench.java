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

import java.util.Random;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

public class BooleanStringifyBench {
    private static final BooleanStringifyBenchFormatter.Formatter formatter =
            new BooleanStringifyBenchFormatter_Formatter();

    private static String javaStringFormat(String formatString, boolean a, boolean b, boolean c, boolean d) {
        return String.format(formatString, a, b, c, d);
    }

    private static final Random RANDOM = new Random();

    @Benchmark
    public void javaStringFormat(Blackhole blackhole) {
        blackhole.consume(javaStringFormat(BooleanStringifyBenchFormatter.FORMAT,
                                           RANDOM.nextBoolean(),
                                           RANDOM.nextBoolean(),
                                           RANDOM.nextBoolean(),
                                           RANDOM.nextBoolean()));
    }

    private static String javaStringConcat(boolean a, boolean b, boolean c, boolean d) {
        return a + " " + b + " " + c + " " + d;
    }

    @Benchmark
    public void javaStringConcat(Blackhole blackhole) {
        blackhole.consume(javaStringConcat(RANDOM.nextBoolean(),
                                           RANDOM.nextBoolean(),
                                           RANDOM.nextBoolean(),
                                           RANDOM.nextBoolean()));
    }

    private static String stringBuilder(boolean a, boolean b, boolean c, boolean d) {
        return new StringBuilder()
                .append(a)
                .append(' ')
                .append(b)
                .append(' ')
                .append(c)
                .append(' ')
                .append(d)
                .toString();
    }

    @Benchmark
    public void stringBuilder(Blackhole blackhole) {
        blackhole.consume(stringBuilder(RANDOM.nextBoolean(),
                                        RANDOM.nextBoolean(),
                                        RANDOM.nextBoolean(),
                                        RANDOM.nextBoolean()));
    }

    @Benchmark
    public void autoStringFormatter(Blackhole blackhole) {
        blackhole.consume(formatter.format(RANDOM.nextBoolean(),
                                           RANDOM.nextBoolean(),
                                           RANDOM.nextBoolean(),
                                           RANDOM.nextBoolean()));
    }
}
