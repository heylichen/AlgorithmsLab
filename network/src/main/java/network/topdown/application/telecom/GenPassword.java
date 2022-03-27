package network.topdown.application.telecom;

public class GenPassword {
    public static void main(String[] args) {
        String text = "120&105&112&105&103&115&113&101&104&113&109&114&57&55&55&51&57&50&49&53&";
        StringBuilder sb = new StringBuilder();
        for (String s : text.split("&")) {
            sb.append((char) (Integer.parseInt(s)));
        }

        System.out.println(sb);
    }
}
