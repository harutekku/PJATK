/**
 * @author Pawłowicz Jakub S18688
 */

package zad1;


import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class Tools {
    public static Options createOptionsFromYaml(String fileName) throws Exception {
        Yaml Yaml = new Yaml();
        Map<String, Object> map = Yaml.load(new InputStreamReader(new FileInputStream(fileName)));
        Options options = new Options((String) map.get("host"),
                (int) map.get("port"),
                (boolean) map.get("concurMode"),
                (boolean) map.get("showSendRes"),
                (Map<String, List<String>>) map.get("clientsMap"));
        return options;
    }
}
