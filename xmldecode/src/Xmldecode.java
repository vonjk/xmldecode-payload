import java.io.*;
import java.beans.XMLEncoder;

public class Xmldecode {
    public static void main(String[] args) throws Exception {
        File file = new File(args[0]);

        //读取ysoserial文件生成的payload

        FileInputStream fileInputStream = new FileInputStream(file);

        //初始化比特数组输出流
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((int) file.length());

        int buf_size=1024;
        byte[] buffer=new byte[buf_size];
        int len=0;
        //读取文件中的内容转到byte数组输出流
        while(-1 != (len=fileInputStream.read(buffer,0,buf_size))){
            byteArrayOutputStream.write(buffer,0,len);
        }

        BufferedOutputStream oop = new BufferedOutputStream(new FileOutputStream(new File(args[1])));

        //使用jdk的xmlencoder把byte数组写入
        XMLEncoder xmlEncoder = new XMLEncoder(oop);
        xmlEncoder.flush();
        xmlEncoder.writeObject(byteArrayOutputStream.toByteArray());
        xmlEncoder.close();
        byteArrayOutputStream.close();
        fileInputStream.close();
    }
}
