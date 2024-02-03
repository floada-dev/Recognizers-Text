// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.recognizers.text.datetime.swedish.extractors;

import com.microsoft.recognizers.text.IExtractor;
import com.microsoft.recognizers.text.datetime.DateTimeOptions;
import com.microsoft.recognizers.text.datetime.config.BaseOptionsConfiguration;
import com.microsoft.recognizers.text.datetime.extractors.BaseDateExtractor;
import com.microsoft.recognizers.text.datetime.extractors.BaseDurationExtractor;
import com.microsoft.recognizers.text.datetime.extractors.BaseTimeExtractor;
import com.microsoft.recognizers.text.datetime.extractors.IDateTimeExtractor;
import com.microsoft.recognizers.text.datetime.extractors.config.IDateTimeExtractorConfiguration;
import com.microsoft.recognizers.text.datetime.resources.SwedishDateTime;
import com.microsoft.recognizers.text.datetime.swedish.utilities.SwedishDatetimeUtilityConfiguration;
import com.microsoft.recognizers.text.datetime.utilities.IDateTimeUtilityConfiguration;
import com.microsoft.recognizers.text.number.swedish.extractors.IntegerExtractor;
import com.microsoft.recognizers.text.utilities.RegExpUtility;
import com.microsoft.recognizers.text.utilities.StringUtility;

import java.util.Arrays;
import java.util.regex.Pattern;

public class SwedishDateTimeExtractorConfiguration extends BaseOptionsConfiguration implements IDateTimeExtractorConfiguration {

    public static final Pattern PrepositionRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.PrepositionRegex);
    public static final Pattern NowRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.NowRegex);
    public static final Pattern SuffixRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.SuffixRegex);
    public static final Pattern TimeOfDayRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.TimeOfDayRegex);
    public static final Pattern SpecificTimeOfDayRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.SpecificTimeOfDayRegex);
    public static final Pattern TimeOfTodayAfterRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.TimeOfTodayAfterRegex);
    public static final Pattern TimeOfTodayBeforeRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.TimeOfTodayBeforeRegex);
    public static final Pattern SimpleTimeOfTodayAfterRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.SimpleTimeOfTodayAfterRegex);
    public static final Pattern SimpleTimeOfTodayBeforeRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.SimpleTimeOfTodayBeforeRegex);
    public static final Pattern SpecificEndOfRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.SpecificEndOfRegex);
    public static final Pattern UnspecificEndOfRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.UnspecificEndOfRegex);
    public static final Pattern UnitRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.TimeUnitRegex);
    public static final Pattern ConnectorRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.ConnectorRegex);
    public static final Pattern NumberAsTimeRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.NumberAsTimeRegex);
    public static final Pattern DateNumberConnectorRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.DateNumberConnectorRegex);
    public static final Pattern SuffixAfterRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.SuffixAfterRegex);

    public IExtractor integerExtractor;
    public IDateTimeExtractor datePointExtractor;
    public IDateTimeExtractor timePointExtractor;
    public IDateTimeExtractor durationExtractor;
    public IDateTimeUtilityConfiguration utilityConfiguration;

    public SwedishDateTimeExtractorConfiguration(DateTimeOptions options) {

        super(options);

        integerExtractor = IntegerExtractor.getInstance();
        datePointExtractor = new BaseDateExtractor(new SwedishDateExtractorConfiguration(this));
        timePointExtractor = new BaseTimeExtractor(new SwedishTimeExtractorConfiguration(options));
        durationExtractor = new BaseDurationExtractor(new SwedishDurationExtractorConfiguration(options));

        utilityConfiguration = new SwedishDatetimeUtilityConfiguration();
    }

    public SwedishDateTimeExtractorConfiguration() {
        this(DateTimeOptions.None);
    }

    @Override
    public Pattern getNowRegex() {
        return NowRegex;
    }

    @Override
    public Pattern getSuffixRegex() {
        return SuffixRegex;
    }

    @Override
    public Pattern getTimeOfTodayAfterRegex() {
        return TimeOfTodayAfterRegex;
    }

    @Override
    public Pattern getSimpleTimeOfTodayAfterRegex() {
        return SimpleTimeOfTodayAfterRegex;
    }

    @Override
    public Pattern getTimeOfTodayBeforeRegex() {
        return TimeOfTodayBeforeRegex;
    }

    @Override
    public Pattern getSimpleTimeOfTodayBeforeRegex() {
        return SimpleTimeOfTodayBeforeRegex;
    }

    @Override
    public Pattern getTimeOfDayRegex() {
        return TimeOfDayRegex;
    }

    @Override
    public Pattern getSpecificEndOfRegex() {
        return SpecificEndOfRegex;
    }

    @Override
    public Pattern getUnspecificEndOfRegex() {
        return UnspecificEndOfRegex;
    }

    @Override
    public Pattern getUnitRegex() {
        return UnitRegex;
    }

    @Override
    public Pattern getNumberAsTimeRegex() {
        return NumberAsTimeRegex;
    }

    @Override
    public Pattern getDateNumberConnectorRegex() {
        return DateNumberConnectorRegex;
    }

    @Override
    public Pattern getSuffixAfterRegex() {
        return SuffixAfterRegex;
    }

    @Override
    public IDateTimeExtractor getDurationExtractor() {
        return durationExtractor;
    }

    @Override
    public IDateTimeExtractor getDatePointExtractor() {
        return datePointExtractor;
    }

    @Override
    public IDateTimeExtractor getTimePointExtractor() {
        return timePointExtractor;
    }

    @Override
    public IExtractor getIntegerExtractor() {
        return integerExtractor;
    }

    @Override
    public IDateTimeUtilityConfiguration getUtilityConfiguration() {
        return utilityConfiguration;
    }

    public boolean isConnector(String text) {

        text = text.trim();

        boolean isPreposition = Arrays.stream(RegExpUtility.getMatches(PrepositionRegex, text)).findFirst().isPresent();
        boolean isConnector = Arrays.stream(RegExpUtility.getMatches(ConnectorRegex, text)).findFirst().isPresent();
        return (StringUtility.isNullOrEmpty(text) || isPreposition || isConnector);
    }
}
