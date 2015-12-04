package trainBooking.model.entity;


public class Price {
    private int id;
    private String routeID;
    private int suitePrice;
    private int coupePrice;
    private int berthPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRouteID() {
        return routeID;
    }

    public void setRouteID(String routeID) {
        this.routeID = routeID;
    }

    public int getSuitePrice() {
        return suitePrice;
    }

    public void setSuitePrice(int suitePrice) {
        this.suitePrice = suitePrice;
    }

    public int getCoupePrice() {
        return coupePrice;
    }

    public void setCoupePrice(int coupePrice) {
        this.coupePrice = coupePrice;
    }

    public int getBerthPrice() {
        return berthPrice;
    }

    public void setBerthPrice(int berthPrice) {
        this.berthPrice = berthPrice;
    }
}
