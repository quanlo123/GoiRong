package beatdz.sg316;

import com.beatdz.network.Writer;

public final class GoiRong_hp {

    public short a;
    public String b;
    public String c;
    public short d;
    public short e;
    public String f = "";
    public String g = "";
    public boolean h;

    public final GoiRong_hp a() {
        GoiRong_hp var1;
        (var1 = new GoiRong_hp()).a = this.a;
        var1.b = this.b;
        var1.c = this.c;
        var1.d = this.d;
        var1.e = this.e;
        var1.f = this.f;
        var1.g = this.g;
        return var1;
    }

    public final String b() {
        String var1 = this.b.trim();
        if (this.f.length() > 0) {
            var1 = var1 + " (" + this.f + ")";
        }

        return var1;
    }

    // $FF: synthetic method
    public final Object clone() {
        return this.a();
    }

    public void write(com.beatdz.network.Writer writer) {
        try {
            writer.writeShort(a);
            writer.writeUTF(b);
            writer.writeUTF(c);
            writer.writeShort(d);
            writer.writeShort(e);

        } catch (Exception ex) {

        }

    }

}
