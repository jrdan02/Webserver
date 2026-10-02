import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class WebServerApplication {
    public static void main(String[] args) {
      
      try{
        WebServer serverWeb = new WebServer(8080); //instance de webserver port 8080
        //System.out.println("coucou");
        serverWeb.run();

    } catch (Exception e) {
        System.out.println("erreur d'execution'");
    }
}
}




