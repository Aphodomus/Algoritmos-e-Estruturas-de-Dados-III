import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Question implements Register {
    // Attributes
    protected int idQuestion;
    protected int idUser;
    protected long creation;
    protected short grade;
    protected String question;
    protected String keyWords;
    protected boolean active;

    // Special methods
    public Question() {
        this.idQuestion = -1;
        this.idUser = -1;
        this.creation = -1;
        this.grade = 0;
        this.question = "";
        this.keyWords = "";
        this.active = true;
    }

    public Question(int idUser, long creation, short grade, String question, String keywords) {
        this.idUser = idUser;
        this.creation = creation;
        this.grade = grade;
        this.question = question;
        this.keyWords = keywords;
        this.active = true;
    }

    public int getID() {
        return this.idQuestion;
    }

    public void setID(int idquestion) {
        this.idQuestion = idquestion;
    }

    public int getIDUser() {
        return this.idUser;
    }

    public void setIDUSer(int iduser) {
        this.idUser = iduser;
    }

    public long getCreation() {
        return this.creation;
    }

    public void setCreation(long creation) {
        this.creation = creation;
    }

    public short getGrade() {
        return this.grade;
    }

    public void setGrade(short grade) {
        this.grade = grade;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getKeyWords() {
        return this.keyWords;
    }

    public void setKeyWords(String keywords) {
        this.keyWords = keywords;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // Functions and methods
    public String toString() {
        return  "\nidQuestion: " + this.idQuestion + 
                "\nidUser: " + this.idUser +
                "\ncreation: " + this.creation +
                "\ngrade: " + this.grade +
                "\nquestion: " + this.question +
                "\nkeyWords: " + this.keyWords +
                "\nactive: " + this.active;
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        dos.writeInt(this.idQuestion);
        dos.writeInt(this.idUser);
        dos.writeLong(this.creation);
        dos.writeShort(this.grade);
        dos.writeUTF(this.question);
        dos.writeUTF(this.keyWords);
        dos.writeBoolean(this.active);

        return baos.toByteArray();
    }

    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);

        this.idQuestion = dis.readInt();
        this.idUser = dis.readInt();
        this.creation = dis.readLong();
        this.grade = dis.readShort();
        this.question = dis.readUTF();
        this.keyWords = dis.readUTF();
        this.active = dis.readBoolean();
    }

}
