package beatdz.sg316;

import com.beatdz.network.Writer;
import java.io.IOException;

public final class GoiRong_fv {
   public short a;
   public short b;
   public short c;
   public byte d;

     public void write(com.beatdz.network.Writer writer) throws IOException {
        writer.writeShort(a);
        writer.writeShort(b);
        writer.writeShort(c);
        writer.writeByte(d);
    }
}
