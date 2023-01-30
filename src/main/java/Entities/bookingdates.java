package Entities;

public class bookingdates {

    private String checkin;
    private String checkout;

    public bookingdates(String checkin, String checkout){
        this.checkin = checkin;
        this.checkout = checkout;
    }


    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

}
