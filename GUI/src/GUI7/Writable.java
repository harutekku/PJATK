package GUI7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface Writable {
    void write(DataOutputStream dos) throws IOException;
    void read(DataInputStream dis) throws IOException;
}
