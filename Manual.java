import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Manual {
    private static final String[] GENERIC_STOP_NAMES = { "Alto São sebastião", "Palmeiramas", "Santo Antonio",
            "Passo Fino", "Aertoporto", "Cavalaria", "Coqueirais", "Sete Carneiros", "Fundo do mar", "11 de setembro",
            "1 de janeiro", "Natal", "Mangueiras", "Palmeiramas Norte", "aaa talll da paraaadaaa", "Parada 1",
            "Quaquauqa", "Rarara", "Surf", "Tamarandare" };
    private static final String[] GENERIC_PEOPLE_NAMES = { "Alice", "Roberto", "Carlos", "Davi", "Amanda", "Francisco",
            "Graciane", "Geovana", "Isaque", "José", "Camila", "Lucia", "Maria", "Fernanda", "Otavio", "Percival",
            "Alberto", "Renato", "Sebastião", "Tomas" };
    private static final int MIN_TRAIN_COUNT = 1;
    private static final int MAX_TRAIN_COUNT = 2;
    private static final int MIN_PASSENGER_COUNT = 5;
    private static final int MAX_PASSENGER_COUNT = 15;
    private static final int MIN_TURNS = 10;
    private static final int MAX_TURNS = 35;
    private static final int FEE = 3;

    private static final Random random = new Random();
    private static final Scanner scanner = new Scanner(System.in);

    public static class Vehicle {
        private final int id;
        private String currentPosition;
        private boolean direction;
        private int capacity;
        private int currentLoad;

        public Vehicle(int id, String initialPosition, boolean initialDirection, int capacity) {
            this.id = id;
            this.currentPosition = initialPosition;
            this.direction = initialDirection;
            this.capacity = capacity;
            this.currentLoad = 0;
        }

        public int getId() {
            return id;
        }

        public String getCurrentPosition() {
            return currentPosition;
        }

        public void move(Route route) {
            String nextPosition = route.getNextStop(currentPosition, direction);
            if (nextPosition.equals(route.getStops().get(0))
                    || nextPosition.equals(route.getStops().get(route.getRouteLength() - 1))) {
                direction = !direction;
            }
            currentPosition = nextPosition;
        }

        public boolean isFull() {
            return currentLoad >= capacity;
        }

        public int getCurrentLoad() {
            return currentLoad;
        }

        public void setCurrentLoad(int currentLoad) {
            this.currentLoad = currentLoad;
        }
    }

    public static class Passenger {
        private final String name;
        private final String origin;
        private final String destination;
        private int credits;
        private String status;
        private List<String> route;

        public Passenger(String name, String origin, String destination, int credits) {
            this.name = name;
            this.origin = origin;
            this.destination = destination;
            this.credits = credits;
            this.status = "outside";
            this.route = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public String getOrigin() {
            return origin;
        }

        public String getDestination() {
            return destination;
        }

        public int getCredits() {
            return credits;
        }

        public void setCredits(int credits) {
            this.credits = credits;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<String> getRoute() {
            return route;
        }
    }

    public static class Route {
        private final List<String> stops;
        private final int routeLength;

        public Route(List<String> stops) {
            this.stops = stops;
            this.routeLength = stops.size();
        }

        public List<String> getStops() {
            return stops;
        }

        public int getRouteLength() {
            return routeLength;
        }

        public String getNextStop(String currentStop, boolean direction) {
            int currentIndex = stops.indexOf(currentStop);
            if (direction) {
                return stops.get((currentIndex + 1) % routeLength);
            } else {
                return stops.get((currentIndex - 1 + routeLength) % routeLength);
            }
        }
    }

    public static void main(String[] args) {
        List<String> stops = generateRandomStops();
        Route route = new Route(stops);
        List<Vehicle> trains = new ArrayList<>();
        List<Passenger> passengers = new ArrayList<>();
        int turnCount = 0;

        boolean exit = false;
        while (!exit) {
            System.out.println("1. Generate Trains");
            System.out.println("2. Generate Passengers");
            System.out.println("3. Simulate Turns");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    int trainCount = generateRandomNumber(MIN_TRAIN_COUNT, MAX_TRAIN_COUNT);
                    trains = generateTrains(trainCount, route);
                    break;
                case 2:
                    int passengerCount = generateRandomNumber(MIN_PASSENGER_COUNT, MAX_PASSENGER_COUNT);
                    passengers = generatePassengers(passengerCount, stops);
                    break;
                case 3:
                    if (trains.isEmpty() || passengers.isEmpty()) {
                        System.out.println("Please generate trains and passengers first!");
                        break;
                    }
                    turnCount = generateRandomNumber(MIN_TURNS, MAX_TURNS);
                    simulateTurns(turnCount, trains, passengers, route);
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
            System.out.println();
        }
    }

    private static List<String> generateRandomStops() {
        List<String> stops = new ArrayList<>();
        int stopCount = generateRandomNumber(20, GENERIC_STOP_NAMES.length);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stopCount; i++) {
            int randomIndex = generateRandomNumber(0, GENERIC_STOP_NAMES.length - 1);
            sb.append(GENERIC_STOP_NAMES[randomIndex]);
            if (i != stopCount - 1) {
                sb.append(" ");
            }
            stops.add(GENERIC_STOP_NAMES[randomIndex]);
        }
        System.out.println("Route Stops: " + sb.toString());
        return stops;
    }

    private static List<Vehicle> generateTrains(int trainCount, Route route) {
        List<Vehicle> trains = new ArrayList<>();
        for (int i = 1; i <= trainCount; i++) {
            String initialPosition = route.getStops().get(0);
            boolean initialDirection = random.nextBoolean();
            int capacity = 625;
            Vehicle train = new Vehicle(i, initialPosition, initialDirection, capacity);
            trains.add(train);
            System.out.println("Train " + train.getId() + " created at " + train.getCurrentPosition() + " (Direction: "
                    + (train.direction ? "Forward" : "Backward") + ")");
        }
        System.out.println();
        return trains;
    }

    private static List<Passenger> generatePassengers(int passengerCount, List<String> stops) {
        List<Passenger> passengers = new ArrayList<>();
        for (int i = 1; i <= passengerCount; i++) {
            String name = generateRandomName();
            String origin = getRandomStation(stops, "");
            String destination = getRandomDestination(stops, origin);
            int credits = generateRandomNumber(10, 100);
            Passenger passenger = new Passenger(name, origin, destination, credits);
            passengers.add(passenger);
            System.out.println("Passenger " + passenger.getName() + " created (Origin: " + passenger.getOrigin()
                    + ", Destination: " + passenger.getDestination() + ", Credits: " + passenger.getCredits() + ")");
        }
        System.out.println();
        return passengers;
    }

    private static List<Passenger> getWaitingPassengers(List<Passenger> passengers, String currentPosition) {
        List<Passenger> waitingPassengers = new ArrayList<>();
        for (Passenger passenger : passengers) {
            if (passenger.getStatus().equals("waiting") && passenger.getOrigin().equals(currentPosition)) {
                waitingPassengers.add(passenger);
            }
        }
        return waitingPassengers;
    }

    private static String getRandomStation(List<String> stops, String currentStation) {
        String randomStation;
        do {
            int randomIndex = generateRandomNumber(0, stops.size() - 1);
            randomStation = stops.get(randomIndex);
        } while (randomStation.equals(currentStation));
        return randomStation;
    }

    private static String getRandomDestination(List<String> stops, String origin) {
        String randomDestination;
        do {
            int randomIndex = generateRandomNumber(0, stops.size() - 1);
            randomDestination = stops.get(randomIndex);
        } while (randomDestination.equals(origin));
        return randomDestination;
    }

    private static String generateRandomName() {
        int randomIndex = generateRandomNumber(0, GENERIC_PEOPLE_NAMES.length - 1);
        return GENERIC_PEOPLE_NAMES[randomIndex];
    }

    private static int generateRandomNumber(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    private static void updatePassengerActivities(List<Passenger> passengers, List<String> stops) {
        for (Passenger passenger : passengers) {
            if (passenger.getStatus().equals("outside")) {
                passenger.setStatus("waiting");
                passenger.getRoute().clear();
                passenger.getRoute().add(passenger.getOrigin());
            } else if (passenger.getStatus().equals("moving")) {
                String currentStation = passenger.getRoute().get(passenger.getRoute().size() - 1);
                String nextStation = getNextStation(stops, currentStation, passenger.getDestination());
                passenger.getRoute().add(nextStation);
                if (nextStation.equals(passenger.getDestination())) {
                    passenger.setStatus("waiting");
                }
            }
        }
    }

    private static String getNextStation(List<String> stops, String currentStation, String destination) {
        int currentIndex = stops.indexOf(currentStation);
        int destinationIndex = stops.indexOf(destination);
        if (destinationIndex > currentIndex) {
            return stops.get(currentIndex + 1);
        } else if (destinationIndex < currentIndex) {
            return stops.get(currentIndex - 1);
        } else {
            return currentStation;
        }
    }

    private static void updateTrainLoads(List<Vehicle> trains, List<Passenger> passengers) {
        for (Vehicle train : trains) {
            int currentLoad = 0;
            for (Passenger passenger : passengers) {
                if (passenger.getStatus().equals("moving") && passenger.getRoute().contains(train.getCurrentPosition())) {
                    currentLoad++;
                }
            }
            train.setCurrentLoad(currentLoad);
        }
    }

    private static void simulateTurns(int turnCount, List<Vehicle> trains, List<Passenger> passengers, Route route) {
        for (int turn = 1; turn <= turnCount; turn++) {
            System.out.println("Turn " + turn + ":");
            for (Vehicle train : trains) {
                System.out.println("Train " + train.getId() + " at " + train.getCurrentPosition() + " (Load: "
                        + train.getCurrentLoad() + "/" + train.capacity + ")");
                List<Passenger> waitingPassengers = getWaitingPassengers(passengers, train.getCurrentPosition());
                if (!waitingPassengers.isEmpty()) {
                    for (Passenger passenger : waitingPassengers) {
                        if (!train.isFull()) {
                            train.setCurrentLoad(train.getCurrentLoad() + 1);
                            passenger.setStatus("moving");
                            passenger.getRoute().add(train.getCurrentPosition());
                            passenger.setCredits(passenger.getCredits() - FEE);
                            System.out.println(passenger.getName() + " boarded Train " + train.getId() + " (Credits: "
                                    + passenger.getCredits() + ")");
                        } else {
                            System.out.println("Train " + train.getId() + " is full, " + passenger.getName()
                                    + " couldn't board.");
                        }
                    }
                }
                train.move(route);
            }
            updatePassengerActivities(passengers, route.getStops());
            updateTrainLoads(trains, passengers);
            System.out.println();
        }
    }
}
