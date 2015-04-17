package javatests;

import org.docx4j.Docx4J;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.contenttype.ContentType;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.PartName;
import org.docx4j.openpackaging.parts.WordprocessingML.AlternativeFormatInputPart;
import org.docx4j.relationships.Relationship;
import org.docx4j.wml.CTAltChunk;

import java.io.*;

/**
 * @Author TaoLei
 * @Date 2015/4/15.
 */
public class FileUtils {

    public static void main(String[] args) {
        String PATH = "E:/download/";
        String FILE_NAME = "test.html";
        String winNew = PATH + "win-new.docx";
        String htmlFullPath = PATH + FILE_NAME;
        String time = System.currentTimeMillis() + "";

//        String codeNewDoc = convertHtmlToDOC(htmlFullPath, PATH + time + ".doc");
        String codeNewDocx = convertHtmlToDOCX(htmlFullPath, PATH + time + ".docx");

//        convertHtmlToDOCXFromTemplate(htmlFullPath, PATH + time + ".docx", winNew);
//        convertDocxToPDF("E:/download/1429235487314.docx", PATH + time + ".pdf");

//        String newWin = convertDocxToPDF(winNewDocx, PATH + time + "_newWin.pdf");
    }

    /**
     * @param htmlFileName
     * @return html文件中String类型的文本内容
     */

    public static String getHtmlFileContent(String htmlFileName) {
        File file = new File(htmlFileName);
        String content = "";
        try {
            if (!file.exists()) {
                System.out.println("html源文件不存在");
                return content;
            }
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = null;
            while ((line = br.readLine()) != null) {
                content += line;
            }

            fr.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * @param htmlPath    html路径
     * @param newFileName 生成的doc文件路径
     * @return
     * @方法说明 生成doc文档，如果返回null，则表示生成失败
     */
    public static String convertHtmlToDOC(String htmlPath, String newFileName) {
        String content = getHtmlFileContent(htmlPath);//这个getHtmlFileContent是我自己写的读取html文件中文本内容的一个方法
        OutputStreamWriter os = null;
        FileOutputStream fos = null;
        try {
            //创建文件
            File file = new File(newFileName);
            if (!file.exists()) {
                file.createNewFile();
                System.out.println(newFileName);
            }
            fos = new FileOutputStream(newFileName);
            os = new OutputStreamWriter(fos, "UTF-8");//设置文档编码
            os.append(content.toString());
            os.flush();
            return newFileName;
        } catch (Exception e) {
            return null;
        } finally {
            try {
                os.close();
                fos.close();
            } catch (Exception e) {
                return null;
            }
        }
    }

    /**
     * @param htmlPath html路径
     * @param newFile  生成的docx文件路径
     * @return
     * @方法说明 根据html生成docx文档
     */
    public static String convertHtmlToDOCX(String htmlPath, String newFile) {
        String content = getHtmlFileContent(htmlPath);

        WordprocessingMLPackage wordMLPackage = null;
        try {
            wordMLPackage = WordprocessingMLPackage.createPackage();

            AlternativeFormatInputPart afiPart = new AlternativeFormatInputPart(new PartName("/temp.html"));
            afiPart.setBinaryData(content.getBytes());
            afiPart.setContentType(new ContentType("text/html"));
            Relationship altChunkRel = wordMLPackage.getMainDocumentPart().addTargetPart(afiPart);

            CTAltChunk ac = Context.getWmlObjectFactory().createCTAltChunk();
            ac.setId(altChunkRel.getId());
            wordMLPackage.getMainDocumentPart().addObject(ac);

            wordMLPackage.getContentTypeManager().addDefaultContentType("html", "text/html");
            wordMLPackage.save(new java.io.File(newFile));
            System.out.println(newFile);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (Docx4JException e) {
            e.printStackTrace();
        }
        return newFile;
    }

    /**
     * @param htmlPath     html路径
     * @param newFile      生成的docx文件路径
     * @param docxTemplate docx模板路径，即通过windows中microsoft创建的docx，且编辑过的空白文档
     * @return
     * @方法说明 根据html生成docx文档
     */
    public static String convertHtmlToDOCXFromTemplate(String htmlPath, String newFile, String docxTemplate) {
        String content = getHtmlFileContent(htmlPath);

        WordprocessingMLPackage wordMLPackage = null;
        try {
            wordMLPackage = WordprocessingMLPackage.load(new File(docxTemplate));

            AlternativeFormatInputPart afiPart = new AlternativeFormatInputPart(new PartName("/temp.html"));
            afiPart.setBinaryData(content.getBytes());
            afiPart.setContentType(new ContentType("text/html"));
            Relationship altChunkRel = wordMLPackage.getMainDocumentPart().addTargetPart(afiPart);

            CTAltChunk ac = Context.getWmlObjectFactory().createCTAltChunk();
            ac.setId(altChunkRel.getId());
            wordMLPackage.getMainDocumentPart().addObject(ac);

            wordMLPackage.getContentTypeManager().addDefaultContentType("html", "text/html");
            wordMLPackage.save(new java.io.File(newFile));
            System.out.println(newFile);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (Docx4JException e) {
            e.printStackTrace();
        }
        return newFile;
    }

    /**
     * @param docxPath docx文件路径
     * @param newFile  生成的PDF文件路径
     * @return
     */
    public static String convertDocxToPDF(String docxPath, String newFile) {

        try {
            File newPdf = new File(newFile);
            if (!newPdf.exists()) {
                newPdf.createNewFile();

                WordprocessingMLPackage wordprocessingMLPackage = WordprocessingMLPackage.load(new File(docxPath));

                Docx4J.toPDF(wordprocessingMLPackage, new FileOutputStream(newPdf));
                System.out.println(newFile);
            }
        } catch (Docx4JException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
