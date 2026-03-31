package ru.mrbedrockpy.util;


import lombok.Getter;
import ru.mrbedrockpy.Game;

import java.io.*;
import java.util.Arrays;

@Getter
public class Resource {

    private final byte[] data;
    private final String extension;

    public Resource(String path) {
        String fullPath = File.separator + path.replace("/", File.separator);
        try (InputStream is = Game.class.getResourceAsStream(fullPath)) {
            if (is == null) throw new RuntimeException("Resource not found: " + fullPath);
            this.data = is.readAllBytes();
            int dotIndex = fullPath.lastIndexOf(".");
            if (dotIndex == -1) throw new RuntimeException("Resource has no extension: " + fullPath);
            this.extension = fullPath.substring(dotIndex);
        } catch (IOException e) { throw new RuntimeException(e); }
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(data) + extension.hashCode();
    }
}
