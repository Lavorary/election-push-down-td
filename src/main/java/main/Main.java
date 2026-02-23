package main;


public class Main {
    public static void main(String[] args) {
        DBConnection dbc = new DBConnection();
        DataRetriever dr = new DataRetriever();
        System.out.println(dr.countAllVotes());

}
}
