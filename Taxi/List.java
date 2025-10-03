import javax.swing.plaf.synth.SynthPasswordFieldUI;

public class List {
    int max_anzahl;
    int current_free_index = 0;
    Taxi[] list;

    public void List(int anzahl) {
        max_anzahl = anzahl;

        // define length of array
        list = new Taxi[max_anzahl];
    }

    public void hintenAnstellen(Taxi neuesTaxi) {
        // check if new tax can enter 
        if (max_anzahl == current_free_index) {
            System.err.println("Taxi Stellplatz ist voll, es muss zuerst ein Taxt raus fahren");
            return;
        }
        
        // there is defenetly space
        list[current_free_index] = neuesTaxi;
        
        // increase current free index
        current_free_index ++;
        return;
    }

    public void vorneHerausfahren() {
        int taxi_raus;

        if (list[0] != null) {
            taxi_raus = list[0].giveNumber();
        } else {
            System.err.println("Es gibt kein Taxi, was rausfahren kann");
            return;
        }

        for (int i = 0; i < current_free_index; i++) {
            list[i] = list[i++];
        }

        current_free_index --;
        
        System.out.println("Taxi" + taxi_raus + "ist rausgefahren");
    }

    public void warteschlangeAusgeben() {
        for (int i = 0; i < current_free_index; i++) {
            System.out.println("Taxi: " + list[i].giveNumber());
        }
    }
}   
