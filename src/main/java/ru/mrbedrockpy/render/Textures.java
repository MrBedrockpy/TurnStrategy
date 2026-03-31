package ru.mrbedrockpy.render;

import com.raylib.Texture;
import ru.mrbedrockpy.util.Resource;
import ru.mrbedrockpy.util.TextureUtil;

public class Textures {

    public static final Texture PLAINS_TILE = TextureUtil.load(new Resource("textures/tiles/plains.png"));
    public static final Texture WATER_TILE = TextureUtil.load(new Resource("textures/tiles/water.png"));
    public static final Texture ROCKS_TILE = TextureUtil.load(new Resource("textures/tiles/rocks.png"));

    public static final Texture LEFT_BOTTOM_CASTLE_BORDER = TextureUtil.load(new Resource("textures/tiles/left_bottom_border.png"));
    public static final Texture LEFT_TOP_CASTLE_BORDER = TextureUtil.load(new Resource("textures/tiles/left_top_border.png"));
    public static final Texture RIGHT_BOTTOM_CASTLE_BORDER = TextureUtil.load(new Resource("textures/tiles/right_bottom_border.png"));
    public static final Texture RIGHT_TOP_CASTLE_BORDER = TextureUtil.load(new Resource("textures/tiles/right_top_border.png"));

    public static final Texture CASTLE_HUD = TextureUtil.load(new Resource("textures/ui/hud.png"));

    public static final Texture CASTLE_ENTITY = TextureUtil.load(new Resource("textures/entities/castle.png"));
    public static final Texture FOREST_ENTITY = TextureUtil.load(new Resource("textures/entities/forest.png"));
    public static final Texture RIOT_ENTITY = TextureUtil.load(new Resource("textures/entities/riot.png"));

}
