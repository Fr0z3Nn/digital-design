package ru.project.unpacker;

import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("all")

public class UnpackService {

    StringBuilder builder = new StringBuilder();

    public static void main(String[] args) {
        UnpackService unpackService = new UnpackService();
        System.out.println(unpackService.unpack("привет2[миша]"));
    }

    public String unpack(String stroke) {
        if (stroke == null || stroke.isEmpty()) throw new InvalidInputException("Invalid stroke");

        String regexp = "(\\d+\\[[^\\[\\]]+\\])";
        builder.replace(0, builder.length(), stroke);
        Matcher strokeMatcher = Pattern.compile(regexp).matcher(stroke);

        Set<String> collectingGroup = Pattern.compile(regexp).matcher(stroke)
                .results()
                .map(MatchResult::group)
                .collect(Collectors.toSet());


        for (String symb : collectingGroup) {
            String numberRegexp = "([\\[\\]])";
            String[] split = symb.split(numberRegexp);
            String repeat = split[1].repeat(Integer.valueOf(split[0]));
            stroke = stroke.replace(symb, repeat);
        }

        if (strokeMatcher.find()) {
            stroke = unpack(stroke);
        }

        return builder.toString();
    }
}
