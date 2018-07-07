import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class MoveFile {
    public static void main(String[] args) {
        File currentPath = new File("/Users/taolei/codes/universe/jvm/src/main/java/");
        //找到dir
        //找到所有子dir
        //找到子dir中所有文件
        //判断文件名是否为java
        //如果是java文件，则创建同名文件夹
        //其中文件夹名称中 '清单'替换为'ch';'-'替换为code ; '.java'替换为空字符串
        File[] subFiles = currentPath.listFiles();
        for (File file : subFiles) {
            File[] chFiles = findChFileInDir(file);
            if (chFiles == null) {
                continue;
            }
            for (File chFile : chFiles) {
                String dirPath = getDirPathByFile(chFile);

                if (dirPath == null || dirPath.isEmpty()) {
                    continue;
                }
                File dir = new File(dirPath);
                if (!dir.exists()) {
                    dir.mkdir();
                    System.out.println("new Dir : " + dirPath);
                }
                copyFile(chFile.getAbsolutePath(), dirPath + "/" + chFile.getName());

            }
        }
    }

    private static File[] findChFileInDir(File chDirectory) {
        File[] chFiles = null;
        if (chDirectory.isDirectory()) {
            chFiles = chDirectory.listFiles();

        }
        return chFiles;
    }

    private static String getDirPathByFile(File chFile) {
        String fileName = chFile.getName();
        String path = chFile.getParent();
        if (fileName.endsWith(".java")) {
            return path + fileName.replace("清单", "/eg").
                    replace("-", "code").
                    replace(".java", "");
        }
        return null;
    }

    private static void copyFile(String oldPath, String newPath) {
        try {
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) {
                InputStream inStream = new FileInputStream(oldPath);
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ((byteread = inStream.read(buffer)) != -1) {
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();

        }
        System.out.println("file moved:" + oldPath + " --to-->" + newPath);

    }
}
