package io.exformat.space.framework.openGL;


import javax.microedition.khronos.opengles.GL10;

import io.exformat.space.framework.impl.GLGraphics;

public class SpriteBatcher {

    private final float[] verticesBuffer;
    private int bufferIndex;

    private final Vertices vertices;
    private int numSprites;

    public SpriteBatcher(GLGraphics glGraphics, int maxSprites) {

        this.verticesBuffer = new float[maxSprites*4*4];
        this.vertices = new Vertices(glGraphics, maxSprites*4, maxSprites*6,
                false, true);
        this.bufferIndex = 0;
        this.numSprites = 0;

        short[] indices = new short[maxSprites*6];
        int len = indices.length;
        short j = 0;

        for (int i = 0; i < len; i += 6, j += 4) {
            indices[i + 0] = (short)(j + 0);
            indices[i + 1] = (short)(j + 1);
            indices[i + 2] = (short)(j + 2);
            indices[i + 3] = (short)(j + 2);
            indices[i + 4] = (short)(j + 3);
            indices[i + 5] = (short)(j + 0);
        }

        vertices.setIndices(indices, 0, indices.length);
    }

    public void beginBatch(Texture texture) {

        texture.bind();
        this.numSprites = 0;
        this.bufferIndex = 0;
    }

    public void endBatch() {

        this.vertices.setVertices(verticesBuffer, 0, bufferIndex);
        this.vertices.bind();
        this.vertices.draw(GL10.GL_TRIANGLES, 0, numSprites * 6);
        this.vertices.unbind();
    }

    public void drawSprite(float x, float y, float width, float height,
                           TextureRegion region) {
        float halfWidth = width / 2;
        float halfHeight = height / 2;
        float x1 = x - halfWidth;
        float y1 = y - halfHeight;
        float x2 = x + halfWidth;
        float y2 = y + halfHeight;

        this.verticesBuffer[this.bufferIndex++] = x1;
        this.verticesBuffer[this.bufferIndex++] = y1;
        this.verticesBuffer[this.bufferIndex++] = region.getU1();
        this.verticesBuffer[this.bufferIndex++] = region.getV2();

        this.verticesBuffer[this.bufferIndex++] = x2;
        this.verticesBuffer[this.bufferIndex++] = y1;
        this.verticesBuffer[this.bufferIndex++] = region.getU2();
        this.verticesBuffer[this.bufferIndex++] = region.getV2();

        this.verticesBuffer[this.bufferIndex++] = x2;
        this.verticesBuffer[this.bufferIndex++] = y2;
        this.verticesBuffer[this.bufferIndex++] = region.getU2();
        this.verticesBuffer[this.bufferIndex++] = region.getV1();

        this.verticesBuffer[this.bufferIndex++] = x1;
        this.verticesBuffer[this.bufferIndex++] = y2;
        this.verticesBuffer[this.bufferIndex++] = region.getU1();
        this.verticesBuffer[this.bufferIndex++] = region.getV1();

        this.numSprites++;
    }

    public void drawSprite(float x, float y, float width, float height,
                           float angle, TextureRegion region) {

        float halfWidth = width / 2;
        float halfHeight = height / 2;
        float rad = (float) Math.toRadians(angle);
        float cos = (float) Math.cos(rad);
        float sin = (float) Math.sin(rad);

        float x1 = -halfWidth * cos - (-halfHeight) * sin;
    }

    //============================================================================
    public float[] getVerticesBuffer() {
        return verticesBuffer;
    }

    public int getBufferIndex() {
        return bufferIndex;
    }

    public void setBufferIndex(int bufferIndex) {
        this.bufferIndex = bufferIndex;
    }

    public Vertices getVertices() {
        return vertices;
    }

    public int getNumSprites() {
        return numSprites;
    }

    public void setNumSprites(int numSprites) {
        this.numSprites = numSprites;
    }
}
