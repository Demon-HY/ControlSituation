package com.control.situation.utils.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件相关工具类
 *
 * Created by demon on 2017/7/1 0001.
 */
public class FileUtils {

    private FileUtils() { }

    /**
     * get file type
     * @param fileName file name
     * @return file type, error return null
     */
    public static String getFileType(String fileName) {
        if (fileName != null ) {
            int i = fileName.lastIndexOf('.');
            if (i > -1) {
                return fileName.substring(i+1).toLowerCase();
            }
        }

        return null;
    }

    /**
     * write byte stream to file, if file path not found, be recursion create file path
     * @param fileBytes 写入的字节流
     * @param filePath 文件路径
     * @param fileName 文件名
     * @return true/false
     */
    public static boolean uploadFile(byte[] fileBytes, String filePath, String fileName) {
        File file = new File(filePath, fileName);
        boolean isFileExists = file.exists();
        if (!isFileExists) {
            isFileExists = file.mkdirs();
        }
        // 文件路径创建失败
        if (!isFileExists) {
            return false;
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            out.write(fileBytes);
            out.flush();

            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert  out != null;
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    /**
     * 创建文件夹
     *
     * @param directory
     * @return File
     */
    public static File makeFolder(String directory) {
        try {
            File folder = new File(directory);
            if (!folder.exists()) {
                makeDirectory(folder);
            }
            return folder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建文件夹
     *
     * @param directory
     * @return File
     * @throws IOException
     */
    private static void makeDirectory(File directory) throws IOException {
        if (!directory.exists()) {
            if (!directory.isDirectory()) {
                throw new IOException("file exists and is not a directory.");
            }

        } else {
            if (directory.mkdirs()) {
                if (!directory.isDirectory()) {
                    throw new IOException("unable to create directory" + directory);
                }
            }
        }
    }

    /**
     * 创建文件
     *
     * @param directory
     * @return File
     */
    public static File makeFile(String directory) {
        try {
            File file = new File(directory);
            File parentFolder = file.getParentFile();
            if (!parentFolder.exists()) {
                makeDirectory(parentFolder);
            }
            return file;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除目录
     *
     * @param directory
     * @throws IOException
     */
    public static void delFolder(String directory) throws IOException {
        File folder = new File(directory);
        if (!folder.exists()) {
            return;
        }

        delFolder(folder);
    }

    public static void delFolder(File directory) throws IOException {
        if (!directory.exists()) {
            return;
        }

        if (!isSymlink(directory)) {

            File[] files = directory.listFiles();
            if (files == null) {
                throw new IOException("Failed to list contents of " + directory);
            }

            IOException exception = null;
            for (File file : files) {
                try {
                    deleteFile(file);
                } catch (IOException ioe) {
                    exception = ioe;
                }
            }

            if (null != exception) {
                throw exception;
            }
            delFolder(directory);
        }
        if (!directory.delete()) {
            throw new RuntimeException("don't delete the directory:" + directory);
        }
    }

    /**
     * 删除文件
     *
     * @param directory
     */
    public static void deleteFile(String directory) {
        try {
            File file = new File(directory);
            if (file.exists() && file.isFile()) {
                delFolder(directory);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteFile(File directory) throws IOException {
        if (directory.exists() && directory.isFile()) {
            delFolder(directory);
        }
    }

    /**
     * 是否自定义文件
     * @param file
     * @return
     * @throws IOException
     */
    private static boolean isSymlink(File file) throws IOException {
        if (file == null) {
            throw new NullPointerException("File must not be null");
        }
        if (File.separatorChar == '\\') {
            return false;
        }
        File fileInCanonicalDir;
        if (file.getParent() == null) {
            fileInCanonicalDir = file;
        } else {
            File canonicalDir = file.getParentFile().getCanonicalFile();
            fileInCanonicalDir = new File(canonicalDir, file.getName());
        }

        return !fileInCanonicalDir.getCanonicalFile().equals(fileInCanonicalDir.getAbsoluteFile());
    }

    public static void main(String[] args) {
        System.out.println(getFileType("a.pdf.pdf"));

        // write file
    }
}
