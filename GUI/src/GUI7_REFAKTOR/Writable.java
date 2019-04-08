package GUI7_REFAKTOR;

import java.io.DataOutputStream;
import java.io.IOException;

public interface Writable {
    void write(DataOutputStream dos) throws IOException;
}
