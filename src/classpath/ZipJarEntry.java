package classpath;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ZipJarEntry extends Entry {
    String absPath;
    String zipName;

    public ZipJarEntry(String path) {
        File dir = new File(path);
        if (dir.exists()) {
            absPath = dir.getParentFile().getAbsolutePath();
            zipName = dir.getName();
            //去掉结尾的.zip或者.jar 千万别碰上其它情况,要不直接异常了;
            zipName = zipName.substring(0, zipName.length() - 4);
        }
    }

    public ZipJarEntry(String path, String zipName) {
        File dir = new File(path, zipName);
        if (dir.exists()) {
            absPath = dir.getAbsolutePath();
            //去掉结尾的.zip或者.jar 千万别碰上其它情况,要不直接异常了;
            this.zipName = zipName.substring(0, zipName.length() - 4);
        }
    }

    @Override
    byte[] readClass(String className) throws IOException {
        File file = new File(absPath);
        ZipInputStream zin = null;
        BufferedInputStream in = null;
        ByteArrayOutputStream out = null;
        ZipFile zf = new ZipFile(file);
        ZipEntry ze = zf.getEntry(className);
        if (ze == null) {
            return null;
        }
        in = new BufferedInputStream(zf.getInputStream(ze));
        out = new ByteArrayOutputStream(1024);
        int size = 0;
        byte[] temp = new byte[1024];
        while ((size = in.read(temp)) != -1) {
            out.write(temp, 0, size);
        }
        if (zin != null) {
            zin.closeEntry();
        }
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.close();
        }
        return out.toByteArray();
    }

    @Override
    String printClassName() {
        return absPath;
    }
}
