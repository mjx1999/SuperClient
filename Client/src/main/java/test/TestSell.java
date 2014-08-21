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
                            +" \"Operate\":\"Save\", "
                            +" \"BillName\":\"s_sale\", "
                            +" \"IsAddnew\":true, "
                            +" \"MasterData\": "
                            +" { "
                            +" \"Amount\":123123.123, "
                            +" \"BillCode\":\"SS-01-20140820-222805\", "
                            +" \"BillDate\":\"2014-08-20\", "
                            +" \"BillID\":-2,  "
                            +" \"BillTo\":\"上海松江区胡角公路600号\", "
                            +" \"ContactPhone\":\"021-57899208\", "
                            +" \"OpID\":0, "
                            +" \"ShopID\":0, "
                            +" \"TraderID\":54 "
                            +" }, "
                            +" \"Detail1Data\": "
                            +" [ "
                            +" {\"APrice\":123.123, "
                            +" \"Amount\":3444444.44, "
                            +" \"BillID\":-2, "
                            +" \"Disc\":1231.123, "
                            +" \"GoodsAmt\":1.2344444444444123E13, "
                            +" \"GoodsID\":3, "
                            +" \"IsLargess\":0, "
                            +" \"ItemNO\":-1, "
                            +" \"OrigPrice\":23.0, "
                            +" \"OrigTaxPrice\":234.234, "
                            +" \"Quantity\":23.2, "
                            +" \"StoreID\":3, "
                            +" \"TaxAmt\":22.222, "
                            +" \"TaxPrice\":2.3, "
                            +" \"TaxRate\":2.33, "
                            +" \"UnitID\":119, "
                            +" \"UnitPrice\":222.22222, "
                            +" \"UnitQuantity\":333333.333, "
                            +" \"UnitRate\":13.2}] "
                            +" } " ;

//                    asResult = client.DoBill("s_sale",asParams);
                        String as = client.getAccList();
                    System.out.println(as);

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
