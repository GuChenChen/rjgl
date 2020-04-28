package com.fykj.scaffold.security.business.service.impl;

import com.fykj.scaffold.support.oss.OssCons;
import com.fykj.scaffold.support.oss.OssSaveUtil;
import fr.opensagres.poi.xwpf.converter.core.FileImageExtractor;
import fr.opensagres.poi.xwpf.converter.core.FileURIResolver;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLConverter;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author yangx
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class WordToHtmlServiceImpl implements EnvironmentAware {

    @Autowired
    private Environment environment;

    private String prefixOfPath;

    /**
     * 将文件放入目标文件夹
     * @param file
     * @return
     * @throws IOException
     */
    public String writeFileToTargetPath(MultipartFile file) throws IOException {
        setEnvironment(environment);
        String targetFileName = file.getOriginalFilename();
        String targetPath = prefixOfPath+ Objects.requireNonNull(targetFileName).substring(0,targetFileName.indexOf('.'))+File.separator;
        File targetFile = new File(targetPath);
        if(!targetFile.exists()){
            boolean result = targetFile.mkdirs();
            if(!result){
                throw new IOException("文件未成功创建");
            }
        }
        File newFile = new File(targetPath,targetFileName);
        file.transferTo(newFile);
        return targetPath;
    }

    /**
     * word文件转html入口方法
     * @param filePath
     * @param file
     * @return
     * @throws Exception
     */
    public String getWordToTransfer(String filePath, MultipartFile file) throws Exception {
        String name;
        String html = null;
        Thread.sleep(500);
        name = Objects.requireNonNull(file.getOriginalFilename()).substring(0, file.getOriginalFilename().lastIndexOf("."));
        if(file.getOriginalFilename().endsWith(".doc")||file.getOriginalFilename().endsWith(".DOC")){
            html=docToHtml(filePath,file.getOriginalFilename(),name+".html");
        }
        if(file.getOriginalFilename().endsWith(".docx")||file.getOriginalFilename().endsWith(".DOCX")){
            html=docxToHtml(filePath,file.getOriginalFilename(),name+".html");
        }
        return html;
    }

    /**
     * doc文件转html
     * @param filePath
     * @param fileName
     * @param htmlName
     * @return
     * @throws Exception
     */
    public String docToHtml(String filePath , String fileName, String htmlName) throws Exception{
        FileInputStream fileInputStream = new FileInputStream(filePath+fileName);
        HWPFDocument wordDocument = new HWPFDocument(fileInputStream);
        fileInputStream.close();
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
        wordToHtmlConverter.setPicturesManager(new PicturesManager() {
            @Override
            public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches, float heightInches) {
                return "test/" + suggestedName;
            }
        });
        wordToHtmlConverter.processDocument(wordDocument);
        List<Picture> pics = wordDocument.getPicturesTable().getAllPictures();
        if (pics != null) {
            for (int i = 0; i < pics.size(); i++) {
                Picture pic = pics.get(i);
                String imagesPath = filePath + "word" +File.separator + "media";
                File imageFile = new File(imagesPath);
                if(!imageFile.exists()){
                    boolean result = imageFile.mkdirs();
                    if(!result){
                        throw new IOException("文件未成功创建");
                    }
                }
                FileOutputStream fileOutputStream = new FileOutputStream(imageFile + File.separator + pic.suggestFullFileName());
                pic.writeImageContent(fileOutputStream);
                fileOutputStream.close();
            }
        }
        Document htmlDocument = wordToHtmlConverter.getDocument();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(out);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer serializer = transformerFactory.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource,streamResult);
        String result = new String(out.toByteArray());
        try{
            out.close();
        }catch (Exception e){
            throw new IOException(e.getMessage());
        }
        FileUtils.writeStringToFile(new File(filePath + htmlName), result, "utf-8");
        return sharedMethod(new File(filePath),result);
    }

    /**
     * docx文件转html
     * @param filePath
     * @param fileName
     * @param htmlName
     * @return
     * @throws IOException
     */
    public String docxToHtml(String filePath , String fileName , String htmlName) throws IOException {
        final String file = filePath + fileName;
        File f = new File(file);
        //加载word文档生成XWPFDocument对象
        InputStream is = new FileInputStream(f);
        XWPFDocument document = new XWPFDocument(is);
        is.close();
        //解析XHTML配置
        File imageFolderFile = new File(filePath);
        XHTMLOptions options = XHTMLOptions.create().URIResolver(new FileURIResolver(imageFolderFile));
        options.setExtractor(new FileImageExtractor(imageFolderFile));
        options.setIgnoreStylesIfUnused(false);
        options.setFragment(true);
        ByteArrayOutputStream htmlStream = new ByteArrayOutputStream();
        XHTMLConverter.getInstance().convert(document, htmlStream, options);
        String result = htmlStream.toString();
        try{
            htmlStream.close();
        }catch (Exception e){
            throw new IOException(e.getMessage());
        }
        FileUtils.writeStringToFile(new File(filePath + htmlName), result, "utf-8");
        return sharedMethod(imageFolderFile, result);
    }

    /**
     * docx和doc文件共享后续处理冗余文件方法
     * @param imageFolderFile
     * @param result
     * @return
     * @throws IOException
     */
    public String sharedMethod(File imageFolderFile,String result) throws IOException{
        File fileToImage=null;
        for(int i = 0; i< Objects.requireNonNull(imageFolderFile.listFiles()).length; i++){
            if(Objects.requireNonNull(imageFolderFile.listFiles())[i].listFiles()!=null){
                fileToImage = Objects.requireNonNull(imageFolderFile.listFiles())[i];
                break;
            }
        }
        File imageFile;
        if(fileToImage!=null){
            imageFile = Objects.requireNonNull(fileToImage.listFiles())[0];
            File[] imageFileList = imageFile.listFiles();
            String newContent=imagePathChange(result, Objects.requireNonNull(imageFileList));
//            for(File ifl:imageFileList){
//                deleteFile(ifl);
//            }
//            deleteFile(imageFile);
//            deleteFile(fileToImage);
//            File[] cc1Files = imageFolderFile.listFiles();
//            for(File if2: Objects.requireNonNull(cc1Files)){
//                deleteFile(if2);
//            }
//            deleteFile(imageFolderFile);
            return newContent;
        } else {
            return null;
        }
    }

    /**
     * 删除文件
     * @param file
     * @throws IOException
     */
    public void deleteFile(File file) throws IOException {
        if(!file.delete()){
            throw new IOException("文件未删除成功");
        }
    }

    /**
     * 图片上传到服务器，并修改html中的图片路径
     * @param content
     * @param imageList
     * @return
     * @throws IOException
     */
    public String imagePathChange(String content, File[] imageList) throws IOException {
        List<String> list = new ArrayList<>();
        for(File f:imageList){
            FileInputStream fis = new FileInputStream(f);
            String pass = OssSaveUtil.save(fis, OssCons.OSS_LOCAL_IMPL_BEAN,f.getName().substring(f.getName().lastIndexOf('.')+1));
            list.add(pass);
            fis.close();
        }
        return convert(content,list);
    }

    /**
     * 修改html中的图片路径
     * @param content
     * @param list
     * @return
     */
    public String convert(String content, List<String> list){
        org.jsoup.nodes.Document doc = Jsoup.parse(content);
        Elements imgs = doc.getElementsByTag("img");
        int i=0;
        for(org.jsoup.nodes.Element img : imgs){
            img.attr("src",list.get(i));
            i++;
        }
        return doc.toString();
    }

    /**
     * 获取资源文件值
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        prefixOfPath = this.environment.getProperty("html.location");
    }
}
