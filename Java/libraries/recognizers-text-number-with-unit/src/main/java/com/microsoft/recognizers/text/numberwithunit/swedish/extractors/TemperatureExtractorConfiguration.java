// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.recognizers.text.numberwithunit.swedish.extractors;

import com.microsoft.recognizers.text.Culture;
import com.microsoft.recognizers.text.CultureInfo;
import com.microsoft.recognizers.text.numberwithunit.Constants;
import com.microsoft.recognizers.text.numberwithunit.resources.BaseUnits;
import com.microsoft.recognizers.text.numberwithunit.resources.SwedishNumericWithUnit;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class TemperatureExtractorConfiguration extends SwedishNumberWithUnitExtractorConfiguration {

    private final Pattern ambiguousUnitNumberMultiplierRegex;

    public TemperatureExtractorConfiguration() {
        this(new CultureInfo(Culture.Swedish));
    }

    public TemperatureExtractorConfiguration(CultureInfo ci) {
        super(ci);

        this.ambiguousUnitNumberMultiplierRegex =
                Pattern.compile(BaseUnits.AmbiguousUnitNumberMultiplierRegex, Pattern.UNICODE_CHARACTER_CLASS);
    }

    @Override
    public String getExtractType() {
        return Constants.SYS_UNIT_TEMPERATURE;
    }

    @Override
    public Map<String, String> getSuffixList() {
        return TemperatureSuffixList;
    }

    @Override
    public Map<String, String> getPrefixList() {
        return Collections.emptyMap();
    }

    @Override
    public Pattern getAmbiguousUnitNumberMultiplierRegex() {
        return this.ambiguousUnitNumberMultiplierRegex;
    }

    @Override
    public List<String> getAmbiguousUnitList() {
        return SwedishNumericWithUnit.AmbiguousTemperatureUnitList;
    }

    public static Map<String, String> TemperatureSuffixList = new HashMap<String, String>(SwedishNumericWithUnit.TemperatureSuffixList);
}
