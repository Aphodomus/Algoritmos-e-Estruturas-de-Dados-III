import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ParIDEndereco implements datastructures.RegistroHashExtensivel<ParIDEndereco> {

  private int id;
  private long endereco;
  private short TAMANHO = 12;

  public ParIDEndereco() {
    this(-1, -1);
  }

  public ParIDEndereco(int i, long e) {
    this.id = i;
    this.endereco = e;
  }

  public int getID() {
    return this.id;
  }

  public long getEndereco() {
    return this.endereco;
  }

  @Override
  public int hashCode() {
    return this.id;
  }

  public short size() {
    return this.TAMANHO;
  }

  public String toString() {
    return this.id + ";" + this.endereco;
  }

  public byte[] toByteArray() throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeInt(this.id);
    dos.writeLong(this.endereco);
    return baos.toByteArray();
  }

  public void fromByteArray(byte[] ba) throws IOException {
    ByteArrayInputStream bais = new ByteArrayInputStream(ba);
    DataInputStream dis = new DataInputStream(bais);
    this.id = dis.readInt();
    this.endereco = dis.readLong();
  }

}