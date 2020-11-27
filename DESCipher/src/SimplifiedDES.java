import java.io.DataInputStream;

// Главные операции
public class SimplifiedDES
{

    public static void main( String args[]) throws Exception
    {
        DataInputStream inp=new DataInputStream(System.in);
        System.out.println("Enter the 10 Bit Key :");
        int K = Integer.parseInt(inp.readLine(),2);
        SDES A = new SDES( K);
        System.out.println("Enter the 8 Bit message To be Encrypt  : ");
        int m = Integer.parseInt(inp.readLine(),2);
        System.out.print("\nKey K1: ");
        SDES.printData( A.K1, 8);
        System.out.print("\nKey K2: ");
        SDES.printData( A.K2, 8);
        m = A.encrypt( m);
        System.out.print("\nEncrypted Message: ");
        SDES.printData( m, 8);
        m = A.decrypt( m);
        System.out.print("\nDecrypted Message: ");
        SDES.printData( m, 8);

    }

}