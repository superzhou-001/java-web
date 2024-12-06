package indi.study.system.sqel;

import com.alibaba.fastjson.JSON;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DirectoryScanner {

    // 主方法，程序入口
    public static void main(String[] args) {
        // 定义需要扫描的根目录
        String rootDirectory = "/Users/zhouming/Documents/sunyur/initProject";
        
        // 调用扫描方法，传入根目录路径和初始层级（1表示第一级目录）
        scanDirectory(new File(rootDirectory), 1);
    }

    /**
     * 递归扫描目录的方法
     * @param dir 要扫描的目录
     * @param level 当前目录层级，用于判断是否是第一级目录
     */
    public static void scanDirectory(File dir, int level) {
        String targetString = "/{";
        List<String> pathList = new ArrayList<>();
        // 判断目录是否存在以及是否是目录
        if (dir.exists() && dir.isDirectory()) {
            // 如果是第一级目录，打印目录名称
            if (level == 2) {
                //System.out.println("Scanning directory: " + dir.getAbsolutePath());
            }

            // 获取目录下的所有文件和子目录
            File[] files = dir.listFiles();
            if (files != null) {
                // 遍历每个文件或子目录
                for (File file : files) {
                    if (file.isDirectory()) {
                        // 递归调用扫描子目录，层级增加1
                        scanDirectory(file, level + 1);
                    } else if (file.isFile()) {
                        if (!file.getName().contains(".java")) {
                            continue;
                        }
                        // 检查文件内容是否包含目标字符串
                        try {
                            // 读取文件所有行
                            List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
                            for (String line : lines) {
                                // 如果该行包含目标字符串，打印文件绝对路径
                                if (line.contains(targetString)) {
                                    System.out.println("Found in file: " + file.getAbsolutePath());
                                    break; // 如果找到匹配，跳出循环
                                }
                            }
                        } catch (IOException e) {
                            // 处理文件读取异常
                            System.err.println("Error reading file: " + file.getAbsolutePath());
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }
}
