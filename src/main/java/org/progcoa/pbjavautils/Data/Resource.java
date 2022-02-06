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

    /**
     * @param file 出力したいファイル
     */
    public static void loadFile(String file) throws IOException, URISyntaxException {
        File destDir = new File("options/" + file); // コピー先のディレクトリ

        loadFile(file, destDir);
    }

    /**
     * @param file 出力したいファイル
     * @param destDir 保存される場所
     */
    public static void loadFile(String file, File destDir) throws IOException, URISyntaxException {
        if (!destDir.exists()) {
            final File jarFile = new File(Resource.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            if (jarFile.isFile()) {
                // JARで実行する場合
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
                // IDEで実行する場合
                final File resource = new File(Resource.class.getClassLoader().getResource(file).toURI());
                FileUtils.copyFile(resource, destDir);
            }
        }
    }

    /**
     * @param file 出力したいディレクトリ
     */
    public static void loadDirectory(String file) throws IOException, URISyntaxException {
        File destDir = new File("options/configs/" + file); // コピー先のディレクトリ

        final File jarFile = new File(Resource.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        if (jarFile.isFile()) {
            // JARで実行する場合
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
            // IDEで実行する場合
            final File resource = new File(Resource.class.getClassLoader().getResource(file).toURI());
            FileUtils.copyDirectory(resource, destDir);
        }
    }
}
