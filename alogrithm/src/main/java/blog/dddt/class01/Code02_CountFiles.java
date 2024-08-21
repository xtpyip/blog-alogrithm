package blog.dddt.class01;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName: Code02_CountFiles
 * @version: 1.0
 * @Author: pyipXt
 * @Description:
 * 给定一个文件目录的路径，
 * 写一个函数统计这个目录下所有的文件数量并返回隐藏文件也算，但是文件夹不算
 **/
public class Code02_CountFiles {
    // 注意这个函数也会统计隐藏文件
    public static int getFileNumber(String folderPath) {
        return process(folderPath);
    }
    public static int process(String folderPath){
        int ans = 0;
        Queue<String> folders = new LinkedList<>();
        folders.add(folderPath);
        while (!folders.isEmpty()){
            File file = new File(folders.poll());
            if(file.isFile()){
                ans++;
            }else{
                for (File listFile : file.listFiles()) {
                    folders.add(listFile.getAbsolutePath());
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        // 你可以自己更改目录
        String path = "E:\\BaiduNetdiskDownload\\备份";
        System.out.println(getFileNumber(path));
    }
}
