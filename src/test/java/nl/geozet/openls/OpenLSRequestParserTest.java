package nl.geozet.openls;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import nl.geozet.openls.databinding.openls.Address;
import nl.geozet.openls.databinding.openls.Building;
import nl.geozet.openls.databinding.openls.GeocodeRequest;
import nl.geozet.openls.databinding.openls.OpenLSConstants;
import nl.geozet.openls.databinding.openls.Place;
import nl.geozet.openls.databinding.openls.PostalCode;
import nl.geozet.openls.databinding.openls.Street;
import nl.geozet.openls.databinding.openls.StreetAddress;
import nl.geozet.openls.parser.OpenLSRequestParser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * The Class OpenLSRequestParserTest.
 */
public class OpenLSRequestParserTest {

    /**
     * Test open ls request parser. Iterate through the sample openls request
     * files and try to extract a GeocodeRequest from them.
     * 
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Test
    public void testOpenLSRequestParser() throws java.io.IOException {
        OpenLSRequestParser rp = new OpenLSRequestParser();
        File folder = new File("./target/test-classes/samplerequests/");
        List<File> fileList = new ArrayList<>();
        listDirectoryFilenames(folder, fileList);
        java.util.Iterator<File> fileIt = fileList.iterator();
        while (fileIt.hasNext()) {
            String fileName = "/samplerequests/" + fileIt.next().getName();
            String requestString = readFileAsString(fileName);
            GeocodeRequest gcrq = rp.parseOpenLSRequest(requestString);
            if (gcrq != null) {
                System.out.println(gcrq.toXML());
            }
            assertNotNull(gcrq);
        }
    }

    /**
     * Test open ls request roundtrip. 
     * <ol>
     * <li>create an openls request
     * <li>serialize it to xml string
     * <li>use the request parser to deserialize the xml to a new openls request
     * object
     * <li>serialize the new openls request object to xml string
     * <li>check if the first xml string is the same as the second xml
     * string
     * </ol>
      *
     */
    @Test
    public void testOpenLSRequestRoundtrip() {
        GeocodeRequest gcr = new GeocodeRequest();

        Address address = new Address();

        StreetAddress sa = new StreetAddress();

        Building building = new Building();
        building.setNumber("100");

        Place p = new Place();
        p.setType(OpenLSConstants.PLACE_TYPE_MUNICIPALITY);
        p.setPlace("Utrecht");

        PostalCode pc = new PostalCode();
        pc.setPostalCode("1234HH");

        Street street = new Street();
        street.setStreet("Kosterijland 78");

        sa.setStreet(street);
        sa.setBuilding(building);
        address.setPostalCode(pc);
        address.setCountryCode("NL");
        address.addPlace(p);
        address.setStreetAddress(sa);

        gcr.addAddress(address);

        // create xml from response object
        String gcrXML = gcr.toXML();

        OpenLSRequestParser rp = new OpenLSRequestParser();
        GeocodeRequest newgcr = rp.parseOpenLSRequest(gcrXML);
        String newgcrXML = newgcr.toXML();

        System.out.println(gcrXML);
        System.out.println(newgcrXML);

        assertEquals(gcrXML, newgcrXML);
    }

    /**
     * Read file as string.
     * 
     * @param filePath
     *            the file path
     * @return the string
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    private String readFileAsString(String filePath) throws java.io.IOException {
        URL url = this.getClass().getResource(filePath);
        File file = new File(url.getFile());
        byte[] buffer = new byte[(int) file.length()];
        BufferedInputStream f = new BufferedInputStream(new FileInputStream(
                file));
        f.read(buffer);
        return new String(buffer);
    }

    /**
     * List directory filenames.
     * 
     * @param folder
     *            the folder
     * @param list
     *            the list
     */
    private void listDirectoryFilenames(File folder, List<File> list) {
        File[] files = folder.listFiles();
        assertNotNull(files);
        for (File file : files) {
            if (file.getName().endsWith(".xml")) {
                list.add(file);
            }
            if (file.isDirectory()) {
                listDirectoryFilenames(file, list);
            }
        }
    }
}
