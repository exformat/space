package io.exformat.space.framework.openGL;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import io.exformat.space.framework.impl.GLGraphics;

public class Vertices3 {

    private final GLGraphics glGraphics;
    private final boolean hasColor;
    private final boolean hasTexCoords;
    private final int vertexSize;
    private final IntBuffer vertices;
    private final int[] tmpBuffer;
    private final ShortBuffer indices;

    public Vertices3(GLGraphics glGraphics, int maxVertices, int maxIndices,
                     boolean hasColor, boolean hasTexCoords) {

        this.glGraphics = glGraphics;
        this.hasColor = hasColor;
        this.hasTexCoords = hasTexCoords;
        this.vertexSize = (3 + (hasColor ? 4 : 0) + (hasTexCoords ? 2 : 0)) * 4;
        this.tmpBuffer = new int[maxVertices * vertexSize / 4];

        ByteBuffer buffer = ByteBuffer.allocateDirect(maxVertices *
                this.vertexSize);

        buffer.order(ByteOrder.nativeOrder());

        this.vertices = buffer.asIntBuffer();

        if (maxIndices > 0) {

            buffer = ByteBuffer.allocateDirect(maxIndices * Short.SIZE / 8);
            buffer.order(ByteOrder.nativeOrder());
            this.indices = buffer.asShortBuffer();
        } else {

            this.indices = null;
        }
    }

    public void setVertices(float[] vertices, int offset, int length) {

        this.vertices.clear();
        int len = offset + length;

        for (int i = offset, j = 0; i < len; i++, j++){
            tmpBuffer[j] = Float.floatToRawIntBits(vertices[i]);
        }

        this.vertices.put(tmpBuffer, 0, length);
        this.vertices.flip();
    }

    public void setIndices(short[] indices, int offset, int length) {

        this.indices.clear();
        this.indices.put(indices, offset, length);
        this.indices.flip();
    }

    public void bind() {

        GL10 gl = this.glGraphics.getGL();
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        this.vertices.position(0);
        gl.glVertexPointer(3, GL10.GL_FLOAT, this.vertexSize, this.vertices);

        if (this.hasColor) {

            gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
            this.vertices.position(3);
            gl.glColorPointer(4, GL10.GL_FLOAT, this.vertexSize, this.vertices);
        }
        if (this.hasTexCoords) {

            gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
            this.vertices.position(this.hasColor ? 7 : 3);
            gl.glTexCoordPointer(2, GL10.GL_FLOAT, this.vertexSize, this.vertices);
        }
    }

    public void draw(int primitiveType, int offset, int numVertices) {

        GL10 gl = this.glGraphics.getGL();

        if (this.indices != null) {

            this.indices.position(offset);
            gl.glDrawElements(primitiveType, numVertices,
                    GL10.GL_UNSIGNED_SHORT, this.indices);
        } else {

            gl.glDrawArrays(primitiveType, offset, numVertices);
        }
    }

    public void unbind() {

        GL10 gl = this.glGraphics.getGL();

        if (this.hasTexCoords)
            gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        if (this.hasColor)
            gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
    }
}
