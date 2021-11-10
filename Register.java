import java.io.IOException;

public interface Register {
    // Methods and functions
    public int getID();
    public void setID(int id);
    public byte[] toByteArray() throws IOException;
    public void fromByteArray (byte[] array) throws IOException;
}
