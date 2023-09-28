package com.pla.Utils;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompoundWordUtil {
    public static void main (String[] args) throws Exception {
        File newFile = new File("C:/Users/ryf/Documents/trademark/TMReferencemark/testfile/Paticulars.docx");
        List<File> srcfiles = new ArrayList<>();
        File file1 = new File("C:/Users/ryf/Documents/trademark/TMReferencemark/testfile/test1.docx");
        File file2 = new File("C:/Users/ryf/Documents/trademark/TMReferencemark/testfile/test2.docx");
        srcfiles.add(file1);
        srcfiles.add(file2);
        try {
            OutputStream dest = new FileOutputStream(newFile);
            ArrayList<XWPFDocument> documentList = new ArrayList<XWPFDocument>();
            XWPFDocument document = null;
            for (int i = 0;i < srcfiles.size(); i++) {
                FileInputStream inputStream = new FileInputStream(srcfiles.get(i).getPath());
                OPCPackage open = OPCPackage.open(inputStream);
                XWPFDocument doc = new XWPFDocument(open);
                documentList.add(doc);
            }

            for (int i = 0; i < documentList.size(); i++) {
                document = documentList.get(0);
                if (i == 0) {
                    documentList.get(i).createParagraph().createRun().addBreak(BreakType.PAGE);
                } else if (i == documentList.size() - 1) {
                    appendBody(document, documentList.get(i));
                } else {
                    documentList.get(i).createParagraph().createRun().addBreak(BreakType.PAGE);
                    appendBody(document, documentList.get(i));
                }
            }

            document.write(dest);
            System.out.println("合成成功");
            Runtime.getRuntime().exec("cmd /c start winword C:/Users/ryf/Documents/trademark/TMReferencemark/testfile/Paticulars.docx");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void appendBody(XWPFDocument src, XWPFDocument append) throws Exception {
        CTBody src1Body = src.getDocument().getBody();
        CTBody src2Body = append.getDocument().getBody();
        List<XWPFPictureData> allPictures = append.getAllPictures();
        Map<String, String> map = new HashMap<String, String>();
        for (XWPFPictureData picture : allPictures) {
            String before = append.getRelationId(picture);
            String after = src.addPictureData(picture.getData(), Document.PICTURE_TYPE_PNG);
            map.put(before, after);
        }
        appendBody(src1Body, src2Body, map);
    }

    private static void appendBody(CTBody src, CTBody append, Map<String, String> map) throws Exception {
        XmlOptions optionsOuter = new XmlOptions();
        optionsOuter.setSaveOuter();
        String appendString = append.xmlText(optionsOuter);
        String srcString = src.xmlText();
        String prefix = srcString.substring(0, srcString.indexOf(">") + 1);
        String mainPart = srcString.substring(srcString.indexOf(">") + 1, srcString.lastIndexOf("<"));
        String sufix = srcString.substring(srcString.lastIndexOf("<"));
        String addPart = appendString.substring(appendString.indexOf(">") + 1, appendString.lastIndexOf("<"));
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<String, String> set : map.entrySet()) {
                addPart = addPart.replace(set.getKey(), set.getValue());
            }
        }

        CTBody makeBody = CTBody.Factory.parse(prefix + mainPart + sufix);
        src.set(makeBody);
    }
}
