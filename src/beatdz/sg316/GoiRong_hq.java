package beatdz.sg316;

public final class GoiRong_hq {

    public String a;
    public GoiRong_hp[] b;

    public void write(com.beatdz.network.Writer writer) {
        try {
            writer.writeUTF(a);
            writer.writeByte(b.length);
            for(int i=0;i<b.length;i++)
            {
                b[i].write(writer);
            }
        } catch (Exception ex) {
            
        }
        
    }
}
