package com.fpl.crypt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.ByteBuffer;

/**
 * copied from sun.misc.CharacterEncoder
 *
 */
public abstract class CharacterEncoder
{
  protected PrintStream pStream;

  protected abstract int bytesPerAtom();

  protected abstract int bytesPerLine();

  protected void encodeBufferPrefix(final OutputStream paramOutputStream)
    throws IOException
  {
    this.pStream = new PrintStream(paramOutputStream);
  }

  protected void encodeBufferSuffix(final OutputStream paramOutputStream)
    throws IOException
  {
  }

  protected void encodeLinePrefix(final OutputStream paramOutputStream, final int paramInt)
    throws IOException
  {
  }

  protected void encodeLineSuffix(final OutputStream paramOutputStream)
    throws IOException
  {
    this.pStream.println();
  }

  protected abstract void encodeAtom(OutputStream paramOutputStream, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException;

  protected int readFully(final InputStream paramInputStream, final byte[] paramArrayOfByte)
    throws IOException
  {
    for (int i = 0; i < paramArrayOfByte.length; ++i) {
      final int j = paramInputStream.read();
      if (j == -1) {
		return i;
	}
      paramArrayOfByte[i] = (byte)j;
    }
    return paramArrayOfByte.length;
  }

  public void encode(final InputStream paramInputStream, final OutputStream paramOutputStream)
    throws IOException
  {
    final byte[] arrayOfByte = new byte[bytesPerLine()];

    encodeBufferPrefix(paramOutputStream);
    while (true)
    {
      final int j = readFully(paramInputStream, arrayOfByte);
      if (j == 0) {
        break;
      }
      encodeLinePrefix(paramOutputStream, j);
      for (int i = 0; i < j; i += bytesPerAtom())
      {
        if ((i + bytesPerAtom()) <= j) {
			encodeAtom(paramOutputStream, arrayOfByte, i, bytesPerAtom());
		} else {
          encodeAtom(paramOutputStream, arrayOfByte, i, j - i);
        }
      }
      if (j < bytesPerLine()) {
        break;
      }
      encodeLineSuffix(paramOutputStream);
    }

    encodeBufferSuffix(paramOutputStream);
  }

  public void encode(final byte[] paramArrayOfByte, final OutputStream paramOutputStream)
    throws IOException
  {
    final ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
    encode(localByteArrayInputStream, paramOutputStream);
  }

  public String encode(final byte[] paramArrayOfByte)
  {
    final ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    final ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
    String str = null;
    try {
      encode(localByteArrayInputStream, localByteArrayOutputStream);

      str = localByteArrayOutputStream.toString("8859_1");
    }
    catch (final Exception localException) {
      throw new Error("CharacterEncoder.encode internal error");
    }
    return str;
  }

  private byte[] getBytes(final ByteBuffer paramByteBuffer)
  {
    byte[] localObject = null;

    if (paramByteBuffer.hasArray()) {
      final byte[] arrayOfByte = paramByteBuffer.array();
      if ((arrayOfByte.length == paramByteBuffer.capacity()) && (arrayOfByte.length == paramByteBuffer.remaining()))
      {
        localObject = arrayOfByte;
        paramByteBuffer.position(paramByteBuffer.limit());
      }
    }

    if (localObject == null)
    {
      localObject = new byte[paramByteBuffer.remaining()];

      paramByteBuffer.get(localObject);
    }

    return localObject;
  }

  public void encode(final ByteBuffer paramByteBuffer, final OutputStream paramOutputStream)
    throws IOException
  {
    final byte[] arrayOfByte = getBytes(paramByteBuffer);
    encode(arrayOfByte, paramOutputStream);
  }

  public String encode(final ByteBuffer paramByteBuffer)
  {
    final byte[] arrayOfByte = getBytes(paramByteBuffer);
    return encode(arrayOfByte);
  }

  public void encodeBuffer(final InputStream paramInputStream, final OutputStream paramOutputStream) throws IOException
  {
    final byte[] arrayOfByte = new byte[bytesPerLine()];

    encodeBufferPrefix(paramOutputStream);
    int j;
    do
    {
      j = readFully(paramInputStream, arrayOfByte);
      if (j == 0) {
        break;
      }
      encodeLinePrefix(paramOutputStream, j);
      for (int i = 0; i < j; i += bytesPerAtom()) {
        if ((i + bytesPerAtom()) <= j) {
			encodeAtom(paramOutputStream, arrayOfByte, i, bytesPerAtom());
		} else {
          encodeAtom(paramOutputStream, arrayOfByte, i, j - i);
        }
      }
      encodeLineSuffix(paramOutputStream); }
    while (j >= bytesPerLine());

    encodeBufferSuffix(paramOutputStream);
  }

  public void encodeBuffer(final byte[] paramArrayOfByte, final OutputStream paramOutputStream)
    throws IOException
  {
    final ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
    encodeBuffer(localByteArrayInputStream, paramOutputStream);
  }

  public String encodeBuffer(final byte[] paramArrayOfByte)
  {
    final ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    final ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
    try {
      encodeBuffer(localByteArrayInputStream, localByteArrayOutputStream);
    }
    catch (final Exception localException) {
      throw new Error("CharacterEncoder.encodeBuffer internal error");
    }
    return localByteArrayOutputStream.toString();
  }

  public void encodeBuffer(final ByteBuffer paramByteBuffer, final OutputStream paramOutputStream)
    throws IOException
  {
    final byte[] arrayOfByte = getBytes(paramByteBuffer);
    encodeBuffer(arrayOfByte, paramOutputStream);
  }

  public String encodeBuffer(final ByteBuffer paramByteBuffer)
  {
    final byte[] arrayOfByte = getBytes(paramByteBuffer);
    return encodeBuffer(arrayOfByte);
  }
}