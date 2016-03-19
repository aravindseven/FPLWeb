package com.fpl.crypt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.nio.ByteBuffer;

public abstract class CharacterDecoder
{
  protected abstract int bytesPerAtom();

  protected abstract int bytesPerLine();

  protected void decodeBufferPrefix(final PushbackInputStream paramPushbackInputStream, final OutputStream paramOutputStream)
    throws IOException
  {
  }

  protected void decodeBufferSuffix(final PushbackInputStream paramPushbackInputStream, final OutputStream paramOutputStream)
    throws IOException
  {
  }

  protected int decodeLinePrefix(final PushbackInputStream paramPushbackInputStream, final OutputStream paramOutputStream)
    throws IOException
  {
    return bytesPerLine();
  }

  protected void decodeLineSuffix(final PushbackInputStream paramPushbackInputStream, final OutputStream paramOutputStream)
    throws IOException
  {
  }

  protected void decodeAtom(final PushbackInputStream paramPushbackInputStream, final OutputStream paramOutputStream, final int paramInt)
    throws IOException
  {
    throw new CEStreamExhausted();
  }

  protected int readFully(final InputStream paramInputStream, final byte[] paramArrayOfByte, final int paramInt1, final int paramInt2)
    throws IOException
  {
    for (int i = 0; i < paramInt2; ++i) {
      final int j = paramInputStream.read();
      if (j == -1) {
        return ((i == 0) ? -1 : i);
      }
      paramArrayOfByte[(i + paramInt1)] = (byte)j;
    }
    return paramInt2;
  }

  public void decodeBuffer(final InputStream paramInputStream, final OutputStream paramOutputStream)
    throws IOException
  {
    @SuppressWarnings("unused")
    int j = 0;

    final PushbackInputStream localPushbackInputStream = new PushbackInputStream(paramInputStream);
    decodeBufferPrefix(localPushbackInputStream, paramOutputStream);
    while (true)
    {
      try
      {
        final int k = decodeLinePrefix(localPushbackInputStream, paramOutputStream);
        int i;
        for (i = 0; (i + bytesPerAtom()) < k; i += bytesPerAtom()) {
          decodeAtom(localPushbackInputStream, paramOutputStream, bytesPerAtom());
          j += bytesPerAtom();
        }
        if ((i + bytesPerAtom()) == k) {
          decodeAtom(localPushbackInputStream, paramOutputStream, bytesPerAtom());
          j += bytesPerAtom();
        } else {
          decodeAtom(localPushbackInputStream, paramOutputStream, k - i);
          j += k - i;
        }
        decodeLineSuffix(localPushbackInputStream, paramOutputStream);
      } catch (final CEStreamExhausted localCEStreamExhausted) {
        break;
      }
    }
    decodeBufferSuffix(localPushbackInputStream, paramOutputStream);
  }

  @SuppressWarnings("deprecation")
  public byte[] decodeBuffer(final String paramString)
    throws IOException
  {
    final byte[] arrayOfByte = new byte[paramString.length()];

    paramString.getBytes(0, paramString.length(), arrayOfByte, 0);
    final ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(arrayOfByte);
    final ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    decodeBuffer(localByteArrayInputStream, localByteArrayOutputStream);
    return localByteArrayOutputStream.toByteArray();
  }

  public byte[] decodeBuffer(final InputStream paramInputStream)
    throws IOException
  {
    final ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    decodeBuffer(paramInputStream, localByteArrayOutputStream);
    return localByteArrayOutputStream.toByteArray();
  }

  public ByteBuffer decodeBufferToByteBuffer(final String paramString)
    throws IOException
  {
    return ByteBuffer.wrap(decodeBuffer(paramString));
  }

  public ByteBuffer decodeBufferToByteBuffer(final InputStream paramInputStream)
    throws IOException
  {
    return ByteBuffer.wrap(decodeBuffer(paramInputStream));
  }
}
