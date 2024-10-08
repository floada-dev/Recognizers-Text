// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.recognizers.text.datetime.swedish.parsers;

import com.google.common.collect.ImmutableMap;
import com.microsoft.recognizers.text.IExtractor;
import com.microsoft.recognizers.text.datetime.Constants;
import com.microsoft.recognizers.text.datetime.config.BaseOptionsConfiguration;
import com.microsoft.recognizers.text.datetime.extractors.IDateTimeExtractor;
import com.microsoft.recognizers.text.datetime.parsers.IDateTimeParser;
import com.microsoft.recognizers.text.datetime.parsers.config.ICommonDateTimeParserConfiguration;
import com.microsoft.recognizers.text.datetime.parsers.config.ITimePeriodParserConfiguration;
import com.microsoft.recognizers.text.datetime.parsers.config.MatchedTimeRangeResult;
import com.microsoft.recognizers.text.datetime.resources.SwedishDateTime;
import com.microsoft.recognizers.text.datetime.swedish.extractors.SwedishTimePeriodExtractorConfiguration;
import com.microsoft.recognizers.text.datetime.utilities.IDateTimeUtilityConfiguration;
import com.microsoft.recognizers.text.datetime.utilities.TimeOfDayResolutionResult;
import com.microsoft.recognizers.text.datetime.utilities.TimexUtility;

import java.util.regex.Pattern;

public class SwedishTimePeriodParserConfiguration extends BaseOptionsConfiguration implements ITimePeriodParserConfiguration {

    private final IDateTimeExtractor timeExtractor;
    private final IDateTimeParser timeParser;
    private final IExtractor integerExtractor;
    private final IDateTimeParser timeZoneParser;

    private final Pattern specificTimeFromToRegex;
    private final Pattern specificTimeBetweenAndRegex;
    private final Pattern pureNumberFromToRegex;
    private final Pattern pureNumberBetweenAndRegex;
    private final Pattern timeOfDayRegex;
    private final Pattern generalEndingRegex;
    private final Pattern tillRegex;

    private final IDateTimeUtilityConfiguration utilityConfiguration;
    private final ImmutableMap<String, Integer> numbers;

    public SwedishTimePeriodParserConfiguration(ICommonDateTimeParserConfiguration config) {

        super(config.getOptions());

        timeExtractor = config.getTimeExtractor();
        integerExtractor = config.getIntegerExtractor();
        timeParser = config.getTimeParser();
        timeZoneParser = config.getTimeZoneParser();
        numbers = config.getNumbers();
        utilityConfiguration = config.getUtilityConfiguration();

        pureNumberFromToRegex = SwedishTimePeriodExtractorConfiguration.PureNumFromTo;
        pureNumberBetweenAndRegex = SwedishTimePeriodExtractorConfiguration.PureNumBetweenAnd;
        specificTimeFromToRegex = SwedishTimePeriodExtractorConfiguration.SpecificTimeFromTo;
        specificTimeBetweenAndRegex = SwedishTimePeriodExtractorConfiguration.SpecificTimeBetweenAnd;
        timeOfDayRegex = SwedishTimePeriodExtractorConfiguration.TimeOfDayRegex;

        generalEndingRegex = SwedishTimePeriodExtractorConfiguration.GeneralEndingRegex;
        tillRegex = SwedishTimePeriodExtractorConfiguration.TillRegex;
    }

    @Override
    public IDateTimeExtractor getTimeExtractor() {
        return timeExtractor;
    }

    @Override
    public IDateTimeParser getTimeParser() {
        return timeParser;
    }

    @Override
    public IExtractor getIntegerExtractor() {
        return integerExtractor;
    }

    @Override
    public IDateTimeParser getTimeZoneParser() {
        return timeZoneParser;
    }

    @Override
    public Pattern getPureNumberFromToRegex() {
        return pureNumberFromToRegex;
    }

    @Override
    public Pattern getPureNumberBetweenAndRegex() {
        return pureNumberBetweenAndRegex;
    }

    @Override
    public Pattern getSpecificTimeFromToRegex() {
        return specificTimeFromToRegex;
    }

    @Override
    public Pattern getSpecificTimeBetweenAndRegex() {
        return specificTimeBetweenAndRegex;
    }

    @Override
    public Pattern getTimeOfDayRegex() {
        return timeOfDayRegex;
    }

    @Override
    public Pattern getGeneralEndingRegex() {
        return generalEndingRegex;
    }

    @Override
    public Pattern getTillRegex() {
        return tillRegex;
    }

    @Override
    public ImmutableMap<String, Integer> getNumbers() {
        return numbers;
    }

    @Override
    public IDateTimeUtilityConfiguration getUtilityConfiguration() {
        return utilityConfiguration;
    }

    @Override
    public MatchedTimeRangeResult getMatchedTimexRange(String text, String timex, int beginHour, int endHour, int endMin) {

        String trimmedText = text.trim().toLowerCase();
        if (trimmedText.endsWith("s")) {
            trimmedText = trimmedText.substring(0, trimmedText.length() - 1);
        }

        beginHour = 0;
        endHour = 0;
        endMin = 0;

        String timeOfDay = "";

        if (SwedishDateTime.MorningTermList.stream().anyMatch(trimmedText::endsWith)) {
            timeOfDay = Constants.Morning;
        } else if (SwedishDateTime.AfternoonTermList.stream().anyMatch(trimmedText::endsWith)) {
            timeOfDay = Constants.Afternoon;
        } else if (SwedishDateTime.EveningTermList.stream().anyMatch(trimmedText::endsWith)) {
            timeOfDay = Constants.Evening;
        } else if (SwedishDateTime.DaytimeTermList.stream().anyMatch(trimmedText::equals)) {
            timeOfDay = Constants.Daytime;
        } else if (SwedishDateTime.NightTermList.stream().anyMatch(trimmedText::endsWith)) {
            timeOfDay = Constants.Night;
        } else if (SwedishDateTime.BusinessHourSplitStrings.stream().allMatch(trimmedText::contains)) {
            timeOfDay = Constants.BusinessHour;
        } else {
            timex = null;
            return new MatchedTimeRangeResult(false, timex, beginHour, endHour, endMin);
        }

        TimeOfDayResolutionResult result = TimexUtility.parseTimeOfDay(timeOfDay);

        return new MatchedTimeRangeResult(true, result.getTimex(), result.getBeginHour(), result.getEndHour(), result.getEndMin());
    }
}
