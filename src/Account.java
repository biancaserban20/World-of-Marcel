import java.util.*;

public class Account {

    Information info;
    List < Character > characters;
    int no_games;

    public Account(Information info, List<Character> characters, int no_games){
        this.characters = characters;
        this.info = info;
        this.no_games = no_games;
    }
    public static class Information{
        private Credentials credentials;
        private Collection < String > games;
        private String name;
        private String country;

        private Information(InformationBuilder builder) {
            this.credentials = builder.credentials;
            this.games = builder.games;
            this.name = builder.name;
            this.country = builder.country;
        }

        @Override
        public String toString() {
            return "Information{" +
                    "credentials=" + credentials +
                    ", games=" + games +
                    ", name='" + name + '\'' +
                    ", country='" + country + '\'' +
                    '}';
        }

        public Credentials getCredentials() {
            return credentials;
        }

        public String getName() {
            return name;
        }
        public Collection<String> getGames() {
            return games;
        }

        public String getCountry() {
            return country;
        }

        public static class InformationBuilder{
            private Credentials credentials;
            private Collection < String > games; //= new TreeSet<String>();
            private String name;
            private String country;

            public InformationBuilder(Credentials credentials, String name){
                this.credentials = credentials;
                this.name = name;
            }

            public InformationBuilder games(Collection<String> games){
                this.games = games;
                return this;
            }

            public InformationBuilder country(String country){
                this.country = country;
                return this;
            }

            public Information build() throws InformationIncompleteException{
                Information information = new Information(this);
                if(information.name == null || information.credentials == null)
                    throw new InformationIncompleteException();
                return information;
            }
        }

    }

    @Override
    public String toString() {
        return "Account{" +
                "info=" + info +
                ", characters=" + characters +
                ", no_games=" + no_games +
                '}';
    }
}
