package org.progcoa.pbjavautils.Data;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@SuppressWarnings("all")
public class Resource {

    public Class aClass;

    public void setaClass(Class target){
        aClass = target;
    }

    public Resource(Class target){
        aClass = target;
    }

    /**
     * @param file 出力したいファイル
     */
    public void loadFile(String file) throws IOException, URISyntaxException {
        File destDir = new File("options/" + file);

        loadFile(file, destDir);
    }

    /**
     * @param file 出力したいディレクトリ
     */
    public void loadDirectory(String file) throws IOException, URISyntaxException {
        File destDir = new File("options/configs/" + file);

        loadDirectory(file, destDir);
    }

    /**
     * @param file 出力したいファイル
     * @param destDir 保存される場所
     */
    public void loadFile(String file, File destDir) throws IOException, URISyntaxException {
        if (!destDir.exists()) {
            final File jarFile = new File(aClass.getProtectionDomain().getCodeSource().getLocation().getPath());
            if (jarFile.isFile()) {
                final JarFile jar = new JarFile(jarFile);
                for (Enumeration<JarEntry> entries = jar.entries(); entries.hasMoreElements(); ) {
                    JarEntry entry = entries.nextElement();
                    if (entry.getName().equals(file) && !entry.isDirectory()) {
                        File parent = destDir.getParentFile();
                        if (parent != null) {
                            parent.mkdirs();
                        }
                        try (FileOutputStream out = new FileOutputStream(destDir); InputStream in = jar.getInputStream(entry)) {
                            byte[] buffer = new byte[8 * 1024];
                            int s;
                            while ((s = in.read(buffer)) > 0) {
                                out.write(buffer, 0, s);
                            }
                        }
                    }
                }
                jar.close();
            } else {
                final File resource = new File(aClass.getClassLoader().getResource(file).toURI());
                FileUtils.copyFile(resource, destDir);
            }
        }
    }

    /**
     * @param file 出力したいディレクトリ
     * @param destDir 保存される場所
     */
    public void loadDirectory(String file, File destDir) throws IOException, URISyntaxException {
        final File jarFile = new File(aClass.getProtectionDomain().getCodeSource().getLocation().getPath());
        if (jarFile.isFile()) {
            final JarFile jar = new JarFile(jarFile);
            for (Enumeration<JarEntry> entries = jar.entries(); entries.hasMoreElements(); ) {
                JarEntry entry = entries.nextElement();
                if (entry.getName().startsWith(file + "/") && !entry.isDirectory()) {
                    File dest = new File(destDir, entry.getName().substring(file.length() + 1));
                    File parent = dest.getParentFile();
                    if (parent != null) {
                        parent.mkdirs();
                    }
                    try (FileOutputStream out = new FileOutputStream(dest); InputStream in = jar.getInputStream(entry)) {
                        byte[] buffer = new byte[8 * 1024];
                        int s;
                        while ((s = in.read(buffer)) > 0) {
                            out.write(buffer, 0, s);
                        }
                    }
                }
            }
            jar.close();
        } else {
            final File resource = new File(aClass.getClassLoader().getResource(file).toURI());
            FileUtils.copyDirectory(resource, destDir);
        }
    }
}
