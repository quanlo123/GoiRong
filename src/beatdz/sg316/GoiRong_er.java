package beatdz.sg316;

import com.beatdz.network.Writer;

public final class GoiRong_er {

    public short a;
    public short b;
    public short c;
    public short d;
    public boolean e;

    public void write(com.beatdz.network.Writer writer) {
        try {
            writer.writeShort(a);
            writer.writeShort(b);
            writer.writeShort(c);
            writer.writeShort(d);
            writer.writeBoolean(e);

        } catch (Exception ex) {

        }

    }
}
