/*
 * Copyright (C) 2018 Masahiro Ide
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

package com.github.imasahiro.stringformatter.it;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.github.imasahiro.stringformatter.annotation.AutoStringFormatter;
import com.github.imasahiro.stringformatter.annotation.Format;

public class CompatibilityTest {

  @AutoStringFormatter
  interface Formatter {
    @Format("%s")
    String boolToString(boolean value);

    @Format("%s")
    String intToString(int value);
  }

  private Formatter formatter = new CompatibilityTest_Formatter();

  @Test
  public void bool() {
    assertThat(formatter.boolToString(true)).isEqualTo(String.format("%s", true));
    assertThat(formatter.boolToString(false)).isEqualTo(String.format("%s", false));
  }

  @Test
  public void integer() {
    assertThat(formatter.intToString(0)).isEqualTo(String.format("%s", 0));
    assertThat(formatter.intToString(1)).isEqualTo(String.format("%s", 1));
    assertThat(formatter.intToString(-1)).isEqualTo(String.format("%s", -1));
    assertThat(formatter.intToString(Integer.MAX_VALUE)).isEqualTo(String.format("%s", Integer.MAX_VALUE));
    assertThat(formatter.intToString(Integer.MIN_VALUE)).isEqualTo(String.format("%s", Integer.MIN_VALUE));
  }
}
