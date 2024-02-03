// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.recognizers.text.number.swedish.extractors;

import com.microsoft.recognizers.text.number.NumberMode;
import com.microsoft.recognizers.text.number.NumberOptions;
import com.microsoft.recognizers.text.number.NumberRangeConstants;
import com.microsoft.recognizers.text.number.extractors.BaseNumberRangeExtractor;
import com.microsoft.recognizers.text.number.parsers.BaseNumberParser;
import com.microsoft.recognizers.text.number.resources.SwedishNumeric;
import com.microsoft.recognizers.text.number.swedish.parsers.SwedishNumberParserConfiguration;
import com.microsoft.recognizers.text.utilities.RegExpUtility;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class NumberRangeExtractor extends BaseNumberRangeExtractor {

    private final NumberOptions options;
    private final Map<Pattern, String> regexes;

    @Override
    public NumberOptions getOptions() {
        return options;
    }

    public NumberRangeExtractor() {
        this(NumberOptions.None);
    }

    public NumberRangeExtractor(NumberOptions options) {
        super(NumberExtractor.getInstance(NumberMode.Default, options), OrdinalExtractor.getInstance(), new BaseNumberParser(new SwedishNumberParserConfiguration()));

        this.options = options;

        HashMap<Pattern, String> builder = new HashMap<>();

        // between...and...
        builder.put(Pattern.compile(SwedishNumeric.TwoNumberRangeRegex1, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS), NumberRangeConstants.TWONUMBETWEEN);
        // more than ... less than ...
        builder.put(RegExpUtility.getSafeRegExp(SwedishNumeric.TwoNumberRangeRegex2, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS), NumberRangeConstants.TWONUM);
        // less than ... more than ...
        builder.put(RegExpUtility.getSafeRegExp(SwedishNumeric.TwoNumberRangeRegex3, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS), NumberRangeConstants.TWONUM);
        // from ... to/~/- ...
        builder.put(RegExpUtility.getSafeLookbehindRegExp(SwedishNumeric.TwoNumberRangeRegex4, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS), NumberRangeConstants.TWONUMTILL);
        // more/greater/higher than ...
        builder.put(Pattern.compile(SwedishNumeric.OneNumberRangeMoreRegex1, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS), NumberRangeConstants.MORE);
        // 30 and/or greater/higher
        builder.put(Pattern.compile(SwedishNumeric.OneNumberRangeMoreRegex2, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS), NumberRangeConstants.MORE);
        // less/smaller/lower than ...
        builder.put(Pattern.compile(SwedishNumeric.OneNumberRangeLessRegex1, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS), NumberRangeConstants.LESS);
        // 30 and/or less/smaller/lower
        builder.put(Pattern.compile(SwedishNumeric.OneNumberRangeLessRegex2, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS), NumberRangeConstants.LESS);
        // equal to ...
        builder.put(RegExpUtility.getSafeLookbehindRegExp(SwedishNumeric.OneNumberRangeEqualRegex, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS), NumberRangeConstants.EQUAL);
        // equal to 30 or more than, larger than 30 or equal to ...
        builder.put(RegExpUtility.getSafeRegExp(SwedishNumeric.OneNumberRangeMoreSeparateRegex, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS),
                NumberRangeConstants.MORE);
        // equal to 30 or less, smaller than 30 or equal ...
        builder.put(RegExpUtility.getSafeRegExp(SwedishNumeric.OneNumberRangeLessSeparateRegex, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS),
                NumberRangeConstants.LESS);

        this.regexes = Collections.unmodifiableMap(builder);
    }

    @Override
    protected Map<Pattern, String> getRegexes() {
        return this.regexes;
    }
}
