import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ParIDUserIDAnswer implements datastructures.RegistroArvoreBMais<ParIDUserIDAnswer> {
    // Attributes
    private int IDUser;
    private int IDAnswer;
    private short tamanho = 8;

    // Special methods
    public ParIDUserIDAnswer() {
        this(-1, -1);
    }

    public ParIDUserIDAnswer(int idUser, int IDAnswer) {
        try {
            this.IDAnswer = IDAnswer;
            this.IDUser = idUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getIDAnswer() {
        return this.IDAnswer;
    }

    public int getIDUser() {
        return this.IDUser;
    }

    // Functions and methods
    @Override
    public ParIDUserIDAnswer clone() {
        return new ParIDUserIDAnswer(this.IDUser, this.IDAnswer);
    }

    public short size() {
        return this.tamanho;
    }

    public int compareTo(ParIDUserIDAnswer par) {
        if (this.IDAnswer != par.IDAnswer) {
            return this.IDAnswer - par.IDAnswer;
        } else {
            // Só compara os valores de Num2, se o Num2 da busca for diferente de -1
            // Isso é necessário para que seja possível a busca de lista
            return this.IDUser == -1 ? 0 : this.IDUser - par.IDUser;
        }
    }

    public String toString() {
        return String.format("%3d", this.IDUser) + ";" + String.format("%-3d", this.IDAnswer);
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        dos.writeInt(this.IDUser);
        dos.writeInt(this.IDAnswer);

        return baos.toByteArray();
    }
    
    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);

        this.IDUser = dis.readInt();
        this.IDAnswer = dis.readInt();
    }
}