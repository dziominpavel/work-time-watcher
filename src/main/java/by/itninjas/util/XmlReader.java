package by.itninjas.util;

import by.itninjas.domain.xml.RowCollection;
import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class XmlReader {

    private static final String STRING_REPLACING1 = " xmlns=\"urn:schemas-microsoft-com:xml-analysis:rowset\"";
    private static final String STRING_REPLACING2 = "&lt;span style=&quot;color:#000000&quot;&gt;";
    private static final String STRING_REPLACING3 = "&lt;/span&gt;";
    private static final String STRING_REPLACING4 = " &amp;#9658";
    private static final String STRING_REPLACING5 = "&amp;#9668 ";

    private static final String STRING_REPLACEMENT = "";

    public RowCollection parse(MultipartFile file) {

        try {
            byte[] bytes = file.getBytes();
            String s1 = new String(bytes).replace(STRING_REPLACING1, STRING_REPLACEMENT);
            String s2 = s1.replace(STRING_REPLACING2, STRING_REPLACEMENT);
            String s3 = s2.replace(STRING_REPLACING3, STRING_REPLACEMENT);
            String s4 = s3.replace(STRING_REPLACING4, STRING_REPLACEMENT);
            String correctXml = s4.replace(STRING_REPLACING5, STRING_REPLACEMENT);

            JAXBContext jaxbContext = JAXBContext.newInstance(RowCollection.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(correctXml);

            return (RowCollection) unmarshaller.unmarshal(reader);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
