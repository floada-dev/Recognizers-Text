// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.recognizers.text.datetime.swedish.extractors;

import com.microsoft.recognizers.text.IExtractor;
import com.microsoft.recognizers.text.datetime.DateTimeOptions;
import com.microsoft.recognizers.text.datetime.config.BaseOptionsConfiguration;
import com.microsoft.recognizers.text.datetime.extractors.BaseTimeExtractor;
import com.microsoft.recognizers.text.datetime.extractors.BaseTimeZoneExtractor;
import com.microsoft.recognizers.text.datetime.extractors.IDateTimeExtractor;
import com.microsoft.recognizers.text.datetime.extractors.config.ITimePeriodExtractorConfiguration;
import com.microsoft.recognizers.text.datetime.extractors.config.ResultIndex;
import com.microsoft.recognizers.text.datetime.resources.SwedishDateTime;
import com.microsoft.recognizers.text.datetime.swedish.utilities.SwedishDatetimeUtilityConfiguration;
import com.microsoft.recognizers.text.datetime.utilities.IDateTimeUtilityConfiguration;
import com.microsoft.recognizers.text.number.swedish.extractors.IntegerExtractor;
import com.microsoft.recognizers.text.utilities.RegExpUtility;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class SwedishTimePeriodExtractorConfiguration extends BaseOptionsConfiguration implements ITimePeriodExtractorConfiguration {

    private String tokenBeforeDate;

    public final String getTokenBeforeDate() {
        return tokenBeforeDate;
    }

    public static final Pattern AmRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.AmRegex);
    public static final Pattern PmRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.PmRegex);
    public static final Pattern HourRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.HourRegex);
    public static final Pattern TillRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.TillRegex);
    public static final Pattern PeriodDescRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.DescRegex);
    public static final Pattern PureNumFromTo = RegExpUtility.getSafeRegExp(SwedishDateTime.PureNumFromTo);
    public static final Pattern TimeUnitRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.TimeUnitRegex);
    public static final Pattern TimeOfDayRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.TimeOfDayRegex);
    public static final Pattern PrepositionRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.PrepositionRegex);
    public static final Pattern TimeFollowedUnit = RegExpUtility.getSafeRegExp(SwedishDateTime.TimeFollowedUnit);
    public static final Pattern PureNumBetweenAnd = RegExpUtility.getSafeRegExp(SwedishDateTime.PureNumBetweenAnd);
    public static final Pattern GeneralEndingRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.GeneralEndingRegex);
    public static final Pattern PeriodHourNumRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.PeriodHourNumRegex);
    public static final Pattern SpecificTimeFromTo = RegExpUtility.getSafeRegExp(SwedishDateTime.SpecificTimeFromTo);
    public static final Pattern SpecificTimeBetweenAnd = RegExpUtility.getSafeRegExp(SwedishDateTime.SpecificTimeBetweenAnd);
    public static final Pattern SpecificTimeOfDayRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.SpecificTimeOfDayRegex);
    public static final Pattern TimeNumberCombinedWithUnit = RegExpUtility.getSafeRegExp(SwedishDateTime.TimeNumberCombinedWithUnit);

    public SwedishTimePeriodExtractorConfiguration() {
        this(DateTimeOptions.None);
    }

    public SwedishTimePeriodExtractorConfiguration(DateTimeOptions options) {

        super(options);

        tokenBeforeDate = SwedishDateTime.TokenBeforeDate;
        singleTimeExtractor = new BaseTimeExtractor(new SwedishTimeExtractorConfiguration(options));
        utilityConfiguration = new SwedishDatetimeUtilityConfiguration();
        integerExtractor = IntegerExtractor.getInstance();
        timeZoneExtractor = new BaseTimeZoneExtractor(new SwedishTimeZoneExtractorConfiguration(options));
    }

    private IDateTimeUtilityConfiguration utilityConfiguration;

    public final IDateTimeUtilityConfiguration getUtilityConfiguration() {
        return utilityConfiguration;
    }

    private IDateTimeExtractor singleTimeExtractor;

    public final IDateTimeExtractor getSingleTimeExtractor() {
        return singleTimeExtractor;
    }

    private IExtractor integerExtractor;

    public final IExtractor getIntegerExtractor() {
        return integerExtractor;
    }

    private final IDateTimeExtractor timeZoneExtractor;

    public IDateTimeExtractor getTimeZoneExtractor() {
        return timeZoneExtractor;
    }


    public Iterable<Pattern> getSimpleCasesRegex() {
        return getSimpleCasesRegex;
    }

    public final Iterable<Pattern> getSimpleCasesRegex = new ArrayList<Pattern>() {
        {
            add(PureNumFromTo);
            add(PureNumBetweenAnd);
            add(SpecificTimeFromTo);
            add(SpecificTimeBetweenAnd);
        }
    };

    public final Pattern getTillRegex() {
        return TillRegex;
    }

    public final Pattern getTimeOfDayRegex() {
        return TimeOfDayRegex;
    }

    public final Pattern getGeneralEndingRegex() {
        return GeneralEndingRegex;
    }

    public final ResultIndex getFromTokenIndex(String input) {
        ResultIndex result = new ResultIndex(false, -1);
        if (input.endsWith("from")) {
            result = new ResultIndex(true, input.lastIndexOf("from"));
        }

        return result;
    }

    public final ResultIndex getBetweenTokenIndex(String input) {
        ResultIndex result = new ResultIndex(false, -1);
        if (input.endsWith("between")) {
            result = new ResultIndex(true, input.lastIndexOf("between"));
        }

        return result;
    }

    public final boolean hasConnectorToken(String input) {
        return input.equals("and");
    }
}
