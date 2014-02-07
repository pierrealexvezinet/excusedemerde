/*
 * StringUtility.java - Copyright (c) 2010, Backelite.
 */
package com.backelite.stats.xiti;

import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * A bunch of statics methods useful with strings.
 * @author <a href="mailto:fabien.devos@backelite.com">Fabien Devos</a>
 */
public class StringUtility {

    /** Used locally to tag Logs */
    // private static final String TAG = "StringUtility";

    /**
     * Index of first char with accent
     **/
    private static final int MIN = 192;
    /**
     * Index of last char with accent
     **/
    private static final int MAX = 255;

    /**
     * Private vector with mapping char with accent to char without accent.
     **/
    private static final Vector<String> map = initMap();

    /**
     * Take a base url and a {@link Map} of parameters to build a valid url (eg :
     * http://example.com?param1=value1&param2=value2)
     * @param baseUrl the base url (eg : http://example.com)
     * @param parameters the {@link Map} of parameters (eg : {param1=value1, param2=value2})
     * @return the builded url
     */
    public static String buildUrl(String baseUrl, Map<String, Object> parameters) {
        StringBuilder strBuilderUrl = new StringBuilder(baseUrl);
        if (parameters != null && parameters.size() > 0) {
            int i = 0;
            for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                if (i == 0) {
                    strBuilderUrl.append('?');
                } else {
                    strBuilderUrl.append('&');
                }
                strBuilderUrl.append(entry.getKey());
                strBuilderUrl.append('=');
                strBuilderUrl.append(entry.getValue());
                i++;
            }

        }
        return strBuilderUrl.toString();
    }

    /**
     * Go through the list and call "toString" (more exactly String.valueOf) on each element to append it to the result
     * String. Also put the specified separator between the strings.
     * @param <T> The type parameter of the list
     * @param list a list of T to append
     * @param separator the separator to put between elements
     * @return a String which concatenate all the elements of the list, an empty string if the list is empty.
     */
    public static <T> String listToString(List<T> list, String separator) {
        StringBuilder result = new StringBuilder("");
        for (int i = 0; i < list.size(); i++) {
            T listElement = list.get(i);
            result.append(listElement);
            if (i != list.size() - 1) {
                result.append(separator);
            }
        }
        return result.toString();

    }



    /**
     * Initialisation du tableau de correspondance entre les caractÈres accentuÈs et leur homologues non accentuÈs
     */
    private static Vector<String> initMap() {
        Vector<String> result = new Vector<String>();
        java.lang.String car = null;

        car = new java.lang.String("A");
        result.add(car); /* '\u00C0' ¿ alt-0192 */
        result.add(car); /* '\u00C1' ¡ alt-0193 */
        result.add(car); /* '\u00C2' ¬ alt-0194 */
        result.add(car); /* '\u00C3' √ alt-0195 */
        result.add(car); /* '\u00C4' ƒ alt-0196 */
        result.add(car); /* '\u00C5' ≈ alt-0197 */
        car = new java.lang.String("AE");
        result.add(car); /* '\u00C6' ∆ alt-0198 */
        car = new java.lang.String("C");
        result.add(car); /* '\u00C7' « alt-0199 */
        car = new java.lang.String("E");
        result.add(car); /* '\u00C8' » alt-0200 */
        result.add(car); /* '\u00C9' … alt-0201 */
        result.add(car); /* '\u00CA'   alt-0202 */
        result.add(car); /* '\u00CB' À alt-0203 */
        car = new java.lang.String("I");
        result.add(car); /* '\u00CC' Ã alt-0204 */
        result.add(car); /* '\u00CD' Õ alt-0205 */
        result.add(car); /* '\u00CE' Œ alt-0206 */
        result.add(car); /* '\u00CF' œ alt-0207 */
        car = new java.lang.String("D");
        result.add(car); /* '\u00D0' – alt-0208 */
        car = new java.lang.String("N");
        result.add(car); /* '\u00D1' — alt-0209 */
        car = new java.lang.String("O");
        result.add(car); /* '\u00D2' “ alt-0210 */
        result.add(car); /* '\u00D3' ” alt-0211 */
        result.add(car); /* '\u00D4' ‘ alt-0212 */
        result.add(car); /* '\u00D5' ’ alt-0213 */
        result.add(car); /* '\u00D6' ÷ alt-0214 */
        car = new java.lang.String("*");
        result.add(car); /* '\u00D7' ◊ alt-0215 */
        car = new java.lang.String("0");
        result.add(car); /* '\u00D8' ÿ alt-0216 */
        car = new java.lang.String("U");
        result.add(car); /* '\u00D9' Ÿ alt-0217 */
        result.add(car); /* '\u00DA' ⁄ alt-0218 */
        result.add(car); /* '\u00DB' € alt-0219 */
        result.add(car); /* '\u00DC' ‹ alt-0220 */
        car = new java.lang.String("Y");
        result.add(car); /* '\u00DD' › alt-0221 */
        car = new java.lang.String(""); // "ﬁ" Replace by empty string.
        result.add(car); /* '\u00DE' ﬁ alt-0222 */
        car = new java.lang.String("B");
        result.add(car); /* '\u00DF' ﬂ alt-0223 */
        car = new java.lang.String("a");
        result.add(car); /* '\u00E0' ‡ alt-0224 */
        result.add(car); /* '\u00E1' · alt-0225 */
        result.add(car); /* '\u00E2' ‚ alt-0226 */
        result.add(car); /* '\u00E3' „ alt-0227 */
        result.add(car); /* '\u00E4' ‰ alt-0228 */
        result.add(car); /* '\u00E5' Â alt-0229 */
        car = new java.lang.String("ae");
        result.add(car); /* '\u00E6' Ê alt-0230 */
        car = new java.lang.String("c");
        result.add(car); /* '\u00E7' Á alt-0231 */
        car = new java.lang.String("e");
        result.add(car); /* '\u00E8' Ë alt-0232 */
        result.add(car); /* '\u00E9' È alt-0233 */
        result.add(car); /* '\u00EA' Í alt-0234 */
        result.add(car); /* '\u00EB' Î alt-0235 */
        car = new java.lang.String("i");
        result.add(car); /* '\u00EC' Ï alt-0236 */
        result.add(car); /* '\u00ED' Ì alt-0237 */
        result.add(car); /* '\u00EE' Ó alt-0238 */
        result.add(car); /* '\u00EF' Ô alt-0239 */
        car = new java.lang.String("d");
        result.add(car); /* '\u00F0'  alt-0240 */
        car = new java.lang.String("n");
        result.add(car); /* '\u00F1' Ò alt-0241 */
        car = new java.lang.String("o");
        result.add(car); /* '\u00F2' Ú alt-0242 */
        result.add(car); /* '\u00F3' Û alt-0243 */
        result.add(car); /* '\u00F4' Ù alt-0244 */
        result.add(car); /* '\u00F5' ı alt-0245 */
        result.add(car); /* '\u00F6' ˆ alt-0246 */
        car = new java.lang.String("/");
        result.add(car); /* '\u00F7' ˜ alt-0247 */
        car = new java.lang.String("0");
        result.add(car); /* '\u00F8' ¯ alt-0248 */
        car = new java.lang.String("u");
        result.add(car); /* '\u00F9' ˘ alt-0249 */
        result.add(car); /* '\u00FA' ˙ alt-0250 */
        result.add(car); /* '\u00FB' ˚ alt-0251 */
        result.add(car); /* '\u00FC' ¸ alt-0252 */
        car = new java.lang.String("y");
        result.add(car); /* '\u00FD' ˝ alt-0253 */
        car = new java.lang.String(""); // "˛" replace by empty string.
        result.add(car); /* '\u00FE' ˛ alt-0254 */
        car = new java.lang.String("y");
        result.add(car); /* '\u00FF' ˇ alt-0255 */
        result.add(car); /* '\u00FF' alt-0255 */

        return result;
    }

    /**
     * Suppress accent.
     * @param stringWithNonAscii
     * @return
     */
    public static String toAscii(String stringWithNonAscii) {

        StringBuilder result = new StringBuilder(stringWithNonAscii);

        for (int bcl = 0; bcl < result.length(); bcl++) {
            int carVal = stringWithNonAscii.charAt(bcl);

            if (carVal >= MIN && carVal <= MAX) { // Replace
                result.replace(bcl, bcl + 1, map.get(carVal - MIN));
            }
        }

        return result.toString();
    }

