// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.recognizers.text.number.swedish.extractors;

import com.microsoft.recognizers.text.number.Constants;
import com.microsoft.recognizers.text.number.LongFormatType;
import com.microsoft.recognizers.text.number.NumberOptions;
import com.microsoft.recognizers.text.number.extractors.BaseNumberExtractor;
import com.microsoft.recognizers.text.number.resources.SwedishNumeric;
import com.microsoft.recognizers.text.utilities.RegExpUtility;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public class IntegerExtractor extends BaseNumberExtractor {

    private final Map<Pattern, String> regexes;

    @Override
    protected Map<Pattern, String> getRegexes() {
        return this.regexes;
    }

    @Override
    protected String getExtractType() {
        return Constants.SYS_NUM_INTEGER;
    }

    @Override
    protected NumberOptions getOptions() {
        return NumberOptions.None;
    }

    @Override
    protected Optional<Pattern> getNegativeNumberTermsRegex() {
        return Optional.empty();
    }

    private static final ConcurrentHashMap<String, IntegerExtractor> instances = new ConcurrentHashMap<>();

    public static IntegerExtractor getInstance() {
        return getInstance(SwedishNumeric.PlaceHolderDefault);
    }

    public static IntegerExtractor getInstance(String placeholder) {
        if (!instances.containsKey(placeholder)) {
            IntegerExtractor instance = new IntegerExtractor(placeholder);
            instances.put(placeholder, instance);
        }

        return instances.get(placeholder);
    }

    private IntegerExtractor() {
        this(SwedishNumeric.PlaceHolderDefault);
    }

    private IntegerExtractor(String placeholder) {
        HashMap<Pattern, String> builder = new HashMap<>();

        builder.put(RegExpUtility.getSafeLookbehindRegExp(SwedishNumeric.NumbersWithPlaceHolder(placeholder), Pattern.UNICODE_CHARACTER_CLASS), "IntegerNum");
        builder.put(RegExpUtility.getSafeLookbehindRegExp(SwedishNumeric.NumbersWithSuffix), "IntegerNum");
        builder.put(Pattern.compile(SwedishNumeric.RoundNumberIntegerRegexWithLocks, Pattern.UNICODE_CHARACTER_CLASS), "IntegerNum");
        builder.put(RegExpUtility.getSafeLookbehindRegExp(SwedishNumeric.NumbersWithDozenSuffix, Pattern.UNICODE_CHARACTER_CLASS), "IntegerNum");
        builder.put(Pattern.compile(SwedishNumeric.AllIntRegexWithLocks, Pattern.UNICODE_CHARACTER_CLASS), "IntegerSwe");
        builder.put(Pattern.compile(SwedishNumeric.AllIntRegexWithDozenSuffixLocks, Pattern.UNICODE_CHARACTER_CLASS), "IntegerSwe");
        builder.put(generateLongFormatNumberRegexes(LongFormatType.IntegerNumComma, placeholder), "IntegerNum");
        builder.put(generateLongFormatNumberRegexes(LongFormatType.IntegerNumBlank, placeholder), "IntegerNum");
        builder.put(generateLongFormatNumberRegexes(LongFormatType.IntegerNumNoBreakSpace, placeholder), "IntegerNum");

        this.regexes = Collections.unmodifiableMap(builder);
    }
}
