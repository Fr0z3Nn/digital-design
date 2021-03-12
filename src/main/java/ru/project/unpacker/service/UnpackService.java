package ru.project.unpacker.service;

import ru.project.unpacker.exception.InvalidInputException;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UnpackService {

    public static void main(String[] args) {
        UnpackService unpackService = new UnpackService();
        System.out.println(unpackService.unpack("12[421]]"));
    }

    public String unpack(String stroke) {
        if (stroke == null || stroke.isEmpty()) {
            throw new InvalidInputException("Invalid stroke");
        }

        String regexp = "(\\d+\\[[^\\[\\]]+\\])";
        for (String symbol : Pattern.compile(regexp).matcher(stroke)
                .results()
                .map(MatchResult::group)
                .collect(Collectors.toSet())) {
            String[] splitToRepeat = symbol.split("([\\[\\]])");
            String repeat = splitToRepeat[1].repeat(Integer.parseInt(splitToRepeat[0]));
            stroke = stroke.replace(symbol, repeat);
            if (Pattern.compile(regexp).matcher(stroke).find()) {
                stroke = unpack(stroke);
            }else {
                return stroke;
            }
        }

        System.out.println(stroke);
        if(Pattern.compile("[\\[\\]]+").matcher(stroke).find()){
            throw new InvalidInputException("Invalid stroke");
        }

        return stroke;
    }
}
