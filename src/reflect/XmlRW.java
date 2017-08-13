package reflect;

import com.sun.xml.internal.stream.StaxXMLInputSource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by sickle on 17-8-12.
 */
public class XmlRW {

    public void SaxReader1() throws DocumentException {
        //创建SAXReader读取器，专门用于读取xml
        SAXReader saxReader=new SAXReader();
        //根据saxReader的read重写方法可知，既可以通过inputStream输入流来读取，也可以通过file对象来读取
        Document document=saxReader.read(new File("xxx.xml"));

        Element rootelement=document.getRootElement();

        for(Iterator i = rootelement.elementIterator(); i.hasNext();){
                Element element=(Element) i.next();

                System.out.println("name: "+element.getName()+"  " +element.getText());
        }
        for(Iterator i=rootelement.elementIterator("name");i.hasNext();){
                Element element=(Element) i.next();
                element.setText("zhaoshuai");
                //设置中间的text
            System.out.println(element.getText());
        }

        try {
            XMLWriter xmlWriter=new XMLWriter(new FileWriter(new File("xxx.xml")));
            xmlWriter.write(document);
            xmlWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Document createDocument() {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Student");
        Element author1 =
                root
                        .addElement("author")
                        .addAttribute("name", "James")
                        .addAttribute("location", "UK")
                        .addText("James Strachan");
        Element author2 =
                root
                        .addElement("author")
                        .addAttribute("name", "Bob")
                        .addAttribute("location", "US")
                        .addText("Bob McWhirter");
        return document;
    }
    public void write(Document document) throws IOException {
        // 指定文件
//        XMLWriter writer = new XMLWriter(
//                new FileWriter( "zzz.xml" )
//        );
//        writer.write( document );
//        writer.close();
        // 美化格式
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer = new XMLWriter( new FileWriter("zzz.xml"), format );
        writer.write( document );
        writer.close();
        // 缩减格式
//        format = OutputFormat.createCompactFormat();
//        writer = new XMLWriter( System.out, format );
//        writer.write( document );
    }

    public static void main(String[] args) throws DocumentException, IOException {
        XmlRW xmlRW=new XmlRW();
        xmlRW.SaxReader1();
        xmlRW.write(xmlRW.createDocument());

    }
}