//---------------------------------------------------------------------------------------------------------
// From Apache commons : WordUtils.
//---------------------------------------------------------------------------------------------------------
    /**
     * Is the character a delimiter.
     *
     * @param ch  the character to check
     * @param delimiters  the delimiters
     * @return true if it is a delimiter
     */
    private static boolean isDelimiter(char ch, char[] delimiters) {
        if (delimiters == null) {
            return Character.isWhitespace(ch);
        }
        for (int i = 0, isize = delimiters.length; i < isize; i++) {
            if (ch == delimiters[i]) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * <p>Capitalizes all the whitespace separated words in a String.
     * Only the first letter of each word is changed. To convert the 
     * rest of each word to lowercase at the same time, 
     * use {@link #capitalizeFully(String)}.</p>
     *
     * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.
     * A <code>null</code> input String returns <code>null</code>.
     * Capitalization uses the unicode title case, normally equivalent to
     * upper case.</p>
     *
     * <pre>
     * WordUtils.capitalize(null)        = null
     * WordUtils.capitalize("")          = ""
     * WordUtils.capitalize("i am FINE") = "I Am FINE"
     * </pre>
     * 
     * @param str  the String to capitalize, may be null
     * @return capitalized String, <code>null</code> if null String input
     * @see #uncapitalize(String)
     * @see #capitalizeFully(String)
     */
    public static String capitalize(String str) {
        return capitalize(str, null);
    }
    /**
     * <p>Capitalizes all the delimiter separated words in a String.
     * Only the first letter of each word is changed. To convert the 
     * rest of each word to lowercase at the same time, 
     * use {@link #capitalizeFully(String, char[])}.</p>
     *
     * <p>The delimiters represent a set of characters understood to separate words.
     * The first string character and the first non-delimiter character after a
     * delimiter will be capitalized. </p>
     *
     * <p>A <code>null</code> input String returns <code>null</code>.
     * Capitalization uses the unicode title case, normally equivalent to
     * upper case.</p>
     *
     * <pre>
     * WordUtils.capitalize(null, *)            = null
     * WordUtils.capitalize("", *)              = ""
     * WordUtils.capitalize(*, new char[0])     = *
     * WordUtils.capitalize("i am fine", null)  = "I Am Fine"
     * WordUtils.capitalize("i aM.fine", {'.'}) = "I aM.Fine"
     * </pre>
     * 
     * @param str  the String to capitalize, may be null
     * @param delimiters  set of characters to determine capitalization, null means whitespace
     * @return capitalized String, <code>null</code> if null String input
     * @see #uncapitalize(String)
     * @see #capitalizeFully(String)
     * @since 2.1
     */
    public static String capitalize(String str, char... delimiters) {
        int delimLen = (delimiters == null ? -1 : delimiters.length);
        if (str == null || str.length() == 0 || delimLen == 0) {
            return str;
        }
        int strLen = str.length();
        StringBuilder buffer = new StringBuilder(strLen);
        boolean capitalizeNext = true;
        for (int i = 0; i < strLen; i++) {
            char ch = str.charAt(i);

            if (isDelimiter(ch, delimiters)) {
                buffer.append(ch);
                capitalizeNext = true;
            } else if (capitalizeNext) {
                buffer.append(Character.toTitleCase(ch));
                capitalizeNext = false;
            } else {
                buffer.append(ch);
            }
        }
        return buffer.toString();
    }
}
