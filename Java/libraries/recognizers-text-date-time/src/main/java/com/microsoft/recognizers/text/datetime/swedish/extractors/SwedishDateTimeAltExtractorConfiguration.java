// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.recognizers.text.datetime.swedish.extractors;

import com.microsoft.recognizers.text.datetime.config.BaseOptionsConfiguration;
import com.microsoft.recognizers.text.datetime.config.IOptionsConfiguration;
import com.microsoft.recognizers.text.datetime.extractors.BaseDateExtractor;
import com.microsoft.recognizers.text.datetime.extractors.BaseDatePeriodExtractor;
import com.microsoft.recognizers.text.datetime.extractors.IDateExtractor;
import com.microsoft.recognizers.text.datetime.extractors.IDateTimeExtractor;
import com.microsoft.recognizers.text.datetime.extractors.config.IDateTimeAltExtractorConfiguration;
import com.microsoft.recognizers.text.datetime.resources.SwedishDateTime;
import com.microsoft.recognizers.text.utilities.RegExpUtility;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class SwedishDateTimeAltExtractorConfiguration extends BaseOptionsConfiguration implements IDateTimeAltExtractorConfiguration {

    private static final Pattern OrRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.OrRegex);
    private static final Pattern DayRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.DayRegex);
    private static final Pattern RangePrefixRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.RangePrefixRegex);

    public static final Pattern ThisPrefixRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.ThisPrefixRegex);
    public static final Pattern PreviousPrefixRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.PreviousPrefixRegex);
    public static final Pattern NextPrefixRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.NextPrefixRegex);
    public static final Pattern AmRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.AmRegex);
    public static final Pattern PmRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.PmRegex);

    public static final Iterable<Pattern> RelativePrefixList = new ArrayList<Pattern>() {
        {
            add(ThisPrefixRegex);
            add(PreviousPrefixRegex);
            add(NextPrefixRegex);
        }
    };

    public static final Iterable<Pattern> AmPmRegexList = new ArrayList<Pattern>() {
        {
            add(AmRegex);
            add(PmRegex);
        }
    };

    private final IDateExtractor dateExtractor;
    private final IDateTimeExtractor datePeriodExtractor;

    public SwedishDateTimeAltExtractorConfiguration(IOptionsConfiguration config) {
        super(config.getOptions());
        dateExtractor = new BaseDateExtractor(new SwedishDateExtractorConfiguration(this));
        datePeriodExtractor = new BaseDatePeriodExtractor(new SwedishDatePeriodExtractorConfiguration(this));
    }

    @Override
    public IDateExtractor getDateExtractor() {
        return dateExtractor;
    }

    @Override
    public IDateTimeExtractor getDatePeriodExtractor() {
        return datePeriodExtractor;
    }

    @Override
    public Iterable<Pattern> getRelativePrefixList() {
        return RelativePrefixList;
    }

    @Override
    public Iterable<Pattern> getAmPmRegexList() {
        return AmPmRegexList;
    }

    @Override
    public Pattern getOrRegex() {
        return OrRegex;
    }

    @Override
    public Pattern getThisPrefixRegex() {
        return ThisPrefixRegex;
    }

    @Override
    public Pattern getDayRegex() {
        return DayRegex;
    }

    @Override public Pattern getRangePrefixRegex() {
        return RangePrefixRegex;
    }
}
