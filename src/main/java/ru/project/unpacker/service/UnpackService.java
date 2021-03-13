package ru.project.unpacker.service;

import ru.project.unpacker.dto.UnpackPair;
import ru.project.unpacker.exception.InvalidInputException;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UnpackService {
    public static void main(String[] args) {
        UnpackService unpackService = new UnpackService();
        unpackService.unpack("1[c]5[v]");
    }

    volatile String stroke1;

    public String unpack(String stroke) {
        if (stroke == null || stroke.trim().isEmpty()) {
            throw new InvalidInputException("Invalid stroke");
        }
        stroke1 = stroke;
        Pattern.compile("(\\d+)\\[([^\\[\\]]+)\\]").matcher(stroke)
                .results()
                .map(matchResult -> {
                    return new UnpackPair(Integer.parseInt(matchResult.group(1)), matchResult.group(2));
                })
                .collect(Collectors.toSet());


        String regexp = "(\\d+\\[[^\\[\\]]+\\])";
        if (Pattern.compile(regexp).matcher(stroke).find()) {
            for (UnpackPair pair : Pattern.compile("(\\d+)\\[([^\\[\\]]+)\\]").matcher(stroke)
                    .results()
                    .map(matchResult -> new UnpackPair(Integer.parseInt(matchResult.group(1)), matchResult.group(2)))
                    .collect(Collectors.toSet())) {
                String repeat = pair.getStrokeToRepeat().repeat(pair.getAmountOfRepeat());
                stroke = stroke.replace(pair.toString(), repeat);
            }
            stroke = unpack(stroke);
        } else if (Pattern.compile("[\\[\\]]+").matcher(stroke).find()) {
            throw new InvalidInputException("Invalid stroke");
        }
        return stroke;
    }
}
