package test;

/**
 * Created by twisty on 14-8-20.
 */
public class TestSell {
    public static void main(String[] args) {
        A3Client client = new A3Client();
        String asResult = "";
        String asParams = "";
        try
        {
            try
            {
                if (client.connectServer())
                {
                    asResult = client.Login("Sample",1,"","01");

                    System.out.println(asResult);
                    asParams = "{"
                            + "\"BillName\":\"s_sorder\","
                            + "\"Operate\":\"GetPriorBill\","
                            + "\"BillCode\":\"SO-2014-01-00007\""
                            + "}" ;
                    asResult = client.DoBill("s_sorder",asParams);
//                        String as = client.getAccList();
                    System.out.println(asResult);

                }
            }
            catch(Exception E)
            {
                asResult = "{\"ErrNo\":-1,\"ErrMessage\":\"~~~~~~~~~~!\"}";
            }
        }
        finally
        {
            System.out.println(asResult);
            client.close();
        }
    }
}
