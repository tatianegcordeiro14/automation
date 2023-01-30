package Entities;

public class Booking {

    private String firstname;
    private String lastname;
    private float totalprice;
    private boolean depositpaid;
    private bookingdates bookingdates;
    private String additionalneeds;

    public Booking(String firstname, String lastname, float totalprice, boolean depositpaid, bookingdates bookingdates, String additionalneeds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
        this.additionalneeds = additionalneeds;
    }

    public float getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(float totalprice) {
        this.totalprice = totalprice;
    }

    public boolean getDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

    public bookingdates getbookingdates() {
        return bookingdates;
    }

    public void setbookingdates(bookingdates bookingdates) {
        this.bookingdates = bookingdates;
    }

    public String getLastname() {
        return lastname;

    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}
