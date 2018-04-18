package io.exformat.space.framework.openGL;

public class TextureRegion {


    private final float u1, v1;
    private final float u2, v2;
    private final Texture texture;


    public TextureRegion(Texture texture,
                         float x,
                         float y,
                         float width,
                         float height) {

        this.u1 = x / texture.getTextureWidth();
        this.v1 = y / texture.getTextureHeight();
        this.u2 = this.u1 + width / texture.getTextureWidth();
        this.v2 = this.v1 + height / texture.getTextureHeight();
        this.texture = texture;
    }

    public float getU1() {
        return u1;
    }

    public float getV1() {
        return v1;
    }

    public float getU2() {
        return u2;
    }

    public float getV2() {
        return v2;
    }

    public Texture getTexture() {
        return texture;
    }
}