// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.recognizers.text.datetime.swedish.extractors;

import com.microsoft.recognizers.text.datetime.DateTimeOptions;
import com.microsoft.recognizers.text.datetime.config.BaseOptionsConfiguration;
import com.microsoft.recognizers.text.datetime.extractors.config.IHolidayExtractorConfiguration;
import com.microsoft.recognizers.text.datetime.resources.SwedishDateTime;
import com.microsoft.recognizers.text.utilities.RegExpUtility;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class SwedishHolidayExtractorConfiguration extends BaseOptionsConfiguration implements IHolidayExtractorConfiguration {

    public static final Pattern YearPattern = RegExpUtility.getSafeRegExp(SwedishDateTime.YearRegex);

    public static final Pattern H = RegExpUtility.getSafeRegExp(SwedishDateTime.HolidayRegex);

    public static final Iterable<Pattern> HolidayRegexList = new ArrayList<Pattern>() {
        {
            add(H);
        }
    };

    public SwedishHolidayExtractorConfiguration() {
        super(DateTimeOptions.None);
    }

    public Iterable<Pattern> getHolidayRegexes() {
        return HolidayRegexList;
    }
}
