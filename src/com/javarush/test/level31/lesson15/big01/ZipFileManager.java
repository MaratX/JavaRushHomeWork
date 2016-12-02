package com.javarush.test.level31.lesson15.big01;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by HMF on 29.11.2016.
 */
public class ZipFileManager
{
    private Path zipFile;

    public ZipFileManager(Path zipFile)
    {
        this.zipFile = zipFile;
    }

    public void createZip(Path source) throws Exception
    {
        try (ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(zipFile)))
        {
            try (InputStream is = Files.newInputStream(source))
            {
                ZipEntry e = new ZipEntry(source.getFileName().toString());
                zos.putNextEntry(e);
                byte[] buf = new byte[1024];
                int size;
                while ((size = is.read(buf)) != -1)
                {
                    zos.write(buf, 0, size);
                }
                zos.closeEntry();
            }
        }
    }

}
