package com.github.paczek224.tests.e2e;

import com.microsoft.playwright.options.AriaRole;
import io.cucumber.java.ParameterType;

import java.util.List;
import java.util.stream.Stream;

public class ParameterTypes {

    @ParameterType("(with|with not|without)")
    public boolean withOrWithNot(String withOrWithNot) {
        return withOrWithNot.equals("with");
    }

    @ParameterType("(is|is not|is no)")
    public boolean isOrIsNot(String isOrIsNot) {
        return isOrIsNot.equals("is");
    }

    @ParameterType("(?i)(enabled|disabled)")
    public boolean enabledOrDisabled(String state) {
        return state.equalsIgnoreCase("enabled");
    }

    @ParameterType("(.*)")
    public AriaRole ariaRole(String role) {
        return AriaRole.valueOf(role.toUpperCase());
    }

    @ParameterType("(?i)(expand|collapse|select)")
    public String action(String text) {
        return text;
    }

    @ParameterType("(?i)(1st|2nd|3rd|4th|5th)")
    public int ordinal(String ordinal) {
        return Integer.parseInt(ordinal.replaceAll("\\D+", ""));
    }

    @ParameterType("\"([^\"]*)\"")
    public List<String> stringList(String raw) {
        return Stream.of(raw.split(","))
                .map(String::trim)
                .toList();
    }
}
