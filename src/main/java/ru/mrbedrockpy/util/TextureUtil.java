package ru.mrbedrockpy.util;

import com.raylib.Image;
import com.raylib.Raylib;
import com.raylib.Texture;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import static com.raylib.Raylib.*;

public class TextureUtil {

    private static final Map<Resource, Texture> textures = new HashMap<>();

    public static Texture load(Resource resource) {
        if (textures.containsKey(resource)) return textures.get(resource);
        ByteBuffer buffer = ByteBuffer.allocateDirect(resource.getData().length);
        buffer.put(resource.getData());
        buffer.flip();
        Image image = loadImageFromMemory(resource.getExtension(), buffer, buffer.capacity());
        if (image.data() == null) throw new RuntimeException("Failed to load image from memory");
        Texture texture = loadTextureFromImage(image);
        unloadImage(image);
        textures.put(resource, texture);
        return texture;
    }

    public static void unload() {
        textures.values().forEach(Raylib::unloadTexture);
    }

}
