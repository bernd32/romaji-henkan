/*
 * Copyright 2019 bernd32
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

package com.bernd32.romajihenkan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;

class ConversionTable {

    private static final String KANA_TO_ROMAJI_FILE = "/romaji_table.csv";
    private static ConversionTable kanaToRomajiTable;

    private Map<String, String> conversionMap;
    private int maxKeyLength;

    private ConversionTable(Map<String, String> conversionMap) {
        this.conversionMap = conversionMap;

        for (String key : conversionMap.keySet()) {

            if (key.length() > maxKeyLength) {
                maxKeyLength = key.length();
            }
        }
    }

    int getMaxKeyLength() {
        return maxKeyLength;
    }

    String get(String key) {
        return conversionMap.get(key);
    }

    public static synchronized ConversionTable getKanaToRomaji() {

        if (kanaToRomajiTable == null) {
            kanaToRomajiTable = createConversionTableFromResource();
        }

        return kanaToRomajiTable;
    }

    private static ConversionTable createConversionTableFromResource() {

        URL resourceUrl = ConversionTable.class.getResource(ConversionTable.KANA_TO_ROMAJI_FILE);

        assert resourceUrl != null;
        try (InputStream inputStream = resourceUrl.openStream()) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            Map<String, String> conversionMap = new TreeMap<>();

            String line;
            while ((line = reader.readLine()) != null) {

                int delimiterIndex = line.indexOf(',');

                String key = line.substring(0, delimiterIndex);
                String value = line.substring(delimiterIndex + 1);

                conversionMap.put(key, value);
            }

            return new ConversionTable(conversionMap);

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
