import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ParIDUserIDVote implements datastructures.RegistroArvoreBMais<ParIDUserIDVote> {
    // Attributes
    private int IDUser;
    private int IDVote;
    private short tamanho = 8;

    // Special methods
    public ParIDUserIDVote() {
        this(-1, -1);
    }

    public ParIDUserIDVote(int iduser, int IDVote) {
        try {
            this.IDUser = iduser;
            this.IDVote = IDVote;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getIDUser() {
        return this.IDUser;
    }

    public int getIDVote() {
        return this.IDVote;
    }

    // Functions and methods
    @Override
    public ParIDUserIDVote clone() {
        return new ParIDUserIDVote(this.IDUser, this.IDVote);
    }

    public short size() {
        return this.tamanho;
    }

    public int compareTo(ParIDUserIDVote par) {
        if (this.IDUser != par.IDUser) {
            return this.IDUser - par.IDUser;
        } else {
            // Só compara os valores de Num2, se o Num2 da busca for diferente de -1
            // Isso é necessário para que seja possível a busca de lista
            return this.IDVote == -1 ? 0 : this.IDVote - par.IDVote;
        }
    }

    public String toString() {
        return String.format("%3d", this.IDUser) + ";" + String.format("%-3d", this.IDVote);
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        dos.writeInt(this.IDUser);
        dos.writeInt(this.IDVote);

        return baos.toByteArray();
    }
    
    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);

        this.IDUser = dis.readInt();
        this.IDVote = dis.readInt();
    }
}
