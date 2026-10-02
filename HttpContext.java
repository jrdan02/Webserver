import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpContext {
    private Socket serversocket;

    HttpContext(Socket socket){
        this.serversocket = socket;
    }


    public void getRequest(){
        HttpRequest request = new HttpRequest(this.serversocket);
        request.readClientRequest(this.serversocket);
    }

    public void getResponse(){
        try{
        HttpResponse reponse = new HttpResponse(this.serversocket);
        reponse.ok("bienvenue sur le web server");
        }
        catch(Exception e){
            HttpResponse reponse = new HttpResponse(this.serversocket);
            reponse.notFound("Erreur 404: page introuvable");
        }

    }

    public void close(){
        try{
        this.serversocket.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
}