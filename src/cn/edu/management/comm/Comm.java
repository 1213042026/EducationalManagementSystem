package cn.edu.management.comm;

import java.io.File;

public class Comm {
	
	/**
     * 判断文件及目录是否存在，若不存在则创建文件及目录
     * @param filepath
     * @return
     * @throws Exception
     */
	
public static File checkExist(String filepath) throws Exception{
       File file=new File(filepath);
      
       if (file.exists()) {//判断文件目录的存在
           System.out.println("文件夹存在！");
           if(file.isDirectory()){//判断文件的存在性      
                 System.out.println("文件存在！");      
             }else{
              file.createNewFile();//创建文件
               System.out.println("文件不存在，创建文件成功！"   );      
             }
       }else {
           System.out.println("文件夹不存在！");
           File file2=new File(file.getParent());
           file2.mkdirs();
           System.out.println("创建文件夹成功！");
           if(file.isDirectory()){      
                 System.out.println("文件存在！");       
             }else{      
              file.createNewFile();//创建文件 
               System.out.println("文件不存在，创建文件成功！"   );      
             }
       }
       return file;
    }
}
