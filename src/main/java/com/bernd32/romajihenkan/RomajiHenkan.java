package com.bernd32.romajihenkan;

public class RomajiHenkan {

    public String convert(String string) {

        ConvertToKana toKana = new ConvertToKana();
        string = toKana.convert(string);
        string = replaceStringWithConversionTable(string, ConversionTable.getKanaToRomaji());
        string = replaceDashMarkerWithLongVowel(string);
        string = replaceSokuonMarkersWithDoubleConsonant(string);
        return string.trim();
    }

    private String replaceStringWithConversionTable(String string, ConversionTable conversionTable) {
        StringBuilder resultBuilder = new StringBuilder();
        int i = 0;
        while (i < string.length()) {
            int maxSubstringLength = Math.min(conversionTable.getMaxKeyLength(), string.length() - i);
            // Look-up the longest substring match.
            for (int substringLength = maxSubstringLength; substringLength > 0; substringLength--) {
                String substring = string.substring(i, i + substringLength);
                String replacementString = conversionTable.get(substring);
                // Replace substring if there's a match.
                if (replacementString != null) {
                    resultBuilder.append(replacementString);
                    i += substring.length();
                    break;
                }
                // Keep original character if no match found.
                if (substringLength == 1) {
                    resultBuilder.append(substring);
                    i += 1;
                    break;
                }
            }
        }
        return resultBuilder.toString();
    }

    private String replaceSokuonMarkersWithDoubleConsonant(String string) {
        StringBuilder resultBuilder = new StringBuilder(string);
        for (int i = 0; i < string.length() - 1; i++) {
            char currentCharacter = string.charAt(i);
            char nextCharacter = string.charAt(i + 1);
            boolean isSokuon = currentCharacter == 'ッ';
            if (isSokuon && isRomanConsonant(nextCharacter)) {
                char replacementCharacter = nextCharacter == 'c' ? 't' : nextCharacter;
                resultBuilder.deleteCharAt(i);
                resultBuilder.insert(i, replacementCharacter);
            }
        }
        return resultBuilder.toString();
    }
    
    /*

    private String replaceDashMarkerWithDoubleVowel(String string) {
        StringBuilder resultBuilder = new StringBuilder(string);
        for (int i = 1; i < string.length(); i++) {
            char currentCharacter = string.charAt(i);
            char previousCharacter = string.charAt(i - 1);
            if (currentCharacter == '-' && isRomanVowel(previousCharacter)) {
                resultBuilder.deleteCharAt(i);
                resultBuilder.insert(i, previousCharacter);
            }
        }
        return resultBuilder.toString();
    }
    
    */
    
    private String replaceDashMarkerWithLongVowel(String string) {
    	String resultStr = string; 
        for (int i = 1; i < string.length(); i++) {
            char currentCharacter = string.charAt(i);
            char previousCharacter = string.charAt(i - 1);
            if (currentCharacter == '-' && isRomanVowel(previousCharacter)) {
            	switch(previousCharacter) {
	            	case 'a': resultStr = string.replace("a", "ā");
	            	case 'i': resultStr = string.replace("i", "ī");
	            	case 'u': resultStr = string.replace("u", "ū");
	            	case 'e': resultStr = string.replace("e", "ē");
	            	case 'o': resultStr = string.replace("o", "ō");
            	}
            }
        }
    	resultStr = resultStr.replace("-", "");
        return resultStr;
    }

    private static boolean isRomanConsonant(char character) {
        return character >= 'a' && character <= 'z' && ! isRomanVowel(character);
    }

    private static boolean isRomanVowel(char character) {
        return character == 'a' || character == 'i' || character == 'u' || character == 'e' || character == 'o';
    }


}
