import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.*;

public class PomReader {

    public static void main(String[] args) {

        File pomFile = new File("pom.xml");

        MavenXpp3Reader reader = new MavenXpp3Reader();
        try {
            Model model = reader.read(new FileReader(pomFile));


            String moduleCoordinate = model.getGroupId() + ":" +model.getArtifactId() + ":pom:" + model.getVersion();

            try(FileWriter fw = new FileWriter("../coordinates.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw))
            {
                out.println(moduleCoordinate);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }


        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (
                XmlPullParserException e) {
            e.printStackTrace();
        }
    }
}
