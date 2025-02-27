package ArthurAndShawn;

public class AddressFactory  {
    public static Address createAddress (String country, String city, String street, int building){
        return new Address(country,city,street,building);
    }
}
