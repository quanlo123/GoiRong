package beatdz.sg316;

import com.beatdz.network.Writer;

public final class GoiRong_hw {

    public short a;
    public short b;
    public short c;
    public short d;
    public short e;
    public short f;
    public short g;

    public void write(com.beatdz.network.Writer writer) {
        try {
            writer.writeByte(a);
            writer.writeShort(b);
            writer.writeShort(c);
            writer.writeShort(d);
            writer.writeShort(e);
            writer.writeShort(f);
            writer.writeShort(g);

        } catch (Exception ex) {

        }

    }
}
