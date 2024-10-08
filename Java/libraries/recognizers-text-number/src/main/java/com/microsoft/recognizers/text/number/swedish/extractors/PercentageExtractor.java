// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.recognizers.text.number.swedish.extractors;

import com.microsoft.recognizers.text.number.NumberMode;
import com.microsoft.recognizers.text.number.NumberOptions;
import com.microsoft.recognizers.text.number.extractors.BasePercentageExtractor;
import com.microsoft.recognizers.text.number.resources.SwedishNumeric;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class PercentageExtractor extends BasePercentageExtractor {

    private final NumberOptions options;
    private final Set<Pattern> regexes;

    @Override
    public NumberOptions getOptions() {
        return options;
    }

    public PercentageExtractor() {
        this(NumberOptions.None);
    }

    public PercentageExtractor(NumberOptions options) {
        super(NumberExtractor.getInstance(NumberMode.Default, options));

        this.options = options;

        Set<String> builder = new HashSet<>();
        builder.add(SwedishNumeric.NumberWithSuffixPercentage);
        builder.add(SwedishNumeric.NumberWithPrefixPercentage);

        if ((options.ordinal() & NumberOptions.PercentageMode.ordinal()) != 0) {
            builder.add(SwedishNumeric.FractionNumberWithSuffixPercentage);
            builder.add(SwedishNumeric.NumberWithPrepositionPercentage);
        }

        this.regexes = buildRegexes(builder);
    }

    @Override
    protected Set<Pattern> getRegexes() {
        return this.regexes;
    }
}
