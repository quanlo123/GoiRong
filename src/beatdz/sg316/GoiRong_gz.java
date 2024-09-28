package beatdz.sg316;

import com.beatdz.network.Writer;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class GoiRong_gz {

    public short a;
    public byte b;
    public short c;
    public short d;

    void write(Writer writer) {
        try {
            writer.writeShort(a);
            writer.writeByte(b);
            writer.writeShort(c);
            writer.writeShort(d);
        } catch (IOException ex) {
        }

    }
}
