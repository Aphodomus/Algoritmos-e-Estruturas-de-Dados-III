import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ParIDUserIDQuestion implements datastructures.RegistroArvoreBMais<ParIDUserIDQuestion> {
    // Attributes
    private int IDUser;
    private int IDQuestion;
    private short tamanho = 8;

    // Special methods
    public ParIDUserIDQuestion() {
        this(-1, -1);
    }

    public ParIDUserIDQuestion(int iduser, int idquestion) {
        try {
            this.IDUser = iduser;
            this.IDQuestion = idquestion;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getIDUser() {
        return this.IDUser;
    }

    public int getIDQuestion() {
        return this.IDQuestion;
    }

    // Functions and methods
    @Override
    public ParIDUserIDQuestion clone() {
        return new ParIDUserIDQuestion(this.IDUser, this.IDQuestion);
    }

    public short size() {
        return this.tamanho;
    }

    public int compareTo(ParIDUserIDQuestion par) {
        if (this.IDUser != par.IDUser) {
            return this.IDUser - par.IDUser;
        } else {
            // Só compara os valores de Num2, se o Num2 da busca for diferente de -1
            // Isso é necessário para que seja possível a busca de lista
            return this.IDQuestion == -1 ? 0 : this.IDQuestion - par.IDQuestion;
        }
    }

    public String toString() {
        return String.format("%3d", this.IDUser) + ";" + String.format("%-3d", this.IDQuestion);
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        dos.writeInt(this.IDUser);
        dos.writeInt(this.IDQuestion);

        return baos.toByteArray();
    }
    
    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);

        this.IDUser = dis.readInt();
        this.IDQuestion = dis.readInt();
    }
}
