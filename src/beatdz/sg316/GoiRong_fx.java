package beatdz.sg316;

import java.io.IOException;

public final class GoiRong_fx {

    public int a;
    public GoiRong_fv[] b;

    public void write(com.beatdz.network.Writer writer) throws IOException {
        writer.writeByte(a);

        writer.writeByte(b.length);
        for (int i = 0; i < b.length; i++) {
            b[i].write(writer);
        }

    }
}
