package cn.edu.management.comm;

import java.io.File;

public class Comm {
	
	/**
     * �ж��ļ���Ŀ¼�Ƿ���ڣ����������򴴽��ļ���Ŀ¼
     * @param filepath
     * @return
     * @throws Exception
     */
	
public static File checkExist(String filepath) throws Exception{
       File file=new File(filepath);
      
       if (file.exists()) {//�ж��ļ�Ŀ¼�Ĵ���
           System.out.println("�ļ��д��ڣ�");
           if(file.isDirectory()){//�ж��ļ��Ĵ�����      
                 System.out.println("�ļ����ڣ�");      
             }else{
              file.createNewFile();//�����ļ�
               System.out.println("�ļ������ڣ������ļ��ɹ���"   );      
             }
       }else {
           System.out.println("�ļ��в����ڣ�");
           File file2=new File(file.getParent());
           file2.mkdirs();
           System.out.println("�����ļ��гɹ���");
           if(file.isDirectory()){      
                 System.out.println("�ļ����ڣ�");       
             }else{      
              file.createNewFile();//�����ļ� 
               System.out.println("�ļ������ڣ������ļ��ɹ���"   );      
             }
       }
       return file;
    }
}
