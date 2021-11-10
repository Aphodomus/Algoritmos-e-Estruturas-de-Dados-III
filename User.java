import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class User implements Register {
    // Attributes
    protected int idUser;
    protected String name;
    protected String email;
    protected int password;
    protected String question;
    protected String answer;

    // Special methods
    public User(String name, String email, String password, String question, String answer) {
        this.name = name;
        this.email = email;
        this.password = password.hashCode();
        this.question = question;
        this.answer = answer;
    }

    public User() {
        this.idUser = -1;
        this.name = "";
        this.email = "";
        this.password = -1;
        this.question = "";
        this.answer = "";
    }

    public int getID() {
        return this.idUser;
    }

    public void setID(int id) {
        this.idUser = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password.hashCode();
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    // Functions and methods
    public String toString() {
        return  "\nidUser: " + this.idUser + 
                "\nname: " + this.name +
                "\nemail: " + this.email +
                "\npassword: " + this.password +
                "\nquestion: " + this.question +
                "\nanswer: " + this.answer;
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        dos.writeInt(this.idUser);
        dos.writeUTF(this.name);
        dos.writeUTF(this.email);
        dos.writeInt(this.password);
        dos.writeUTF(this.question);
        dos.writeUTF(this.answer);

        return baos.toByteArray();
    }

    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);

        this.idUser = dis.readInt();
        this.name = dis.readUTF();
        this.email = dis.readUTF();
        this.password = dis.readInt();
        this.question = dis.readUTF();
        this.answer = dis.readUTF();
    }
}