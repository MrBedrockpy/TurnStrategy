package ru.mrbedrockpy.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class LoggerStream extends OutputStream {

    private final int maxLines;
    private final Deque<String> lines = new ArrayDeque<>();
    private final StringBuilder buffer = new StringBuilder();

    public LoggerStream(int maxLines) {
        this.maxLines = maxLines;
    }

    @Override
    public void write(int b) {
        if (b == '\n') {
            addLine(buffer.toString());
            buffer.setLength(0);
        } else {
            buffer.append((char) b);
        }
    }

    private void addLine(String line) {
        if (lines.size() == maxLines) {
            lines.removeFirst();
        }
        lines.addLast(line);
    }

    public List<String> getLastLines() {
        return new ArrayList<>(lines);
    }
}
