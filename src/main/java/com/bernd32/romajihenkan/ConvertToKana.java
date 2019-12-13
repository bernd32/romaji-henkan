package com.bernd32.romajihenkan;

import com.atilika.kuromoji.ipadic.Token;
import com.atilika.kuromoji.ipadic.Tokenizer;

import java.util.List;

/*
 *  Convert string in Japanese to Katakana
 */

class ConvertToKana {

    String convert(String text) {
        Tokenizer tokenizer = new Tokenizer();
        List<Token> tokens = tokenizer.tokenize(text);
        StringBuilder outputBuilder = new StringBuilder();

        for (Token token : tokens) {
            String reading = getReading(token);
            String type = getType(token);
            if (token.getSurface().charAt(0)=='\n') {
                outputBuilder.append('\n');
                continue;
            }
            if (token.getSurface().equals(" ")) {
                continue; // Skip whitespaces
            }
            if (hasNoReading(token)) {
                //Don't convert tokens that don't have a reading
                outputBuilder.append(token.getSurface());
                outputBuilder.append(" ");
                continue;
            }
            if( token.getAllFeaturesArray()[0].equals("記号"))  {
                // Avoid double spaces
                if(type.equals("空白") && getReading(token).equals("　")) {
                    continue;
                }
            }
            if (reading.endsWith("ッ")) {
                // Do not append space after sokuon
                outputBuilder.append(reading);
                continue;
            }
            outputBuilder.append(reading);
            outputBuilder.append(" ");
        }
        return outputBuilder.toString();
    }

    private boolean hasNoReading(Token token) {
        return getReading(token).equals("*");
    }

    private String getReading(Token token) {
        // Returns reading of Japanese word in token in katakana
        return token.getAllFeaturesArray()[8];
    }

    private String getType(Token token) {
        return token.getAllFeaturesArray()[1];
    }
}
