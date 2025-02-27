package ArthurAndShawn;

public class Address {
    private String country;
    private String city;
    private String street;
    private int building;

    public Address(String country, String city, String street, int building) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.building = building;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getBuilding() {
        return building;
    }

    public void setBuilding(int building) {
        this.building = building;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
