package GUI7_REFAKTOR_2.GUI7_REFAKTOR;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface Writable {
    void write(DataOutputStream dos) throws IOException;
    static Figures read(DataInputStream dis) throws IOException{
        System.err.println("Where is my body");
        return null;
    };
}
