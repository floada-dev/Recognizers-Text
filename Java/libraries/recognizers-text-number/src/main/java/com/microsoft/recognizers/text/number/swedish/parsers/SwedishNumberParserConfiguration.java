// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.recognizers.text.number.swedish.parsers;

import com.microsoft.recognizers.text.Culture;
import com.microsoft.recognizers.text.CultureInfo;
import com.microsoft.recognizers.text.ParseResult;
import com.microsoft.recognizers.text.number.NumberOptions;
import com.microsoft.recognizers.text.number.parsers.BaseNumberParserConfiguration;
import com.microsoft.recognizers.text.number.resources.SwedishNumeric;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class SwedishNumberParserConfiguration extends BaseNumberParserConfiguration {

    public SwedishNumberParserConfiguration() {
        this(NumberOptions.None);
    }

    public SwedishNumberParserConfiguration(NumberOptions options) {
        this(new CultureInfo(Culture.Swedish), options);
    }

    public SwedishNumberParserConfiguration(CultureInfo cultureInfo, NumberOptions options) {
        super(
                SwedishNumeric.LangMarker,
                cultureInfo,
                SwedishNumeric.CompoundNumberLanguage,
                SwedishNumeric.MultiDecimalSeparatorCulture,
                options,
                SwedishNumeric.NonDecimalSeparatorChar,
                SwedishNumeric.DecimalSeparatorChar,
                SwedishNumeric.FractionMarkerToken,
                SwedishNumeric.HalfADozenText,
                SwedishNumeric.WordSeparatorToken,
                SwedishNumeric.WrittenDecimalSeparatorTexts,
                SwedishNumeric.WrittenGroupSeparatorTexts,
                SwedishNumeric.WrittenIntegerSeparatorTexts,
                SwedishNumeric.WrittenFractionSeparatorTexts,
                SwedishNumeric.CardinalNumberMap,
                SwedishNumeric.OrdinalNumberMap,
                SwedishNumeric.RoundNumberMap,
                Pattern.compile(SwedishNumeric.HalfADozenRegex, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS),
                Pattern.compile(SwedishNumeric.DigitalNumberRegex, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS),
                Pattern.compile(SwedishNumeric.NegativeNumberSignRegex, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS),
                Pattern.compile(SwedishNumeric.FractionPrepositionRegex, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS)
        );
    }

    @Override
    public List<String> normalizeTokenSet(List<String> tokens, ParseResult context) {
        List<String> words = new ArrayList<>();

        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).contains("-")) {
                String[] splitTokens = tokens.get(i).split(Pattern.quote("-"));
                if (splitTokens.length == 2 && getOrdinalNumberMap().containsKey(splitTokens[1])) {
                    words.add(splitTokens[0]);
                    words.add(splitTokens[1]);
                } else {
                    words.add(tokens.get(i));
                }
            } else if (i < tokens.size() - 2 && tokens.get(i + 1).equals("-")) {
                if (getOrdinalNumberMap().containsKey(tokens.get(i + 2))) {
                    words.add(tokens.get(i));
                    words.add(tokens.get(i + 2));
                } else {
                    words.add(tokens.get(i) + tokens.get(i + 1) + tokens.get(i + 2));
                }

                i += 2;
            } else {
                words.add(tokens.get(i));
            }
        }

        return words;
    }

    @Override
    public long resolveCompositeNumber(String numberStr) {
        // Swedish Ordinals can't be used for denoting fractions as in other languages, e.g. English.
        // The default method uses the OrdinalNumberMap map to find a fraction expression.
        // When parsing swedish fractions, such as "en tjugoförstedel" (1/21) this method
        // fails to find the corresponding Ordinal since this doesn't exists in the OrdinalNumberMap.
        long resolvedNumber = super.resolveCompositeNumber(numberStr);

        // So, if resolvedNumber == 0 we test for fractions and thus choose to
        // use the fallback swedishWrittenFractionLookupMap map to try to
        // find the corresponding value.
        if (resolvedNumber == 0) {
            // The swedishWrittenFractionLookupMap map contains the leading parts of all
            // tenths fractions, e.g.
            // 21: "tjugoförst" -> "tjugoförst(a|e)del(s|ar(na)?s?)"
            // 26: "tjugosjätted" -> "tjugosjätted(el(s|ar(na)?s?)"
            resolvedNumber = SwedishNumeric.SwedishWrittenFractionLookupMap.entrySet()
                    .stream()
                    .filter(e -> numberStr.toLowerCase().startsWith(e.getKey().toLowerCase()))
                    .findFirst()
                    .map(Map.Entry::getValue)
                    .orElse(0L);
        }

        return resolvedNumber;

    }
}
