// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.recognizers.text.datetime.swedish.extractors;

import com.google.common.collect.ImmutableMap;
import com.microsoft.recognizers.text.IExtractor;
import com.microsoft.recognizers.text.datetime.DateTimeOptions;
import com.microsoft.recognizers.text.datetime.config.BaseOptionsConfiguration;
import com.microsoft.recognizers.text.datetime.extractors.config.IDurationExtractorConfiguration;
import com.microsoft.recognizers.text.datetime.resources.SwedishDateTime;
import com.microsoft.recognizers.text.number.swedish.extractors.CardinalExtractor;
import com.microsoft.recognizers.text.utilities.RegExpUtility;

import java.util.regex.Pattern;

public class SwedishDurationExtractorConfiguration extends BaseOptionsConfiguration implements IDurationExtractorConfiguration {

    public static final Pattern DurationUnitRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.DurationUnitRegex);
    public static final Pattern SuffixAndRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.SuffixAndRegex);
    public static final Pattern DurationFollowedUnit = RegExpUtility.getSafeRegExp(SwedishDateTime.DurationFollowedUnit);
    public static final Pattern NumberCombinedWithDurationUnit = RegExpUtility.getSafeRegExp(SwedishDateTime.NumberCombinedWithDurationUnit);
    public static final Pattern AnUnitRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.AnUnitRegex);
    public static final Pattern DuringRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.DuringRegex);
    public static final Pattern AllRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.AllRegex);
    public static final Pattern HalfRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.HalfRegex);
    public static final Pattern ConjunctionRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.ConjunctionRegex);
    public static final Pattern InexactNumberRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.InexactNumberRegex);
    public static final Pattern InexactNumberUnitRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.InexactNumberUnitRegex);
    public static final Pattern RelativeDurationUnitRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.RelativeDurationUnitRegex);
    public static final Pattern DurationConnectorRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.DurationConnectorRegex);
    public static final Pattern MoreThanRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.MoreThanRegex);
    public static final Pattern LessThanRegex = RegExpUtility.getSafeRegExp(SwedishDateTime.LessThanRegex);

    private final IExtractor cardinalExtractor;
    private final ImmutableMap<String, String> unitMap;
    private final ImmutableMap<String, Long> unitValueMap;

    public SwedishDurationExtractorConfiguration() {
        this(DateTimeOptions.None);
    }

    public SwedishDurationExtractorConfiguration(DateTimeOptions options) {

        super(options);

        cardinalExtractor = CardinalExtractor.getInstance();
        unitMap = SwedishDateTime.UnitMap;
        unitValueMap = SwedishDateTime.UnitValueMap;
    }

    @Override
    public Pattern getFollowedUnit() {
        return DurationFollowedUnit;
    }

    @Override
    public Pattern getNumberCombinedWithUnit() {
        return NumberCombinedWithDurationUnit;
    }

    @Override
    public Pattern getAnUnitRegex() {
        return AnUnitRegex;
    }

    @Override
    public Pattern getDuringRegex() {
        return DuringRegex;
    }

    @Override
    public Pattern getAllRegex() {
        return AllRegex;
    }

    @Override
    public Pattern getHalfRegex() {
        return HalfRegex;
    }

    @Override
    public Pattern getSuffixAndRegex() {
        return SuffixAndRegex;
    }

    @Override
    public Pattern getConjunctionRegex() {
        return ConjunctionRegex;
    }

    @Override
    public Pattern getInexactNumberRegex() {
        return InexactNumberRegex;
    }

    @Override
    public Pattern getInexactNumberUnitRegex() {
        return InexactNumberUnitRegex;
    }

    @Override
    public Pattern getRelativeDurationUnitRegex() {
        return RelativeDurationUnitRegex;
    }

    @Override
    public Pattern getDurationUnitRegex() {
        return DurationUnitRegex;
    }

    @Override
    public Pattern getDurationConnectorRegex() {
        return DurationConnectorRegex;
    }

    @Override
    public Pattern getLessThanRegex() {
        return LessThanRegex;
    }

    @Override
    public Pattern getMoreThanRegex() {
        return MoreThanRegex;
    }

    @Override public Pattern getSpecialNumberUnitRegex() {
        return null;
    }

    @Override
    public IExtractor getCardinalExtractor() {
        return cardinalExtractor;
    }

    @Override
    public ImmutableMap<String, String> getUnitMap() {
        return unitMap;
    }

    @Override
    public ImmutableMap<String, Long> getUnitValueMap() {
        return unitValueMap;
    }
}
