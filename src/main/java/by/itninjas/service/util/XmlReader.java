package by.itninjas.service.util;

import static lombok.AccessLevel.PRIVATE;

import by.itninjas.domain.xml.RowCollection;
import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public final class XmlReader {

    public static <T> T parse(String xml, Class<T> castClass) {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(RowCollection.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xml);

            return castClass.cast(unmarshaller.unmarshal(reader));

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

}
