import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ParEmailID implements datastructures.RegistroHashExtensivel <ParEmailID> {
    // Attributes
    private String email;
    private int id;
    private short tamanho = 44;

    // Special methods
    public ParEmailID() throws Exception {
        this("", -1);
    }

    public ParEmailID(String email, int id) throws Exception {
        try {
            this.email = email;
            this.id = id;

            if (email.length() + 4 > tamanho) {
                throw new Exception("Quantidade de caracteres do email maior que a permitida. Dados serao perdidos.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getEmail() {
        return this.email;
    }
    
      public int getID() {
        return this.id;
    }

    // Functions and methods
    @Override
    public int hashCode() {
        return this.email.hashCode();
    }

    public short size() {
        return this.tamanho;
    }

    public String toString() {
        return this.email + "; " + this.id;
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        dos.writeUTF(this.email);
        dos.writeInt(this.id);

        byte[] bs = baos.toByteArray();
        byte[] bs2 = new byte[this.tamanho];

        for (int i = 0; i < this.tamanho; i++) {
            bs2[i] = ' ';
        }

        for (int i = 0; i < bs.length && i < this.tamanho; i++) {
            bs2[i] = bs[i];
        }

        return bs2;
    }

    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
        this.email = dis.readUTF();
        this.id = dis.readInt();
    }
}